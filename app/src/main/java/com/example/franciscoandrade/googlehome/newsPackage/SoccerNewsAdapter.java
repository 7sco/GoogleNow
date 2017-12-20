package com.example.franciscoandrade.googlehome.newsPackage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.franciscoandrade.googlehome.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by C4Q on 12/17/17.
 */

public class SoccerNewsAdapter extends RecyclerView.Adapter<SoccerNewsAdapter.ViewHolderSoccerNews> {

     List<Article> articleList;
    Context context;
    String link;

    public SoccerNewsAdapter(List<Article> articleList, Context context) {
        this.articleList = articleList;
       this.context = context;
    }


    @Override
    public SoccerNewsAdapter.ViewHolderSoccerNews onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.soccer_news_item, parent, false);
        return new SoccerNewsAdapter.ViewHolderSoccerNews(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderSoccerNews holder, int position) {

        holder.newsAuthorSoccer.setText(articleList.get(position).getAuthorSoccer()+"");
        holder.newsSummarySoccer.setText(articleList.get(position).getDescriptionSoccer()+"");
        holder.newsTitleSoccer.setText(articleList.get(position).getTitleSoccer()+"");
        String url= articleList.get(position).getUrlToImageSoccer().toString();
        Picasso.with(holder.newsBackgroundSoccer.getContext()).load(url).fit().into(holder.newsBackgroundSoccer);
        link= articleList.get(position).getUrlSoccer();

//        holder.prictureCardSoccer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent= new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse(link));
//                context.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class ViewHolderSoccerNews extends RecyclerView.ViewHolder {

        private ImageView imageView;
        ImageView newsBackgroundSoccer;
        TextView newsTitleSoccer;
        TextView  newsSummarySoccer;
        TextView  newsAuthorSoccer;
        CardView prictureCardSoccer;
        public ViewHolderSoccerNews(View itemView) {
            super(itemView);
            newsBackgroundSoccer= (ImageView) itemView.findViewById(R.id.newsBackgroundSoccer);
            newsTitleSoccer=(TextView)itemView.findViewById(R.id.newsTitleSoccer);
            newsSummarySoccer=(TextView)itemView.findViewById(R.id.newsDescriptionSoccer);
            newsAuthorSoccer=(TextView)itemView.findViewById(R.id.newsAuthorSoccer);
            prictureCardSoccer=(CardView)itemView.findViewById(R.id.prictureCardSoccer);
        }
    }
}
