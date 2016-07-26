package com.suyuening.elasticsearch.demo.searchapi;

import java.util.Iterator;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.junit.Test;

import com.suyuening.elasticsearch.util.ESClient;

public class GradeAndClassAggregation {
    /**
     * Java分组统计年级和班级学生个数.<br/>
     * 如SQL: SELECT grade,class,count(1) FROM student GROUP BY grade,class;
     */
    @Test
    public void testAggregation() {

        // 索引数据
        // index:school
        // type:student
        // ---------------------------------------------------
        // {"grade":"1", "class":"1", "name":"xiao 1"}
        // {"grade":"1", "class":"1", "name":"xiao 2"}
        // {"grade":"1", "class":"2", "name":"xiao 3"}
        // {"grade":"1", "class":"2", "name":"xiao 4"}
        // {"grade":"1", "class":"2", "name":"xiao 5"}
        // {"grade":"2", "class":"1", "name":"xiao 6"}
        // {"grade":"2", "class":"1", "name":"xiao 7"}
        // {"grade":"2", "class":"1", "name":"xiao 8"}
        // {"grade":"2", "class":"2", "name":"xiao 9"}
        // {"grade":"2", "class":"2", "name":"xiao 10"}

        // 输出结果
        // 1年级有5个学生。
        // 1年级2班有3个学生。
        // 1年级1班有2个学生。
        //
        // 2年级有5个学生。
        // 2年级1班有3个学生。
        // 2年级2班有2个学生。

        SearchResponse sr = ESClient.client().prepareSearch("school").setTypes("student")
                .addAggregation(AggregationBuilders.terms("gradeAgg").field("grade")
                        .subAggregation(AggregationBuilders.terms("classAgg").field("class")))
                .execute().actionGet();

        Map<String, Aggregation> aggMap = sr.getAggregations().asMap();

        StringTerms gradeTerms = (StringTerms) aggMap.get("gradeAgg");

        Iterator<Bucket> gradeBucketIt = gradeTerms.getBuckets().iterator();

        while (gradeBucketIt.hasNext()) {
            Bucket gradeBucket = gradeBucketIt.next();
            System.out.println(gradeBucket.getKey() + "年级有" + gradeBucket.getDocCount() + "个学生。");

            StringTerms classTerms =
                    (StringTerms) gradeBucket.getAggregations().asMap().get("classAgg");
            Iterator<Bucket> classBucketIt = classTerms.getBuckets().iterator();

            while (classBucketIt.hasNext()) {
                Bucket classBucket = classBucketIt.next();
                System.out.println(gradeBucket.getKey() + "年级" + classBucket.getKey() + "班有"
                        + classBucket.getDocCount() + "个学生。");
            }
            System.out.println();
        }

    }
}
