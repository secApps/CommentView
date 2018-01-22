package com.example.boundless.commentview;

import java.util.List;

/**
 * Created by boundless on 9/27/16.
 */
public class Layer_one {
    public List<Layer_two> reply;
    public  String name;
    public  int id;
    public  boolean isExpanded = false;
    Layer_one(List<Layer_two> reply, String name){

        this.reply=reply;
        this.name = name;

    }

    public  void setExpanded(boolean expanded){
        this.isExpanded=expanded;

    }
}
