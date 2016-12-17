package com.example.boundless.commentview;

import java.util.List;

/**
 * Created by boundless on 9/27/16.
 */
public class CommentWithReply {
    public List<ReplyWithChild> reply;
    public  String name;
    public  boolean isExpanded = false;
    CommentWithReply( List<ReplyWithChild> reply, String name){

        this.reply=reply;
        this.name = name;

    }

    public  void setExpanded(boolean expanded){
        this.isExpanded=expanded;

    }
}
