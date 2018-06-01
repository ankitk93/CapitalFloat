package com.ankit.assignment.capitalfloat.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllNews {

    @SerializedName("status")
    String status;

    @SerializedName("totalResults")
    int totalResults;

    @SerializedName("articles")
    List<SingleArticle> newsArticleList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<SingleArticle> getNewsArticleList() {
        return newsArticleList;
    }

    public void setNewsArticleList(List<SingleArticle> newsArticleList) {
        this.newsArticleList = newsArticleList;
    }
}
