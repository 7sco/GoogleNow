package com.example.franciscoandrade.googlehome;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.franciscoandrade.googlehome.weatherPackage.Weatherctivity;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView        recyclerView;
    Context             context=this;
    List<GetArticles>   listData=new ArrayList<>();
    EditText            searchET;
    FloatingActionButton buttton_floatWeather, buttton_floatTodo;




    SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //swipeRefresh= (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);

        recyclerView    =(RecyclerView)findViewById(R.id.recyclerHomeNews);
        searchET        =(EditText)findViewById(R.id.searchET);
        new Peticion().execute();
        timer();
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        keyTextListener();


//        git status.setOnRefreshListener(this);

        buttton_floatWeather= (FloatingActionButton)findViewById(R.id.buttton_floatWeather);
        buttton_floatTodo= (FloatingActionButton)findViewById(R.id.buttton_floatTodo);


        buttton_floatWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Weatherctivity.class);
                startActivity(intent);
            }
        });

        buttton_floatTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), ToDoList.class);
                startActivity(intent);
            }
        });


    }

    private void timer() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                Log.d("RESULTS", "AFTER THREAD ====="+listData.size());
                NewsAdapter newsAdapter =new NewsAdapter(listData, context);
                recyclerView.setAdapter(newsAdapter);
            }
        }, 1000);
    }

    private void keyTextListener() {
        searchET.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction()== keyEvent.ACTION_DOWN){

                    if(i== keyEvent.KEYCODE_ENTER && !TextUtils.isEmpty(searchET.getText())){

                        String url= searchET.getText().toString();
                        Intent intent= new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("https://www.google.com/#q="+url));
                        startActivity(intent);
                        searchET.setText("");
                    }
                }

                return false;
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()){

            case R.id.imageGoogle:
                Intent intentImage= new Intent(Intent.ACTION_VIEW);
                intentImage.setData(Uri.parse("https://www.google.com"));
                startActivity(intentImage);
                break;

        }
    }

    @Override
    public void onRefresh() {

        Toast.makeText(this, "REFRESH", Toast.LENGTH_SHORT).show();







    }

    public  class Peticion extends AsyncTask<Void, String, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            final String  url="https://newsapi.org/v2/";

            Retrofit retrofit= new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ServiceAPI service= retrofit.create(ServiceAPI.class);
            Call<GetArticles> response= service.getResponseGet();

            response.enqueue(new Callback<GetArticles>() {
                @Override
                public void onResponse(Call<GetArticles> call, Response<GetArticles> response) {

                    GetArticles getArticles= response.body();
                    for(int i=0; i< getArticles.getArticles().length; i++){

                        listData.add(getArticles);
                    }
                    //Log.d("RESPONSE======", "onResponse: "+ listData.get(0));
                }
                @Override
                public void onFailure(Call<GetArticles> call, Throwable t) {

                    Log.d("FAIL=======", "onFailure: ");
                }
            });
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
        }

        @Override
        protected void onPostExecute(Void aVoid) {


        }
    }
}
