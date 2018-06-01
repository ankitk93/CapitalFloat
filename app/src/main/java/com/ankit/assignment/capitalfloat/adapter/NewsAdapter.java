package com.ankit.assignment.capitalfloat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ankit.assignment.capitalfloat.R;
import com.ankit.assignment.capitalfloat.model.SingleArticle;
import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<SingleArticle> mArticleList;
    private Context mContext;

    public NewsAdapter(Context context , List<SingleArticle> articleList){
        mContext = context;
        mArticleList = articleList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.headlines_item , parent , false);
        return new NewsViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.mTitleTextView.setText(mArticleList.get(position).getTitle());
        holder.mSourceTextView.setText(mArticleList.get(position).getSourceInfo().getName());
        holder.mDescTextView.setText(mArticleList.get(position).getDescription());

        Glide.with(mContext.getApplicationContext())
                .load(mArticleList.get(position).getUrlToImage())
                .into(holder.mHeadlineImageView);
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder{

        TextView mSourceTextView , mTitleTextView , mDescTextView;
        CardView mHeadlineCardView;
        ImageView mHeadlineImageView;

        public NewsViewHolder(View view){
            super(view);

            mSourceTextView = view.findViewById(R.id.tv_headline_source);
            mTitleTextView = view.findViewById(R.id.tv_headline_title);
            mDescTextView = view.findViewById(R.id.tv_headline_desc);
            mHeadlineCardView = view.findViewById(R.id.headline_card_view);
            mHeadlineImageView = view.findViewById(R.id.iv_news);

            mHeadlineCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mArticleList.size();
    }
}
