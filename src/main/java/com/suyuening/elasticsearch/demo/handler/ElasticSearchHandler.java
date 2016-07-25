package com.suyuening.elasticsearch.demo.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import com.suyuening.elasticsearch.demo.domain.Sxtdept;
import com.suyuening.elasticsearch.util.ESClient;

public class ElasticSearchHandler {

    private Client client;
    public ElasticSearchHandler()  {
        client = ESClient.client();
    }
    
    /**
     * 建立索引,索引建立好之后,会在elasticsearch\data\elasticsearch\nodes\0创建所以你看
     * @param indexname  为索引库名，一个es集群中可以有多个索引库。 名称必须为小写
     * @param type  Type为索引类型，是用来区分同索引库下不同类型的数据的，一个索引库下可以有多个索引类型。
     * @param jsondata     json格式的数据集合
     * 
     * @return
     */
    public void createIndexResponse(String indexname, String type, List<String> jsondata){
        //创建索引库 需要注意的是.setRefresh(true)这里一定要设置,否则第一次建立索引查找不到数据
        IndexRequestBuilder requestBuilder = client.prepareIndex(indexname, type).setRefresh(true);
        for(int i=0; i<jsondata.size(); i++){
            requestBuilder.setSource(jsondata.get(i)).execute().actionGet();
        }     
         
    }

    public void deleteIndexResponse(String indexname, String type, List<String> jsondata){
        //创建索引库 需要注意的是.setRefresh(true)这里一定要设置,否则第一次建立索引查找不到数据
        IndexRequestBuilder requestBuilder = client.prepareIndex(indexname, type).setRefresh(true);
        for(int i=0; i<jsondata.size(); i++){
            requestBuilder.setSource(jsondata.get(i)).execute().actionGet();
        }

    }

    public IndexResponse createIndexResponse(String indexname, String type, String jsondata){
        IndexResponse response = client.prepareIndex(indexname, type)
            .setSource(jsondata)
            .execute()
            .actionGet();
        return response;
    }
    
    /**
     * 执行搜索
     * @param queryBuilder
     * @param indexname
     * @param type
     * @return
     */
    public List<Sxtdept>  searcher(QueryBuilder queryBuilder, String indexname, String type){
        List<Sxtdept> list = new ArrayList<Sxtdept>();
        SearchResponse searchResponse = client.prepareSearch(indexname).setTypes(type)
        .setQuery(queryBuilder)
        .execute()
        .actionGet();
        SearchHits hits = searchResponse.getHits();
        System.out.println("查询到记录数=" + hits.getTotalHits());
        SearchHit[] searchHists = hits.getHits();
        if(searchHists.length>0){
            for(SearchHit hit:searchHists){
                Integer id = (Integer)hit.getSource().get("id");
                String name =  (String) hit.getSource().get("name");
                String function =  (String) hit.getSource().get("funciton");
                list.add(new Sxtdept(id, name, function));
            }
        }
        return list;
    }
    public static String obj2JsonData(Sxtdept sxtdept){
        String jsonData = null;
        try {
            //使用XContentBuilder创建json数据
            XContentBuilder jsonBuild = XContentFactory.jsonBuilder();
            jsonBuild.startObject()
                    .field("id", sxtdept.getId())
                    .field("name", sxtdept.getName())
                    .field("funciton", sxtdept.getFunction())
                    .endObject();
            jsonData = jsonBuild.string();
//            System.out.println(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData;
    }
    public static List<String> getInitJsonData(){
        List<String> list = new ArrayList<String>();
        String data1  = obj2JsonData(new Sxtdept(1,"尚学堂java部门","功能：培训java，ssh三大框架"));
        String data2  = obj2JsonData(new Sxtdept(2,"尚学堂前端部门","功能：培训web前端，easyUI jQuery"));
        String data3  = obj2JsonData(new Sxtdept(3,"尚学堂ios部门","功能：培训苹果应用ios开发"));
        String data4  = obj2JsonData(new Sxtdept(4,"尚学堂安卓部门","功能：培训安卓手机应用开发"));
        String data5  = obj2JsonData(new Sxtdept(5,"前台妹子","功能：代表公司形象"));
        String data6  = obj2JsonData(new Sxtdept(6,"尚学堂大数据部门","功能：培训数据挖掘 大数据云计算技术"));
        list.add(data1);
        list.add(data2);
        list.add(data3);
        list.add(data4);
        list.add(data5);
        list.add(data6);
        return list;
    }

    
    public static void main(String[] args) {
        ElasticSearchHandler esHandler = new ElasticSearchHandler();
        List<String> jsondata = getInitJsonData();
        String indexname = "indexdemo";
        String type = "typedemo";
//        esHandler.createIndexResponse(indexname, type, jsondata);
        //查询条件
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("funciton", "培训");
        List<Sxtdept> result = esHandler.searcher(queryBuilder, indexname, type);
        for(int i=0; i<result.size(); i++){
            Sxtdept sxtdept = result.get(i);
            System.out.println("(" + sxtdept.getId() + ")部门名称:" + sxtdept.getName() + "\t\t" + sxtdept.getFunction());
        }
    }
}