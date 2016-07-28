package com.suyuening.elasticsearch.demo.searchapi;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import com.suyuening.elasticsearch.util.ESClient;

/**
 * <a href="https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-search.html" target="_blank">查看ES Doc</a>
 * @author suyuening
 *
 */
public class SearchAPI {
    public static void main(String[] args) {
        SearchResponse response = ESClient.client().prepareSearch("movielens", "school") // 指定index
                .setTypes("ratings", "student") // 指定type
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("movieId", "367"))                 // Query
//                .setPostFilter(QueryBuilders.rangeQuery("rating").from(4).to(4.5))     // Filter
                .setFrom(0).setSize(60).setExplain(true)
                .execute()
                .actionGet();

     // MatchAll on the whole cluster with all default options
//        SearchResponse response = ESClient.client().prepareSearch().execute().actionGet();
        
        SearchHits hits = response.getHits();
        for (SearchHit searchHit : hits) {
            System.out.println(searchHit.getIndex());
            System.out.println(searchHit.getType());
            System.out.println(searchHit.getId());
            System.out.println(searchHit.getSourceAsString());
        }
        System.out.println(hits.getTotalHits());

        ESClient.close();
    }
}
