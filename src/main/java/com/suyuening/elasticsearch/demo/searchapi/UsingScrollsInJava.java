package com.suyuening.elasticsearch.demo.searchapi;

import static org.elasticsearch.index.query.QueryBuilders.*;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.sort.SortParseElement;

import com.suyuening.elasticsearch.util.ESClient;

/**
 * <a href=
 * "https://https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-search-scrolling.html"
 * target="_blank">查看ES Doc</a>
 * 
 * @author suyuening
 *
 */
public class UsingScrollsInJava {
    public static void main(String[] args) {
        QueryBuilder qb = termQuery("user", "lashou.com");

        SearchResponse scrollResp = ESClient.client().prepareSearch("customer", "twitter")
                .addSort(SortParseElement.DOC_FIELD_NAME, SortOrder.ASC)
                .setScroll(new TimeValue(60000)).setQuery(qb).setSize(100).execute().actionGet(); // 100
                                                                                                  // hits
                                                                                                  // per
                                                                                                  // shard
                                                                                                  // will
                                                                                                  // be
                                                                                                  // returned
                                                                                                  // for
                                                                                                  // each
                                                                                                  // scroll

        // Scroll until no hits are returned
        while (true) {

            for (SearchHit hit : scrollResp.getHits().getHits()) {
                System.out.println(hit.getIndex());
                System.out.println(hit.getType());
                System.out.println(hit.getId());
                System.out.println(hit.getSourceAsString());
            }
            scrollResp = ESClient.client().prepareSearchScroll(scrollResp.getScrollId())
                    .setScroll(new TimeValue(60000)).execute().actionGet();
            // Break condition: No hits are returned
            if (scrollResp.getHits().getHits().length == 0) {
                break;
            }
        }

        ESClient.close();
    }
}
