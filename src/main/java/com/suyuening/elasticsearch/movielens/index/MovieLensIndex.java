package com.suyuening.elasticsearch.movielens.index;

import com.suyuening.elasticsearch.movielens.indexprocessor.LinksIndexProcessor;
import com.suyuening.elasticsearch.movielens.indexprocessor.MoviesIndexProcessor;
import com.suyuening.elasticsearch.movielens.indexprocessor.RatingsIndexProcessor;
import com.suyuening.elasticsearch.movielens.indexprocessor.TagsIndexProcessor;

public class MovieLensIndex {
	public static void main(String[] args) {
		final String index = "movielens";

		new RatingsIndexProcessor().readAndIndexData(index, "ratings", "E:\\data\\movielens\\ml-latest-small\\ratings.csv");
		new LinksIndexProcessor().readAndIndexData(index, "links", "E:\\data\\movielens\\ml-latest-small\\links.csv");
		new MoviesIndexProcessor().readAndIndexData(index, "movies", "E:\\data\\movielens\\ml-latest-small\\movies.csv");
		new TagsIndexProcessor().readAndIndexData(index, "tags", "E:\\data\\movielens\\ml-latest-small\\tags.csv");
		
//		new GenomeTagsIndexProcessor().readAndIndexData(index, "genome-tags", "E:\\data\\movielens\\ml-latest-small\\genome-tags.csv");
//		new GenomeScoresIndexProcessor().readAndIndexData(index, "genome-scores", "E:\\data\\movielens\\ml-latest-small\\genome-scores.csv");
		
		System.exit(0);
	}
}