package com.suyuening.elasticsearch.movielens.converter;

import com.suyuening.elasticsearch.movielens.domain.Links;
import com.suyuening.elasticsearch.util.BaseCsv2Jsons;

public class LinksCsv2Jsons extends BaseCsv2Jsons<Links> {

    protected Links makeDomain(Links bean, String[] array) {
        // movieId,imdbId,tmdbId
        // 1,0114709,862
        bean = new Links();
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
