package com.suyuening.elasticsearch.demo.countapi;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import org.elasticsearch.action.count.CountResponse;

import com.suyuening.elasticsearch.util.ESClient;

/**
 * <a href=
 * "https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/count.html"
 * target="_blank">查看ES Doc</a>
 * 
 * @author suyuening
 *
 */
public class CountAPI {
    public static void main(String[] args) {
        CountResponse response = ESClient.client().prepareCount("customer")
                .setQuery(termQuery("_type", "external"))
                .execute()
                .actionGet();

        System.out.println(response.getCount());

        ESClient.close();
    }
}
