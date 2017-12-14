package com.example.franciscoandrade.googlehome.weatherPackage;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by franciscoandrade on 12/11/17.
 */

public interface ServiceAPI {

//    @GET("40.641171,-74.01329")
//    Call<ResponseService> getResponseGet();

        @GET("40.641171,-74.01329")
        Call<GetCurrently> getResponseGet();


    @POST("40.641171,-74.01329")
    Call<GetCurrently> getResponsePost();

}
