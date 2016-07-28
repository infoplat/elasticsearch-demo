package com.suyuening.elasticsearch.demo.searchapi;

import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import com.suyuening.elasticsearch.util.ESClient;

public class MultiSearchAPI {
    public static void main(String[] args) {
        SearchRequestBuilder srb1 = ESClient.client().prepareSearch("movielens", "school")
                .setQuery(QueryBuilders.queryStringQuery("xiao")).setSize(1000);
        SearchRequestBuilder srb2 = ESClient.client().prepareSearch("movielens", "school")
                .setQuery(QueryBuilders.matchQuery("movieId", "27515")).setSize(1000);

        MultiSearchResponse sr =
                ESClient.client().prepareMultiSearch().add(srb1).add(srb2).execute().actionGet();

        // You will get all individual responses from MultiSearchResponse#getResponses()
        long nbHits = 0;
        for (MultiSearchResponse.Item item : sr.getResponses()) {
            SearchResponse response = item.getResponse();
            if (response == null) {
                continue;
            }
            SearchHits hits = response.getHits();
            nbHits += hits.getTotalHits();
            for (SearchHit searchHit : hits) {
                System.out.println(searchHit.getIndex());
                System.out.println(searchHit.getType());
                System.out.println(searchHit.getId());
                System.out.println(searchHit.getSourceAsString());
            }
            System.out.println(String.format("TotalHits:%d", nbHits));
        }

        ESClient.close();
    }
}
