package com.suyuening.elasticsearch.demo.documentapis;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;

import com.suyuening.elasticsearch.util.ESClient;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

/**
 * <a href="https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-docs-index.html" target="_blank">查看ES Doc</a>
 * @author suyuening
 */
public class IndexAPI {
    public static void main(String[] args) {
     // on startup
//        Client client = null;
        try {
//            client = TransportClient.builder().build()
//                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("Centos_6_6_01"), 9300))
//                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("Centos_6_6_02"), 9300));

//            client = ESClientUtil.client();

//            Note that you have to set the cluster name if you use one different than "elasticsearch"
//            Settings settings = Settings.settingsBuilder()
//                    .put("cluster.name", "myClusterName").build();
//            Client client = TransportClient.builder().settings(settings).build();
            //Add transport addresses and do something with the client...
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        // on shutdown

//        String json = "{" +
//                "\"user\":\"suyuening\"," +
//                "\"postDate\":\"2016-07-12\"," +
//                "\"message\":\"Hello Elasticsearch\"" +
//            "}";
        
//        Map<String, Object> json = new HashMap<String, Object>();
//        json.put("user","suyuening");
//        json.put("postDate",new Date());
//        json.put("message","Hello Elasticsearch");
        
        
        try {
//            XContentBuilder builder = jsonBuilder()
//                    .startObject()
//                        .field("user", "suyuening")
//                        .field("postDate", new Date())
//                        .field("message", "Hello Elasticsearch")
//                    .endObject();
        
//        json = builder.string();
            IndexResponse response = ESClient.client().prepareIndex("twitter", "tweet")
                    .setSource(jsonBuilder()
                        .startObject()
                        .field("user", "China")
                        .field("postDate", new Date())
                        .field("message", "Hello China!")
                        .endObject()
                            )
                    .get();
            
         // Index name
            String _index = response.getIndex();
            System.out.println(_index);
            // Type name
            String _type = response.getType();
            System.out.println(_type);
            // Document ID (generated or not)
            String _id = response.getId();
            System.out.println(_id);
            // Version (if it's the first time you index this document, you will get: 1)
            long _version = response.getVersion();
            System.out.println(_version);
            // isCreated() is true if the document is a new one, false if it has been updated
            boolean created = response.isCreated();
            System.out.println(created);
            
//            IndexResponse response = client.prepareIndex("twitter", "tweet")
//                    .setSource(json)
//                    .get();
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}
