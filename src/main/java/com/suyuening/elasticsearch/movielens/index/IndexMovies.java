package com.suyuening.elasticsearch.movielens.index;

import java.util.List;

import com.suyuening.elasticsearch.movielens.converter.MoviesCsv2Jsons;
import com.suyuening.elasticsearch.processor.ESBulkProcessor;

public class IndexMovies {
    public static void main(String[] args) {
        List<String> jsonLines = new MoviesCsv2Jsons().csv2JsonLines("E:\\data\\movielens\\ml-latest-small\\movies.csv");
        ESBulkProcessor.process("movielens", "movies", jsonLines);
        System.exit(0);
    }
}