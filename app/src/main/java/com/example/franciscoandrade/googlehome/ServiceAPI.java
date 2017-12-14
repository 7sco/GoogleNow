package com.example.franciscoandrade.googlehome;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by franciscoandrade on 12/12/17.
 */

public interface ServiceAPI {

    @GET("everything?sources=the-verge&apiKey=bb9882ead46d4fb4a8c5c158a3754d85")
    Call<GetArticles> getResponseGet();

}
