package com.suyuening.elasticsearch.movielens.indexprocessor;

import com.suyuening.elasticsearch.movielens.domain.Ratings;

public class RatingsIndexProcessor extends BaseIndexProcessor<Ratings> {

	@Override
	protected Ratings makeDomain(Ratings bean, String line) {
		// userId,movieId,rating,timestamp
		// 1,16,4.0,1217897793
		bean = new Ratings();
		String[] array = line.split(",");
		try {
			bean.setUserId(Integer.parseInt(array[0]));
			bean.setMovieId(Integer.parseInt(array[1]));
			bean.setRating(Float.parseFloat(array[2]));
			bean.setTimestamp(Long.parseLong(array[3]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
}
