package com.example.franciscoandrade.googlehome.newsPackage;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by C4Q on 12/17/17.
 */

public class SoccerNews {

    @SerializedName("articles")
    public List<Article> articleList;

    public List<Article> getArticleList(){
        return articleList;
    }
}
