package com.suyuening.elasticsearch.movielens.domain;

public class Ratings {
    /** 用户ID */
    private int userId;
    /** 电影ID */
    private int movieId;
    /** 评分 */
    private float rating;
    /** 日期 */
    private long timestamp;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}
