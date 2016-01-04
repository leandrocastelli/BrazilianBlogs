package com.lcsmobileapps.brazilianblogs2.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.lcsmobileapps.brazilianblogs2.R;
import com.lcsmobileapps.brazilianblogs2.controller.ControllerData;
import com.lcsmobileapps.brazilianblogs2.controller.ControllerVolley;
import com.lcsmobileapps.brazilianblogs2.model.Post;

import java.util.List;

/**
 * Created by Leandro on 11/9/2015.
 */
public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private String blog;
    private Context context;
    private List<Post> mDataSet;

    public PostAdapter(String blog, Context context) {
        this.blog = blog;
        this.context = context;

        mDataSet = ControllerData.getInstance().getPosts(blog, context);
    }

    class MyHolder extends RecyclerView.ViewHolder{
        public MyHolder(View v) {
            super(v);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_networking,parent,false);
        RecyclerView.ViewHolder vh = new MyHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView postTitle = (TextView)holder.itemView.findViewById(R.id.post_title);
        TextView postDesc = (TextView)holder.itemView.findViewById(R.id.post_text);
        ViewGroup cardView = (RelativeLayout)holder.itemView.findViewById(R.id.relative_layout_item);

        final Post current = mDataSet.get(position);
        postTitle.setText(current.getTitle());
        postDesc.setText(current.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = current.getPostUrl();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = current.getPostUrl();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        });

        NetworkImageView imgView = (NetworkImageView)holder.itemView.findViewById(R.id.post_image);
       // imgView.setImageUrl(current.getImagePath(), ControllerVolley.getInstance().getImageLoader());
        imgView.setImageUrl(current.getImagePath(), ControllerVolley.getInstance().getImageLoader());
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}

