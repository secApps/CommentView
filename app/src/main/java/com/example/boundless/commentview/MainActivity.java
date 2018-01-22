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
    TagListAdapter tagListAdapter;
    EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search=(EditText)findViewById(R.id.search);
        commentList =(RecyclerView)findViewById(R.id.comment);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        commentList.setLayoutManager(linearLayoutManager);
        tagListAdapter = new TagListAdapter(getSampleArrayList1());
        commentList.setAdapter(tagListAdapter);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    tagListAdapter.search(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


    private ArrayList<Object> getSampleArrayList1(){
        ArrayList<Object> items = new ArrayList<>();
        for(int k=0;k<11;k++) {
            ArrayList<Layer_two> second_items = new ArrayList<>();
            for (int i = 0; i < 11; i++) {

                ArrayList<Layer_three> third_items = new ArrayList<>();
                for (int j = 0; j < 6; j++) {

                    third_items.add(new Layer_three("C"+Integer.toString(j)));
                }
                second_items.add(new Layer_two(third_items,"B"+Integer.toString(i)));

            }
            items.add(new Layer_one(second_items,"A"+Integer.toString(k)));
        }

        return  items;
    }

}
