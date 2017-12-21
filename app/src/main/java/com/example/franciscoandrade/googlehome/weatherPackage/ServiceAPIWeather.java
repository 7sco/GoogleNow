package com.example.franciscoandrade.googlehome.weatherPackage;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceAPIWeather {

//    @GET("40.641171,-74.01329")
//    Call<ResponseService> getResponseGet();

    @GET("40.641171,-74.01329")
    Call<GetCurrently> getResponseGet();

//    @POST("40.641171,-74.01329")
//    Call<GetCurrently> getResponsePost();

}
