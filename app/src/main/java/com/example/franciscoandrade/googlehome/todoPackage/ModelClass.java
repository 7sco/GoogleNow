package com.example.franciscoandrade.googlehome.todoPackage;

public class ModelClass {

    private String title;
    private String text;
    private int red, green, blue;

    public ModelClass(String text, String title, int red, int green, int blue) {
        this.title = title;
        this.text = text;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }
}

