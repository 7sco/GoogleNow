package com.example.franciscoandrade.googlehome.weatherPackage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.franciscoandrade.googlehome.R;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by franciscoandrade on 12/11/17.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolderWeather> {

    List<Data> listData;

    public WeatherAdapter(List<Data> listData) {
        this.listData = listData;
    }

    @Override
    public ViewHolderWeather onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);


        return new ViewHolderWeather(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderWeather holder, int position) {
        DateFormat ddf3= DateFormat.getTimeInstance(DateFormat.SHORT, Locale.ENGLISH);
        ddf3.setTimeZone(TimeZone.getTimeZone("EST"));
        Date date2;
        long unixSeconds2;
        unixSeconds2= listData.get(position).getTime();
        date2= new Date(unixSeconds2*1000L);
        String time=ddf3.format(date2);
        holder.timeRV.setText(time);
        holder.tempRV.setText(String.valueOf((int)listData.get(position).getTemperature())+"\u00b0");
        holder.feelsTempRV.setText(String.valueOf((int)listData.get(position).getApparentTemperature())+"\u00b0");
        holder.summaryRV.setText(listData.get(position).getSummary()+"");

        switch (listData.get(position).getIcon()){
            case "clear-day":
                holder.iconRV.setBackgroundResource(R.drawable.weather_clear);
                break;
            case "clear-night":
                holder.iconRV.setBackgroundResource(R.drawable.weather_clear_night);
                break;
            case "rain":
                holder.iconRV.setBackgroundResource(R.drawable.weather_rain_day);
                break;
            case "snow":
                holder.iconRV.setBackgroundResource(R.drawable.weather_snow);
                break;
            case "sleet":
                holder.iconRV.setBackgroundResource(R.drawable.weather_hail);
                break;
            case "wind":
                holder.iconRV.setBackgroundResource(R.drawable.weather_wind);
                break;
            case "fog":
                holder.iconRV.setBackgroundResource(R.drawable.weather_fog);
                break;
            case "cloudy":
                holder.iconRV.setBackgroundResource(R.drawable.weather_clouds);
                break;
            case "partly-cloudy-day":
                holder.iconRV.setBackgroundResource(R.drawable.weather_few_clouds);
                break;
            case "partly-cloudy-night":
                holder.iconRV.setBackgroundResource(R.drawable.weather_few_clouds_night);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolderWeather extends RecyclerView.ViewHolder {

        TextView timeRV, tempRV, feelsTempRV, summaryRV;
        ImageView iconRV;

        public ViewHolderWeather(View itemView) {
            super(itemView);

            timeRV=(TextView)itemView.findViewById(R.id.timeRV);
            tempRV=(TextView)itemView.findViewById(R.id.tempRV);
            feelsTempRV=(TextView)itemView.findViewById(R.id.feelsTempRV);
            summaryRV=(TextView)itemView.findViewById(R.id.summaryRV);
            iconRV=(ImageView) itemView.findViewById(R.id.iconRV);

        }
    }
}
