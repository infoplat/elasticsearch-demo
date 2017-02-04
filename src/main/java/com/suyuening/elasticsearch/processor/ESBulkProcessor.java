package com.suyuening.elasticsearch.processor;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;

import com.suyuening.elasticsearch.utils.ESClient;

public class ESBulkProcessor {
	public static void process(String index, String type, List<String> jsonFields) {
		try (Client client = ESClient.client("es5-bi-cluster")) {
			BulkProcessor bulkProcessor = BulkProcessor.builder(client, new BulkProcessor.Listener() {
				public void beforeBulk(long executionId, BulkRequest request) {
				}

				public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
				}

				public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
				}
			}).setBulkActions(150000).setBulkSize(new ByteSizeValue(100, ByteSizeUnit.MB))
					.setFlushInterval(TimeValue.timeValueSeconds(30)).setConcurrentRequests(1)
					.setBackoffPolicy(BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3)).build();

			for (String json : jsonFields) {
				bulkProcessor.add(new IndexRequest(index, type).source(json));
			}
			bulkProcessor.awaitClose(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
