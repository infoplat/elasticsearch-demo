package com.suyuening.elasticsearch.movielens.domain;

public class GenomeScores {
	/** 电影ID */
	private int movieId;
	/** 标签ID */
	private int tagId;
	/** 相关性 */
	private double relevance;

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public double getRelevance() {
		return relevance;
	}

	public void setRelevance(double relevance) {
		this.relevance = relevance;
	}

}
