package com.suyuening.elasticsearch.movielens.indexprocessor;

import com.suyuening.elasticsearch.movielens.domain.GenomeTags;

public class GenomeTagsIndexProcessor extends BaseIndexProcessor<GenomeTags> {

	@Override
	protected GenomeTags makeDomain(GenomeTags bean, String line) {
		// tagId,tag
		// 2,007 (series)
		bean = new GenomeTags();
		String[] array = line.split(",");
		try {
			bean.setTagId(Integer.parseInt(array[0]));
			bean.setTag(array[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
}
