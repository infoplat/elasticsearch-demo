package com.suyuening.elasticsearch.movielens.index;

import java.util.List;

import com.suyuening.elasticsearch.movielens.converter.RatingsCsv2Jsons;
import com.suyuening.elasticsearch.processor.ESBulkProcessor;

public class IndexRatings {
    public static void main(String[] args) {
        List<String> jsonLines = new RatingsCsv2Jsons().csv2JsonLines("E:\\data\\movielens\\ml-latest-small\\ratings.csv");
        ESBulkProcessor.process("movielens", "ratings", jsonLines);
        System.exit(0);
    }
}