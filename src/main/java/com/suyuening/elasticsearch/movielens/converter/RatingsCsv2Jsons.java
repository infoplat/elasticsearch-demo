package com.suyuening.elasticsearch.movielens.converter;

import com.suyuening.elasticsearch.movielens.domain.Ratings;
import com.suyuening.elasticsearch.util.BaseCsv2Jsons;

public class RatingsCsv2Jsons extends BaseCsv2Jsons<Ratings> {

    protected Ratings makeDomain(Ratings bean, String[] array) {
        // userId,movieId,rating,timestamp
        // 1,16,4.0,1217897793
        bean = new Ratings();
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
