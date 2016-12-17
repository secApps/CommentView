package com.example.boundless.commentview;

import java.util.List;

/**
 * Created by boundless on 9/27/16.
 */
public class ReplyWithChild {
    public List<SubChild> subChildList;
    public  String name;
    public boolean isExpanded= false;

    ReplyWithChild(List<SubChild> subChildList,String name){
        this.subChildList=subChildList;
        this.name=name;

    }
    public  void setExpanded(boolean expanded){
        this.isExpanded=expanded;

    }
}
