package com.suyuening.elasticsearch.movielens.domain;

public class Tags {
    /** 用户ID */
    private int userId;
    /** 电影ID */
    private int movieId;
    /** 标签 */
    private String tag;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}
