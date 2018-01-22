package com.example.boundless.commentview;

import java.util.List;

/**
 * Created by boundless on 9/27/16.
 */
public class Layer_two {
    public List<Layer_three> tagList;
    public  String name;
    public boolean isExpanded= false;

    Layer_two(List<Layer_three> tagList, String name){
        this.tagList = tagList;
        this.name=name;

    }
    public  void setExpanded(boolean expanded){
        this.isExpanded=expanded;

    }
}
