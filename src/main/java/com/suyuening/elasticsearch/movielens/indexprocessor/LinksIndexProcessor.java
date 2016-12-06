package com.suyuening.elasticsearch.movielens.indexprocessor;

import com.suyuening.elasticsearch.movielens.domain.Links;

public class LinksIndexProcessor extends BaseIndexProcessor<Links> {

	@Override
	protected Links makeDomain(Links bean, String line) {
		// movieId,imdbId,tmdbId
		// 1,0114709,862
		bean = new Links();
		String[] array = line.split(",");
		try {
			bean.setMovieId(Integer.parseInt(array[0]));
			bean.setImdbId(array[1]);
			bean.setTmdbId(array[2]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
}
