package com.suyuening.elasticsearch.movielens.domain;

public class Links {
    /** 电影ID */
    private int movieId;
    /** ImdbId */
    private String imdbId;
    /** TmdbId */
    private String tmdbId;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(String tmdbId) {
        this.tmdbId = tmdbId;
    }

}
