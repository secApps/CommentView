package com.example.boundless.commentview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by boundless on 9/27/16.
 */
public class ReplyViewHolder extends RecyclerView.ViewHolder{
TextView body;
    ImageView expand;
    public ReplyViewHolder(View itemView) {
        super(itemView);
        body = (TextView)itemView.findViewById(R.id.body);
        expand =(ImageView)itemView.findViewById(R.id.expand1);
    }
}
