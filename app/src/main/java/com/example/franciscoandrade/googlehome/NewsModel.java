package com.example.franciscoandrade.googlehome;

/**
 * Created by franciscoandrade on 12/12/17.
 */

public class NewsModel {

    private String title;
    private String summary;
    private String author;
    private String url;
    private String urlImage;


    public NewsModel(String title, String summary, String author, String url, String urlImage) {
        this.title = title;
        this.summary = summary;
        this.author = author;
        this.url = url;
        this.urlImage = urlImage;
    }


    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlImage() {
        return urlImage;
    }
}
