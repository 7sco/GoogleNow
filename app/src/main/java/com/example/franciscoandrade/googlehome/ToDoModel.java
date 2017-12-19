package com.example.franciscoandrade.googlehome;

/**
 * Created by Chelsi on 12/17/2017.
 */

public class ToDoModel {

    private String text;

    public ToDoModel(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
