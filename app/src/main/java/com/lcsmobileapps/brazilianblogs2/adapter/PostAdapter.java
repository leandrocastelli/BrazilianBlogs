package com.lcsmobileapps.brazilianblogs2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcsmobileapps.brazilianblogs2.R;

/**
 * Created by Leandro on 11/9/2015.
 */
public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    class MyHolder extends RecyclerView.ViewHolder{
        public MyHolder(View v) {
            super(v);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        RecyclerView.ViewHolder vh = new MyHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 30;
    }
}

