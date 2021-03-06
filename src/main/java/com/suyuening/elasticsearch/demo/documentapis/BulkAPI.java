package com.suyuening.elasticsearch.demo.documentapis;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.util.Date;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;

import com.suyuening.elasticsearch.utils.ESClient;

/**
 * <a href=
 * "https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-docs-bulk.html"
 * target="_blank">查看ES Doc</a>
 * 
 * @author suyuening
 *
 */
public class BulkAPI {
	public static void main(String[] args) {
		BulkRequestBuilder bulkRequest = null;
		// either use client#prepare, or use Requests# to directly build
		// index/delete requests
		try (Client client = ESClient.client()) {
			bulkRequest = client.prepareBulk();
			// 如果文档已经存在会更新原来的文档
			bulkRequest.add(client.prepareIndex("twitter", "tweet", "1")
					.setSource(jsonBuilder().startObject().field("user", "zhangsan").field("postDate", new Date())
							.field("message", "zhangsan trying out Elasticsearch!").endObject()));
			bulkRequest.add(client.prepareIndex("twitter", "tweet", "3")
					.setSource(jsonBuilder().startObject().field("user", "lisi").field("postDate", new Date())
							.field("message", "lisi trying out Elasticsearch!").endObject()));

			BulkResponse bulkResponse = bulkRequest.get();
			if (bulkResponse.hasFailures()) {
				// process failures by iterating through each bulk response item
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
