package com.suyuening.elasticsearch.movielens.indexprocessor;

import com.suyuening.elasticsearch.movielens.domain.GenomeScores;

public class GenomeScoresIndexProcessor extends BaseIndexProcessor<GenomeScores> {

	@Override
	protected GenomeScores makeDomain(GenomeScores bean, String line) {
		// movieId,tagId,relevance
		// 1,1,0.02400000000000002
		bean = new GenomeScores();
		String[] array = line.split(",");
		try {
			bean.setMovieId(Integer.parseInt(array[0]));
			bean.setTagId(Integer.parseInt(array[1]));
			bean.setRelevance(Double.parseDouble(array[2]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
}
