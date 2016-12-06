package com.suyuening.elasticsearch.demo.documentapis;

import org.elasticsearch.action.ActionWriteResponse.ShardInfo;
import org.elasticsearch.action.delete.DeleteResponse;

import com.suyuening.elasticsearch.utils.ESClient;

/**
 * <a href="https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-docs-delete.html" target="_blank">查看ES Doc</a>
 * @author suyuening
 *
 */
public class DeleteAPI {
    public static void main(String[] args) {
//        DeleteResponse response =
//                ESClient.client().prepareDelete("twitter", "tweet", "AVXh7CqcuJHvs-RbNMiT").get();

        DeleteResponse response = ESClient.client().prepareDelete("twitter", "tweet", "1")
                .get();
        
        if (response != null && response.isFound()) {
            System.out.println(response.getIndex());
            System.out.println(response.getType());
            System.out.println(response.getId());
            System.out.println(response.getVersion());

            ShardInfo shardInfo = response.getShardInfo();
            System.out.println(shardInfo.getTotal());
            System.out.println(shardInfo.getFailed());
            System.out.println(shardInfo.getSuccessful());
        }

        ESClient.close();
    }
}
