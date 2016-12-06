package com.suyuening.elasticsearch.movielens.indexprocessor;

import com.suyuening.elasticsearch.movielens.domain.Movies;

public class MoviesIndexProcessor extends BaseIndexProcessor<Movies> {

    protected Movies makeDomain(Movies bean, String line) {
        // movieId,title,genres
        // 1,Toy Story (1995),Adventure|Animation|Children|Comedy|Fantasy
        bean = new Movies();
        String[] array = line.split(",");
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
