package com.example.boundless.commentview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by boundless on 9/26/16.
 */
public class CommentListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> items;

    private final int COMMENT = 0, REPLY = 1;
    public CommentListAdapter(List<Object> items){
        this.items = items;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType){
            case COMMENT:
                View v1 = inflater.inflate(R.layout.comment_view, parent, false);
                viewHolder = new CommentViewHolder(v1);
                break;
            case REPLY:
                View v2 = inflater.inflate(R.layout.reply_view, parent, false);
                viewHolder = new ReplyViewHolder(v2);
                break;
            default:
               View v3 = inflater.inflate(R.layout.default_view, parent, false);
                viewHolder = new DefaultViewHolder(v3);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position, List<Object> payloads) {
        switch (viewHolder.getItemViewType()) {
            case COMMENT:
                CommentViewHolder vh1 = (CommentViewHolder) viewHolder;
                configureCommentViewHolder(vh1, position);
                break;
            case REPLY:
                ReplyViewHolder vh2 = (ReplyViewHolder) viewHolder;
                configureReplyViewHolder(vh2, position);
                break;
            default:
                DefaultViewHolder vh = (DefaultViewHolder) viewHolder;
                configureDefaultViewHolder(vh, position);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof Comment) {
            return COMMENT;
        } else if (items.get(position) instanceof Reply) {
            return REPLY;
        }
        return -1;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public  static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
    private  void configureCommentViewHolder(CommentViewHolder v1, int position){

    }

    private void configureReplyViewHolder(ReplyViewHolder v1, int position){

    }
    private void configureDefaultViewHolder(DefaultViewHolder v1, int position){

    }
}
