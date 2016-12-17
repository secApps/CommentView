package com.example.boundless.commentview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView commentList;
    LinearLayoutManager linearLayoutManager;
    CommentListAdapter commentListAdapter;
    EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search=(EditText)findViewById(R.id.search);
        commentList =(RecyclerView)findViewById(R.id.comment);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        commentList.setLayoutManager(linearLayoutManager);
        commentListAdapter = new CommentListAdapter(getSampleArrayList1());
        commentList.setAdapter(commentListAdapter);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    commentListAdapter.search(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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
    private ArrayList<Object> getSampleArrayList1(){
        ArrayList<Object> items = new ArrayList<>();
        for(int k=0;k<11;k++) {
            ArrayList<ReplyWithChild> second_items = new ArrayList<>();
            for (int i = 0; i < 11; i++) {

                ArrayList<SubChild> third_items = new ArrayList<>();
                for (int j = 0; j < 6; j++) {

                    third_items.add(new SubChild("C"+Integer.toString(j)));
                }
                second_items.add(new ReplyWithChild(third_items,"B"+Integer.toString(i)));

            }
            items.add(new CommentWithReply(second_items,"A"+Integer.toString(k)));
        }

        return  items;
    }

}
