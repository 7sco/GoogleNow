package com.example.franciscoandrade.googlehome.weatherPackage;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.franciscoandrade.googlehome.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Weatherctivity extends AppCompatActivity {

    TextView summaryNow, currentDate, currentTemp, feelsTemp;
    TextView summaryHour;
    List<Data> listData = new ArrayList<>();
    ImageView iconIV;
    RecyclerView recyclerView;
    WeatherAdapter weatherAdapter;

    String currentWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weatherctivity);
//Layout
        summaryNow = (TextView) findViewById(R.id.summaryNow);
        currentDate = (TextView) findViewById(R.id.currentDate);
        currentTemp = (TextView) findViewById(R.id.currentTemp);
        feelsTemp = (TextView) findViewById(R.id.feelsTemp);
        summaryHour = (TextView) findViewById(R.id.summaryHour);
        iconIV = (ImageView) findViewById(R.id.iconIV);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewContainer);
        //ASYNCTASK Conection
        Peticion peticion = new Peticion();
        peticion.execute();
        //Timer
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms

                Log.d("RESULTS", "AFTER THREAD =====" + listData.size());
                weatherAdapter = new WeatherAdapter(listData);

                recyclerView.setAdapter(weatherAdapter);
                Log.d("SIZE =====", listData.size()+"");

            }
        }, 1000);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    private void weatherIcon(String value) {
        switch (value) {
            case "clear-day":
                iconIV.setBackgroundResource(R.drawable.weather_clear);
                break;
            case "clear-night":
                iconIV.setBackgroundResource(R.drawable.weather_clear_night);
                break;
            case "rain":
                iconIV.setBackgroundResource(R.drawable.weather_rain_day);
                break;
            case "snow":
                iconIV.setBackgroundResource(R.drawable.weather_snow);
                break;
            case "sleet":
                iconIV.setBackgroundResource(R.drawable.weather_hail);
                break;
            case "wind":
                iconIV.setBackgroundResource(R.drawable.weather_wind);
                break;
            case "fog":
                iconIV.setBackgroundResource(R.drawable.weather_fog);
                break;
            case "cloudy":
                iconIV.setBackgroundResource(R.drawable.weather_clouds);
                break;
            case "partly-cloudy-day":
                iconIV.setBackgroundResource(R.drawable.weather_few_clouds);
                break;
            case "partly-cloudy-night":
                iconIV.setBackgroundResource(R.drawable.weather_few_clouds_night);
                break;
        }
    }

    public class Peticion extends AsyncTask<Void, String, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            final String url = "https://api.darksky.net/forecast/1b839889901b88e5b5e02c421ab7d3ef/";
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ServiceAPI service = retrofit.create(ServiceAPI.class);
            Call<GetCurrently> response = service.getResponseGet();

            response.enqueue(new Callback<GetCurrently>() {
                @Override
                public void onResponse(Call<GetCurrently> call, Response<GetCurrently> response) {

                    long unixSeconds = response.body().getCurrently().getTime();
                    Date date = new Date(unixSeconds * 1000L);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
                    sdf.setTimeZone(TimeZone.getTimeZone("EST"));
                    String formattedDate = sdf.format(date);
                    DateFormat ddf2 = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.ENGLISH);
                    ddf2.setTimeZone(TimeZone.getTimeZone("EST"));
                    String format3 = ddf2.format(date);
                    Currently currently = response.body().getCurrently();
                    String summaryNow = currently.getSummary();
                    String currentDate = format3;
                    String currentTemp = String.valueOf((int) currently.getTemperature());
                    String feelsTemp = String.valueOf((int) currently.getApparentTemperature());
                    Hourly hourly = response.body().getHourly();
                    DateFormat ddf3 = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.ENGLISH);
                    ddf3.setTimeZone(TimeZone.getTimeZone("EST"));
                    Date date2;
                    long unixSeconds2;
                    for (int i = 0; i < hourly.getData().length; i++) {

                        unixSeconds2 = response.body().getHourly().getData()[i].getTime();
                        date2 = new Date(unixSeconds2 * 1000L);
                        String format4 = ddf3.format(date2);
                        listData.add(response.body().getHourly().getData()[i]);
                    }
                    Log.d("SIZE =====", listData.size()+"");
                    publishProgress(currently.getIcon().toString(), hourly.getSummary(), summaryNow, currentDate, currentTemp, feelsTemp);
                }

                @Override
                public void onFailure(Call<GetCurrently> call, Throwable t) {
                    Log.d("RESULTS =====", "FAIL+++++++");
                }
            });

            return null;
        }


        @Override
        protected void onProgressUpdate(String... values) {
            weatherIcon(values[0]);
            summaryHour.setText(values[1] + "");
            summaryNow.setText(values[2]);
            currentDate.setText(values[3]);
            currentTemp.setText(values[4] + "\u00b0");
            feelsTemp.setText(values[5] + "\u00b0");

            setCurrentWeather(values[4] + "\u00b0C");


        }

        @Override
        protected void onPostExecute(Void aVoid) {
        }
    }


    public String getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(String currentWeather) {
        this.currentWeather = currentWeather;
    }


}
