package com.suyuening.elasticsearch.movielens.indexprocessor;

import com.suyuening.elasticsearch.movielens.domain.Tags;

public class TagsIndexProcessor extends BaseIndexProcessor<Tags> {

    protected Tags makeDomain(Tags bean, String line) {
        // userId,movieId,tag,timestamp
        // 12,16,robert de niro,1144396554
        bean = new Tags();
        String[] array = line.split(",");
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
