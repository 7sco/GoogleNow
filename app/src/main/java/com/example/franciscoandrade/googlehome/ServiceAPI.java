package com.example.franciscoandrade.googlehome;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceAPI {

    @GET("top-headlines?sources=google-news&apiKey=e267d9189dee4f41a1243eb98b33933b")
    Call<GetArticles> getResponseGet();
}
