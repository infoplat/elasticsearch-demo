package com.suyuening.elasticsearch.movielens.index;

import java.util.List;

import com.suyuening.elasticsearch.movielens.converter.LinksCsv2Jsons;
import com.suyuening.elasticsearch.processor.ESBulkProcessor;

public class IndexLinks {
    public static void main(String[] args) {
        List<String> jsonLines = new LinksCsv2Jsons().csv2JsonLines("E:\\data\\movielens\\ml-latest-small\\links.csv");
        ESBulkProcessor.process("movielens", "links", jsonLines);
        System.exit(0);
    }
}