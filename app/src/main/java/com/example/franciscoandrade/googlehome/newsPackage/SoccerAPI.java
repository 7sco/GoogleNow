package com.example.franciscoandrade.googlehome.newsPackage;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by C4Q on 12/17/17.
 */

public interface SoccerAPI {
    @GET("top-headlines?sources=the-sport-bible&apiKey=e267d9189dee4f41a1243eb98b33933b")
    Call<SoccerNews> getNews();
}
