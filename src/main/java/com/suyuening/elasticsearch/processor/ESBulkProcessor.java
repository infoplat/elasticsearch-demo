package com.suyuening.elasticsearch.processor;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;

import com.suyuening.elasticsearch.utils.ESClient;

public class ESBulkProcessor {
    public static void process(String index, String type, List<String> jsonFields) {
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
            .setBulkActions(100000) 
            .setBulkSize(new ByteSizeValue(1, ByteSizeUnit.GB)) 
            .setFlushInterval(TimeValue.timeValueSeconds(5)) 
            .setConcurrentRequests(10) 
            .setBackoffPolicy(BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3)) 
            .build();

        boolean isComplete = false;

        try {

            for (String json : jsonFields) {
                bulkProcessor.add(new IndexRequest(index, type).source(json));
            }
            isComplete = bulkProcessor.awaitClose(30, TimeUnit.MINUTES);
            if (isComplete) {
                ESClient.close();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (!isComplete) {
                ESClient.close();
            }
        }
    }
}
