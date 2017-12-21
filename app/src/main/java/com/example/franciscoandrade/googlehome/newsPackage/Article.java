package com.example.franciscoandrade.googlehome.newsPackage;

import com.google.gson.annotations.SerializedName;

/**
 * Created by C4Q on 12/17/17.
 */

public class Article {

    @SerializedName("source")
    public Source sourceSoccer;

    @SerializedName("author")
    public String authorSoccer;

    @SerializedName("title")
    public String titleSoccer;

    @SerializedName("description")
    public String descriptionSoccer;

    @SerializedName("url")
    public String urlSoccer;

    @SerializedName("urlToImage")
    public String urlToImageSoccer;

    @SerializedName("published")
    public String publishedAtSoccer;

    public Source getSourceSoccer() {
        return sourceSoccer;
    }

    public String getAuthorSoccer() {
        return authorSoccer;
    }

    public String getTitleSoccer() {
        return titleSoccer;
    }

    public String getDescriptionSoccer() {
        return descriptionSoccer;
    }

    public String getUrlSoccer() {
        return urlSoccer;
    }

    public String getUrlToImageSoccer() {
        return urlToImageSoccer;
    }

    public String getPublishedAtSoccer() {
        return publishedAtSoccer;
    }


}
