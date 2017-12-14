package com.example.franciscoandrade.googlehome.weatherPackage;

/**
 * Created by franciscoandrade on 12/11/17.
 */

public class HourlyModel {

    private String time;
    private String temperature;
    private String feelsTemperature;
    private String summary;
    private String icon;


    public HourlyModel(String time, String temperature, String feelsTemperature, String summary, String icon) {
        this.time = time;
        this.temperature = temperature;
        this.feelsTemperature = feelsTemperature;
        this.summary = summary;
        this.icon = icon;
    }

    public String getTime() {
        return time;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getFeelsTemperature() {
        return feelsTemperature;
    }

    public String getSummary() {
        return summary;
    }

    public String getIcon() {
        return icon;
    }
}
