package com.suyuening.elasticsearch.demo.countapi;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;
import static org.junit.Assert.assertEquals;

import org.elasticsearch.action.count.CountResponse;
import org.junit.Test;

import com.suyuening.elasticsearch.utils.ESClient;

/**
 * <a href=
 * "https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/count.html"
 * target="_blank">查看ES Doc</a>
 * 
 * @author suyuening
 *
 */
public class CountAPI {
	@Test
	public void testCount() {
		@SuppressWarnings("deprecation")
		CountResponse response = ESClient.client().prepareCount("movielens").setQuery(termQuery("_type", "movies"))
				.setQuery(termQuery("movieId", "1999")).execute().actionGet();

		assertEquals(17, response.getCount());

		ESClient.close();
	}
}
