package com.suyuening.elasticsearch.movielens.index;

import com.suyuening.elasticsearch.movielens.indexprocessor.GenomeScoresIndexProcessor;

public class MovieLensIndex {
	public static void main(String[] args) {
		final String index = "movielens";

//		new RatingsIndexProcessor().readAndIndexData(index, "ratings", "E:\\data\\movielens\\ml-latest\\ratings.csv");
//		new LinksIndexProcessor().readAndIndexData(index, "links", "E:\\data\\movielens\\ml-latest\\links.csv");
//		new MoviesIndexProcessor().readAndIndexData(index, "movies", "E:\\data\\movielens\\ml-latest\\movies.csv");
//		new TagsIndexProcessor().readAndIndexData(index, "tags", "E:\\data\\movielens\\ml-latest\\tags.csv");
		
//		new GenomeTagsIndexProcessor().readAndIndexData(index, "genome-tags", "E:\\data\\movielens\\ml-latest\\genome-tags.csv");
		new GenomeScoresIndexProcessor().readAndIndexData(index, "genome-scores", "E:\\data\\movielens\\ml-latest\\genome-scores.csv");
		
		System.exit(0);
	}
}