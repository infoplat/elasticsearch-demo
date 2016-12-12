package com.suyuening.elasticsearch.demo.searchapi;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import com.suyuening.elasticsearch.utils.ESClient;

/**
 * <a href=
 * "https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-search-terminate-after.html"
 * target="_blank">查看ES Doc</a>
 * 
 * @author suyuening
 *
 */
public class TerminateAfter {
	public static void main(String[] args) {
		try (Client client = ESClient.client()) {
			SearchResponse sr = client.prepareSearch("customer").setTerminateAfter(5).get();

			if (sr.isTerminatedEarly()) {
				// We finished early
				SearchHits hits = sr.getHits();
				for (SearchHit searchHit : hits) {
					System.out.println(searchHit.getIndex());
					System.out.println(searchHit.getType());
					System.out.println(searchHit.getId());
					System.out.println(searchHit.getSourceAsString());
				}
			}
		}
	}
}
