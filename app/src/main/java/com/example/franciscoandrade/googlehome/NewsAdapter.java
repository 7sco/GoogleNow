package com.example.franciscoandrade.googlehome;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolderNews>{

    List<GetArticles> listNews;
    private Context context;
    private String link;

    public NewsAdapter(List<GetArticles> listNews, Context context) {
        this.listNews = listNews;
        this.context=context;
    }

    @Override
    public NewsAdapter.ViewHolderNews onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new NewsAdapter.ViewHolderNews(view);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolderNews holder, int position) {

        holder.newsAuthor.setText(listNews.get(position).getArticles()[position].getAuthor()+"");
        holder.newsSummary.setText(listNews.get(position).getArticles()[position].getDescription()+"");
        holder.newsTitle.setText(listNews.get(position).getArticles()[position].getTitle()+"");
        String url= listNews.get(position).getArticles()[position].getUrlToImage();
        Picasso.with(context).load(url).fit().into(holder.newsBackground);
        link=listNews.get(position).getArticles()[position].getUrl();

        holder.prictureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(link));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNews.size();
    }

    public class ViewHolderNews extends RecyclerView.ViewHolder {
        ImageView newsBackground;
        TextView  newsTitle;
        TextView  newsSummary;
        TextView  newsAuthor;
        CardView prictureCard;
        public ViewHolderNews(View itemView) {
            super(itemView);
            newsBackground= (ImageView) itemView.findViewById(R.id.newsBackground);
            newsTitle=(TextView)itemView.findViewById(R.id.newsTitle);
            newsSummary=(TextView)itemView.findViewById(R.id.newsSummary);
            newsAuthor=(TextView)itemView.findViewById(R.id.newsAuthor);
            prictureCard=(CardView)itemView.findViewById(R.id.prictureCard);
        }
    }
}
