package com.suyuening.elasticsearch.movielens.index;

import java.util.List;

import com.suyuening.elasticsearch.movielens.converter.TagsCsv2Jsons;
import com.suyuening.elasticsearch.processor.ESBulkProcessor;

public class IndexTags {
    public static void main(String[] args) {
        List<String> jsonLines = new TagsCsv2Jsons().csv2JsonLines("E:\\data\\movielens\\ml-latest-small\\tags.csv");
        ESBulkProcessor.process("movielens", "tags", jsonLines);
        System.exit(0);
    }
}