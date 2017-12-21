package com.example.franciscoandrade.googlehome.todoPackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.franciscoandrade.googlehome.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ToDoActivity extends AppCompatActivity {

    FloatingActionButton createNote;
    List<ModelClass> listNotes;
    AdapterNotes adapterNotes;
    EditText noteET, titleET;
    private int red, green, blue;
    private final static String KEY = "tasks";
    private final static String KEY_SHAREDPREF = "sharedpreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        noteET = (EditText) findViewById(R.id.noteET);
        titleET = (EditText) findViewById(R.id.titleET);
        createNote = (FloatingActionButton) findViewById(R.id.createNote);
        loadData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerToDo);
        adapterNotes = new AdapterNotes(listNotes, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterNotes);
        recyclerView.setLayoutManager(linearLayoutManager);
// Create an `ItemTouchHelper` and attach it to the `RecyclerView`
        ItemTouchHelper.Callback callback = new SwipeHelper(adapterNotes);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);
        //Hide keyboard on done
//        noteET.setOnEditorActionListener(new DoneOnEditorActionListener());
//        titleET.setOnEditorActionListener(new DoneOnEditorActionListener());
    }

    private void randomColor() {
        red = generateRandom();
        green = generateRandom();
        blue = generateRandom();
    }

    private int generateRandom() {
        return new Random().nextInt(256);
    }

    public void onClick(View view) {

        if (!TextUtils.isEmpty(noteET.getText().toString()) && !TextUtils.isEmpty(titleET.getText().toString())) {

            randomColor();
            listNotes.add(0, new ModelClass(noteET.getText().toString(), titleET.getText().toString(), red, green, blue));
            adapterNotes.notifyItemInserted(listNotes.size() - 1);
            adapterNotes.notifyDataSetChanged();
            noteET.setText("");
            titleET.setText("");


            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(noteET.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(titleET.getWindowToken(), 0);


        }
        //If input is empty show a Toast
        else {
            Toast.makeText(this, "Empty Fields", Toast.LENGTH_SHORT).show();
        }

    }

    private void saveData() {
        Log.d("SAVING", "onDestroy: saveData called");
        SharedPreferences sharedPreferences = getSharedPreferences(KEY_SHAREDPREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(listNotes);
        editor.putString(KEY, json);
        editor.commit();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(KEY_SHAREDPREF, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY, null);
        Type type = new TypeToken<ArrayList<ModelClass>>() {
        }.getType();
        listNotes = gson.fromJson(json, type);
        if (listNotes == null) {
            listNotes = new ArrayList<>();
        }
    }

    @Override
    protected void onStop() {
        saveData();
        super.onStop();
    }

    class DoneOnEditorActionListener implements TextView.OnEditorActionListener {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            return true;

        }
    }

}
