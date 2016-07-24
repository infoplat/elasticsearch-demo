package com.suyuening.elasticsearch.demo.documentapis;

import java.util.Map;

import org.elasticsearch.action.get.GetResponse;

import com.suyuening.elasticsearch.util.ESClient;

/**
 * <a href="https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-docs-get.html" target="_blank">查看ES Doc</a>
 * @author suyuening
 *
 */
public class GetAPI {
    public static void main(String[] args) {
        GetResponse response = ESClient.client().prepareGet("twitter", "tweet", "2")
                .setOperationThreaded(false) //默认为true
                .get();
        if (response != null && response.isExists()) {
            System.out.println(response.getIndex());
            System.out.println(response.getType());
            System.out.println(response.getId());
            System.out.println(response.getVersion());
            
            Map<String, Object> fields = response.getSource();
            for (Map.Entry<String, Object> field : fields.entrySet()) {
                System.out.println(String.format("%s:%s", field.getKey(), field.getValue()));
            }
            
            System.out.println(response.getSourceAsString());
        }
        ESClient.close();
    }
}
