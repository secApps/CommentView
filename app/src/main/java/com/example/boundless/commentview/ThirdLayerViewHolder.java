package com.example.boundless.commentview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by boundless on 9/27/16.
 */
public class ThirdLayerViewHolder extends RecyclerView.ViewHolder {
    public TextView mTitle;
    public CheckBox mCheckBox;
    public ThirdLayerViewHolder(View itemView) {
        super(itemView);
        mTitle= (TextView) itemView.findViewById(R.id.sub);
        mCheckBox = (CheckBox) itemView.findViewById(R.id.check_box);

    }
}
