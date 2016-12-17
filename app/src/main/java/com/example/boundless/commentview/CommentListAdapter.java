package com.example.boundless.commentview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by boundless on 9/26/16.
 */
public class CommentListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> items;
    private List<Object> backupItems;

    private final int COMMENT = 0, REPLY = 1,SUB_REPLY=2;
    public CommentListAdapter(List<Object> items){
        this.items=new ArrayList<Object>();
        this.items.addAll(items);
        this.backupItems= new ArrayList<Object>();
        this.backupItems.addAll(items);

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
            case SUB_REPLY:
                View v3 = inflater.inflate(R.layout.default_view, parent, false);
                viewHolder = new DefaultViewHolder(v3);
                break;
            default:
               View v4 = inflater.inflate(R.layout.default_view, parent, false);
                viewHolder = new DefaultViewHolder(v4);
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
            case SUB_REPLY:
                DefaultViewHolder vh3 = (DefaultViewHolder) viewHolder;
                configureDefaultViewHolder(vh3, position);
                break;
            default:
                DefaultViewHolder vh4 = (DefaultViewHolder) viewHolder;
                configureDefaultViewHolder(vh4, position);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof CommentWithReply) {
            return COMMENT;
        } else if (items.get(position) instanceof ReplyWithChild) {
            return REPLY;
        }else if(items.get(position) instanceof SubChild){
            return SUB_REPLY;
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
    private  void configureCommentViewHolder(CommentViewHolder v1, final int position){
      v1.body.setText ( ((CommentWithReply)items.get(position)).name);
        if(((CommentWithReply)items.get(position)).isExpanded)
        v1.expand.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24px);
        else
            v1.expand.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24px);
         v1.body.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(items.get(position) instanceof CommentWithReply)
                if(((CommentWithReply)items.get(position)).reply.size()>0){
//                    for(int i=0;i<((CommentWithReply)items.get(position)).reply.size();i++){
//                        items.add(((CommentWithReply)items.get(position)).reply.get(i));
//                    }
                    if(!((CommentWithReply)items.get(position)).isExpanded) {
                        items.addAll(position + 1, ((CommentWithReply) items.get(position)).reply);
                        notifyItemRangeInserted(position + 1, ((CommentWithReply) items.get(position)).reply.size());
                        ((CommentWithReply)items.get(position)).setExpanded(true);
                    }else{
                        items.removeAll(((CommentWithReply) items.get(position)).reply);
                        for(int i=0;i<((CommentWithReply) items.get(position)).reply.size();i++){
                            items.removeAll(((ReplyWithChild) ((CommentWithReply) items.get(position)).reply.get(i)).subChildList);
                        }

                        notifyItemRangeRemoved(position+1,((CommentWithReply)items.get(position)).reply.size());
                        ((CommentWithReply)items.get(position)).setExpanded(false);
                    }
                    notifyDataSetChanged();
                }
             }
         });

    }

    private void configureReplyViewHolder(ReplyViewHolder v1,final int position){
        v1.body.setText ( ((ReplyWithChild)items.get(position)).name);
        if(((ReplyWithChild)items.get(position)).isExpanded)
            v1.expand.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24px);
        else
            v1.expand.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24px);
        v1.body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(items.get(position) instanceof ReplyWithChild)
                if(((ReplyWithChild)items.get(position)).subChildList.size()>0){
//                    for(int i=0;i<((ReplyWithChild)items.get(position)).subChildList.size();i++){
//                        items.add(((ReplyWithChild)items.get(position)).subChildList.get(i));
//                    }
                    if(!((ReplyWithChild)items.get(position)).isExpanded) {
                        items.addAll(position + 1, ((ReplyWithChild) items.get(position)).subChildList);
                        ((ReplyWithChild)items.get(position)).setExpanded(true);
                        notifyItemRangeInserted(position+1,((ReplyWithChild)items.get(position)).subChildList.size());
                    }else{
                        items.removeAll(((ReplyWithChild) items.get(position)).subChildList);
                        notifyItemRangeRemoved(position+1,((ReplyWithChild)items.get(position)).subChildList.size());
                        ((ReplyWithChild)items.get(position)).setExpanded(false);
                    }

                    notifyDataSetChanged();
                }
            }
        });

    }
    private void configureDefaultViewHolder(DefaultViewHolder v1,final int position){
        v1.mTitle.setText ( ((SubChild)items.get(position)).name);
        v1.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    ((SubChild) items.get(position)).setChecked(true);
                else
                    ((SubChild) items.get(position)).setChecked(false);
            }
        });
        if(items.get(position) instanceof SubChild) {
            if (((SubChild) items.get(position)).isChecked)
                v1.mCheckBox.setChecked(true);
            else
                v1.mCheckBox.setChecked(false);


        }

    }

    public void search(String query){
        query=query.toLowerCase();
        if(query.length()>0) {
            Log.d("Entered","length"+Integer.toString(backupItems.size()));
            items.clear();
            Log.d("Entered","length"+Integer.toString(backupItems.size()));
            for (int i = 0; i < backupItems.size(); i++) {
                Log.d("name",">>"+((CommentWithReply)backupItems.get(i)).name.toLowerCase());
                if(((CommentWithReply)backupItems.get(i)).name.toLowerCase().contains(query)) {
                    items.add(backupItems.get(i));
                    Log.d("Entered","added header");
                }
                for(int j=0;j<((CommentWithReply)backupItems.get(i)).reply.size();j++){
                    if(((ReplyWithChild)((CommentWithReply) backupItems.get(i)).reply.get(j)).name.toLowerCase().contains(query)) {

                        if(!items.contains(backupItems.get(i))){
                            items.add(backupItems.get(i));
                        }
                        if(items.get(i) instanceof  CommentWithReply)
                        ((CommentWithReply)items.get(i)).setExpanded(true);

                        items.add(((ReplyWithChild) ((CommentWithReply) backupItems.get(i)).reply.get(j)));
                        Log.d("Entered","added second header");

                    }

                    for(int k=0;k<((ReplyWithChild) ((CommentWithReply) backupItems.get(i)).reply.get(j)).subChildList.size();k++){
                       if(((ReplyWithChild) ((CommentWithReply) backupItems.get(i)).reply.get(j)).subChildList.get(k).name.toLowerCase().contains(query)){
                           if(!items.contains(backupItems.get(i))){
                               items.add(backupItems.get(i));
                               ((CommentWithReply)items.get(items.lastIndexOf(backupItems.get(i)))).setExpanded(true);
                           }
                           if(!items.contains(((ReplyWithChild) ((CommentWithReply) backupItems.get(i)).reply.get(j)))){
                               items.add(((ReplyWithChild) ((CommentWithReply) backupItems.get(i)).reply.get(j)));
                               ((ReplyWithChild) items.get(items.lastIndexOf(((ReplyWithChild) ((CommentWithReply) backupItems.get(i)).reply.get(j))))).setExpanded(true);
                           }
                           Log.d("Entered","added third header");
                           items.add(((ReplyWithChild) ((CommentWithReply) backupItems.get(i)).reply.get(j)).subChildList.get(k));
//                           ((CommentWithReply)items.get(i)).setExpanded(true);



                       }

                    }

                }
                Log.d("Entered","notified"+Integer.toString(items.size()));
             notifyDataSetChanged();
            }
        }else{
            items.addAll(backupItems);
            notifyDataSetChanged();
        }

    }
}
