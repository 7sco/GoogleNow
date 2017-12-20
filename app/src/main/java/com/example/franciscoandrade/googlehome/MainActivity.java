package com.example.franciscoandrade.googlehome;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
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

import com.example.franciscoandrade.googlehome.newsPackage.Article;
import com.example.franciscoandrade.googlehome.newsPackage.SoccerAPI;
import com.example.franciscoandrade.googlehome.newsPackage.SoccerNews;
import com.example.franciscoandrade.googlehome.newsPackage.SoccerNewsAdapter;
import com.example.franciscoandrade.googlehome.weatherPackage.Weatherctivity;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//DELETE THIS COMMENT, ITS ONLY FOR TESTING

public class MainActivity extends AppCompatActivity {

    RecyclerView        recyclerView;
    Context             context=this;
    List<GetArticles>   listData=new ArrayList<>();
    EditText            searchET;
    FloatingActionButton buttton_floatWeather;

    private static final String TAG = "JSON?";

    private static final String  url= "https://newsapi.org/v2/";


     List<Article> sportNewsArticlesList = new ArrayList<>();
     RecyclerView recyclerView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView    =(RecyclerView)findViewById(R.id.recyclerHomeNews);
        recyclerView2    =(RecyclerView)findViewById(R.id.recyclerSportsNews);

        searchET        =(EditText)findViewById(R.id.searchET);
        new Peticion().execute();
        new RetrofitCall().execute();
        timer();
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        LinearLayoutManager linearLayoutManager2= new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(linearLayoutManager2);


        keyTextListener();//where is setadapter?



        //recyclerView2 = (RecyclerView) findViewById(R.id.recyclerSportsNews);
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

                //SIRAN

//                recyclerView2.setHasFixedSize(true);
                SoccerNewsAdapter soccerNewsAdapter= new SoccerNewsAdapter(sportNewsArticlesList, context);
                recyclerView2.setAdapter(soccerNewsAdapter);//setadapter



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
            case R.id.buttton_floatWeather:
                Intent intent=new Intent(this, Weatherctivity.class);
                startActivity(intent);
                break;
            case R.id.buttton_floatTodo:
                Toast.makeText(context, "NOT YET IMPLEMENTED", Toast.LENGTH_SHORT).show();
                break;

            case R.id.imageGoogle:
                Intent intentImage= new Intent(Intent.ACTION_VIEW);
                intentImage.setData(Uri.parse("https://www.google.com"));
                startActivity(intentImage);
                break;



        }
    }

    public class RetrofitCall extends AsyncTask<Void, String, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            Log.d(TAG, "doInBackground: BACKGROUNDTHREAD");

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            SoccerAPI sportsNewsService= retrofit.create(SoccerAPI.class);
            sportsNewsService = retrofit.create(SoccerAPI.class);

            Call<SoccerNews> soccerNewsCall = sportsNewsService.getNews();

            soccerNewsCall.enqueue(new Callback<SoccerNews>() {
                @Override
                public void onResponse(Call<SoccerNews> call, Response<SoccerNews> response) {
                    Log.d(TAG, "onResponse: ======");

                    response.body().getArticleList();

                    SoccerNews soccerNews= response.body();


                    for(int i=0; i< soccerNews.getArticleList().size(); i++){

                        sportNewsArticlesList.add(soccerNews.getArticleList().get(i));
                    }

                    Log.d(TAG, "onResponse: "+sportNewsArticlesList.get(0).getUrlToImageSoccer());

                }

                @Override
                public void onFailure(Call<SoccerNews> call, Throwable t) {

                    Log.d(TAG, "onFailure: ========");
                }
            });

            return null;
        }
    }

    public  class Peticion extends AsyncTask<Void, String, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

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
