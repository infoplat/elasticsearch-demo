package com.suyuening.elasticsearch.demo.documentapis;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.client.Client;

import com.suyuening.elasticsearch.utils.ESClient;

/**
 * <a href=
 * "https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-docs-multi-get.html"
 * target="_blank">查看ES Doc</a>
 * 
 * @author suyuening
 */
public class MultiGetAPI {
	public static void main(String[] args) {
		try (Client client = ESClient.client()) {
			MultiGetResponse multiGetItemResponses = client.prepareMultiGet().add("twitter", "tweet", "2")
					.add("twitter", "tweet", "AVXiEhAXbOm9AJ7RGovT", "AVXh51G_uJHvs-RbNMiR", "AVXh6nmjuJHvs-RbNMiS")
					.add("customer", "external", "AVXY-f0oloyBfKBINKC_").get();

			for (MultiGetItemResponse itemResponse : multiGetItemResponses) {
				GetResponse response = itemResponse.getResponse();
				if (response.isExists()) {
					String json = response.getSourceAsString();
					System.out.println(json);
				}
			}

		}
	}
}
