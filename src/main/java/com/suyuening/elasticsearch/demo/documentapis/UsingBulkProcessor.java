package com.suyuening.elasticsearch.demo.documentapis;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;

import com.suyuening.elasticsearch.util.ESClient;

public class UsingBulkProcessor {
    public static void main(String[] args) {
        BulkProcessor bulkProcessor = BulkProcessor.builder(
            ESClient.client(),  
            new BulkProcessor.Listener() {
                public void beforeBulk(long executionId,
                                       BulkRequest request) {} 

                public void afterBulk(long executionId,
                                      BulkRequest request,
                                      BulkResponse response) {} 

                public void afterBulk(long executionId,
                                      BulkRequest request,
                                      Throwable failure) {} 
            })
            .setBulkActions(10000) 
            .setBulkSize(new ByteSizeValue(1, ByteSizeUnit.GB)) 
            .setFlushInterval(TimeValue.timeValueSeconds(5)) 
            .setConcurrentRequests(1) 
            .setBackoffPolicy(
                BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3)) 
            .build();
        
        boolean isComplete = false; 
                
        try {

            bulkProcessor.add(new IndexRequest("twitter", "tweet", "4").source(
                jsonBuilder()
                .startObject()
                .field("user", "wangwu")
                .field("postDate", new Date())
                .field("message", "Hello wangwu!")
                .endObject()
                    ));
            bulkProcessor.add(new DeleteRequest("twitter", "tweet", "3"));

            isComplete = bulkProcessor.awaitClose(10, TimeUnit.MINUTES);
            if (isComplete) {
                ESClient.close();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (!isComplete) {
                ESClient.close();
            }
        }
    }
}
