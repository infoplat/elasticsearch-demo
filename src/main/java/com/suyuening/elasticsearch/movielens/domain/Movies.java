package com.suyuening.elasticsearch.movielens.domain;

public class Movies {
    /** 电影ID */
    private int movieId;
    /** 电影名 */
    private String title;
    /** 流派 */
    private String genres;

    /**
     * 取得：电影ID
     * 
     * @return
     */
    public int getMovieId() {
        return this.movieId;
    }

    /**
     * 设置：电影ID
     * 
     * @param movieId
     */
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    /**
     * 取得：电影名
     * 
     * @return
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 设置：电影名
     * 
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 取得：流派
     * 
     * @return
     */
    public String getGenres() {
        return this.genres;
    }

    /**
     * 设置：流派
     * 
     * @param genres
     */
    public void setGenres(String genres) {
        this.genres = genres;
    }
}
