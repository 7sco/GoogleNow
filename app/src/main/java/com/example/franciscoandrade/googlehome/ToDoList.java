package com.example.franciscoandrade.googlehome;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by Chelsi on 12/17/2017.
 */

public class ToDoList extends AppCompatActivity {

    EditText task;
    Button taskButton;
    RecyclerView recyclerView;
    ArrayList<String> strings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todolist_view);

        task = findViewById(R.id.toDoEditText);
        taskButton = findViewById(R.id.saveToDo);
        recyclerView = findViewById(R.id.recyclerViewToDo);
        strings = new ArrayList<>();
//        integers.add(9);
//        integers.add(3);
//        integers.add(10);
        /*
        * if(taskButton.OnclickLiterner){
        * String value = task.getText().toString();
        * strings.add(value);
        * }
        *
        * */


        ToDoAdapter adapter = new ToDoAdapter(strings);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);


    }

}

