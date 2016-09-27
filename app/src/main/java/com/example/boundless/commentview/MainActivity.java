package com.example.boundless.commentview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     RecyclerView commentList;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        commentList =(RecyclerView)findViewById(R.id.comment);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        commentList.setLayoutManager(linearLayoutManager);
        commentList.setAdapter(new CommentListAdapter(getSampleArrayList()));

    }

    private ArrayList<Object> getSampleArrayList() {
        ArrayList<Object> items = new ArrayList<>();
        items.add(new Comment("Dany Targaryen"));
        items.add(new Reply("Rob Stark"));
        items.add(new Reply("Rob Stark"));
        items.add(new Comment("Jon Snow"));
        items.add(new Comment("Jon Snow"));
        items.add( new Reply("Rob Stark"));
        return items;
    }

}
