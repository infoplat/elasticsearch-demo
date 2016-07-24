package com.suyuening.elasticsearch.movielens.converter;

import com.suyuening.elasticsearch.movielens.domain.Movies;
import com.suyuening.elasticsearch.util.BaseCsv2Jsons;

public class MoviesCsv2Jsons extends BaseCsv2Jsons<Movies> {

    protected Movies makeDomain(Movies bean, String[] array) {
        // movieId,title,genres
        // 1,Toy Story (1995),Adventure|Animation|Children|Comedy|Fantasy
        bean = new Movies();
        try {
            bean.setMovieId(Integer.parseInt(array[0]));
            bean.setTitle(array[1]);
            bean.setGenres(array[2]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
