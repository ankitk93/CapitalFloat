package com.ankit.assignment.capitalfloat.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ankit.assignment.capitalfloat.R;
import com.ankit.assignment.capitalfloat.activity.DetailsActivity;
import com.ankit.assignment.capitalfloat.activity.MainActivity;
import com.ankit.assignment.capitalfloat.model.SingleArticle;
import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<SingleArticle> mArticleList;
    private Context mContext;

    String newsUrl , descText;

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
        holder.mSourceTextView.setText(mArticleList.get(position).getAuthor());
        holder.mRemoveItemTextView.setText("REMOVE");

        Glide.with(mContext.getApplicationContext())
                .load(mArticleList.get(position).getUrlToImage())
                .thumbnail(0.2f)
                .into(holder.mHeadlineImageView);
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder{

        TextView mSourceTextView , mTitleTextView, mRemoveItemTextView ;
        CardView mHeadlineCardView;
        ImageView mHeadlineImageView;

        public NewsViewHolder(View view){
            super(view);

            mSourceTextView = view.findViewById(R.id.tv_headline_source);
            mTitleTextView = view.findViewById(R.id.tv_headline_title);
            mHeadlineCardView = view.findViewById(R.id.headline_card_view);
            mHeadlineImageView = view.findViewById(R.id.iv_news);
            mRemoveItemTextView = view.findViewById(R.id.tv_remove_item);

            mHeadlineCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newsUrl = mArticleList.get(getAdapterPosition()).getUrlToImage();
                    descText = mArticleList.get(getAdapterPosition()).getDescription();
                    /*CustomTabsIntent.Builder detailsTab = new CustomTabsIntent.Builder();
                    detailsTab.addDefaultShareMenuItem();
                    detailsTab.setCloseButtonIcon(BitmapFactory.decodeResource(mContext.getResources(),
                            R.mipmap.ic_arrow_back_white_24dp));
                    detailsTab.setToolbarColor(mContext.getResources().getColor(R.color.colorPrimary));
                    CustomTabsIntent customTabsIntent = detailsTab.build();
                    customTabsIntent.launchUrl(mContext, Uri.parse(newsUrl));*/

                    Intent detailsIntent = new Intent(mContext , DetailsActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putString("imageUrl",newsUrl);
                    mBundle.putString("newsDesc" , mArticleList.get(getAdapterPosition()).getDescription());
                    mBundle.putString("newsTitle" , mArticleList.get(getAdapterPosition()).getTitle());
                    mBundle.putString("newsAuthor",mArticleList.get(getAdapterPosition()).getAuthor());
                    detailsIntent.putExtras(mBundle);
                    mContext.startActivity(detailsIntent);
                }
            });

            mRemoveItemTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeNewsItem(getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mArticleList.size();
    }

    public void removeNewsItem(int position){
        mArticleList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position , mArticleList.size());
    }
}
