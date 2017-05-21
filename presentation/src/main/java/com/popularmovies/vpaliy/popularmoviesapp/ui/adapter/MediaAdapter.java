package com.popularmovies.vpaliy.popularmoviesapp.ui.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.popularmovies.vpaliy.domain.model.MediaCover;
import com.popularmovies.vpaliy.popularmoviesapp.R;
import com.popularmovies.vpaliy.popularmoviesapp.ui.eventBus.RxBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.MediaViewHolder>{

    private List<MediaCover> data;
    private LayoutInflater inflater;
    private RxBus rxBus;
    private boolean hasBeenClicked;

    public MediaAdapter(@NonNull Context context,
                        @NonNull RxBus rxBus){
        this.inflater=LayoutInflater.from(context);
        this.rxBus=rxBus;
        this.data=new ArrayList<>();
    }

    public void setData(List<MediaCover> data) {
        this.data = data;
    }

    public class MediaViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.media_release_year)
        TextView releaseYear;

        @BindView(R.id.media_poster)
        ImageView posterImage;

        @BindView(R.id.media_rating)
        TextView ratings;

        @BindView(R.id.media_title)
        TextView mediaTitle;

        public MediaViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void onBindData(){

        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public MediaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MediaViewHolder holder, int position) {
        holder.onBindData();
    }
}