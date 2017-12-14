package com.example.franciscoandrade.googlehome;

/**
 * Created by franciscoandrade on 12/12/17.
 */

class GetArticles {

    private Articles[] articles;

    private String status;

    public Articles[] getArticles ()
    {
        return articles;
    }

    public void setArticles (Articles[] articles)
    {
        this.articles = articles;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [articles = "+articles+", status = "+status+"]";
    }

}
