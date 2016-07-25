package com.suyuening.elasticsearch.demo.searchapi;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;

import com.suyuening.elasticsearch.util.ESClient;

/**
 * <a href=
 * "https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-search-aggs.html"
 * target="_blank">查看ES Doc</a>
 * 
 * @author suyuening
 *
 */
public class UsingAggregations {
    public static void main(String[] args) {

        try {
            // TODO 没有弄明白这个示例
            // The following code shows how to add two aggregations within your search:
            SearchResponse sr =
                    ESClient.client().prepareSearch("movielens")
                            .setQuery(QueryBuilders.matchAllQuery())
                            .addAggregation(AggregationBuilders.terms("agg").field("userId"))
                            .execute().actionGet();

            // Get your facet results
            Terms agg1 = sr.getAggregations().get("agg");
            System.out.println(agg1.getName());
            // DateHistogram agg2 = sr.getAggregations().get("agg2");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ESClient.close();
        }

    }
}
