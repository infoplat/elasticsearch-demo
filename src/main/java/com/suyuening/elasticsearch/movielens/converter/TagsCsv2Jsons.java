package com.suyuening.elasticsearch.movielens.converter;

import com.suyuening.elasticsearch.movielens.domain.Tags;
import com.suyuening.elasticsearch.util.BaseCsv2Jsons;

public class TagsCsv2Jsons extends BaseCsv2Jsons<Tags> {

    protected Tags makeDomain(Tags bean, String[] array) {
        // userId,movieId,tag,timestamp
        // 12,16,robert de niro,1144396554
        bean = new Tags();
        try {
            bean.setUserId(Integer.parseInt(array[0]));
            bean.setMovieId(Integer.parseInt(array[1]));
            bean.setTag(array[2]);
            bean.setTimestamp(Long.parseLong(array[3]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
