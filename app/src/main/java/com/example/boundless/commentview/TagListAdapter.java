package com.example.boundless.commentview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by boundless on 9/26/16.
 */
public class TagListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> items;
    private List<Object> backupItems;

    private final int LAYER_1 = 0, LAYER_2 = 1,LAYER_3=2;
    public TagListAdapter(List<Object> items){
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
            case LAYER_1:
                View v1 = inflater.inflate(R.layout.first_layer_view, parent, false);
                viewHolder = new FirstLayerViewHolder(v1);
                break;
            case LAYER_2:
                View v2 = inflater.inflate(R.layout.second_layer_view, parent, false);
                viewHolder = new SecondLayerViewHolder(v2);
                break;
            case LAYER_3:
                View v3 = inflater.inflate(R.layout.third_layer_view, parent, false);
                viewHolder = new ThirdLayerViewHolder(v3);
                break;
            default:
               View v4 = inflater.inflate(R.layout.third_layer_view, parent, false);
                viewHolder = new ThirdLayerViewHolder(v4);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position, List<Object> payloads) {
        switch (viewHolder.getItemViewType()) {
            case LAYER_1:
                FirstLayerViewHolder vh1 = (FirstLayerViewHolder) viewHolder;
                configureLayerOneViewHolder(vh1, position);
                break;
            case LAYER_2:
                SecondLayerViewHolder vh2 = (SecondLayerViewHolder) viewHolder;
                configureLayerTwoViewHolder(vh2, position);
                break;
            case LAYER_3:
                ThirdLayerViewHolder vh3 = (ThirdLayerViewHolder) viewHolder;
                configureLayerThreeViewHolder(vh3, position);
                break;
            default:
                ThirdLayerViewHolder vh4 = (ThirdLayerViewHolder) viewHolder;
                configureLayerThreeViewHolder(vh4, position);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof Layer_one) {
            return LAYER_1;
        } else if (items.get(position) instanceof Layer_two) {
            return LAYER_2;
        }else if(items.get(position) instanceof Layer_three){
            return LAYER_3;
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
    private  void configureLayerOneViewHolder(FirstLayerViewHolder v1, final int position){
      v1.body.setText ( ((Layer_one)items.get(position)).name);
        if(((Layer_one)items.get(position)).isExpanded)
        v1.expand.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24px);
        else
            v1.expand.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24px);
         v1.body.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(items.get(position) instanceof Layer_one)
                if(((Layer_one)items.get(position)).reply.size()>0){
//                    for(int i=0;i<((Layer_one)items.get(position)).reply.size();i++){
//                        items.add(((Layer_one)items.get(position)).reply.get(i));
//                    }
                    if(!((Layer_one)items.get(position)).isExpanded) {
                        items.addAll(position + 1, ((Layer_one) items.get(position)).reply);
                        notifyItemRangeInserted(position + 1, ((Layer_one) items.get(position)).reply.size());
                        ((Layer_one)items.get(position)).setExpanded(true);
                    }else{
                        items.removeAll(((Layer_one) items.get(position)).reply);
                        for(int i = 0; i<((Layer_one) items.get(position)).reply.size(); i++){
                            items.removeAll(((Layer_two) ((Layer_one) items.get(position)).reply.get(i)).tagList);
                        }

                        notifyItemRangeRemoved(position+1,((Layer_one)items.get(position)).reply.size());
                        ((Layer_one)items.get(position)).setExpanded(false);
                    }
                    notifyDataSetChanged();
                }
             }
         });

    }

    private void configureLayerTwoViewHolder(SecondLayerViewHolder v1, final int position){
        v1.body.setText ( ((Layer_two)items.get(position)).name);
        if(((Layer_two)items.get(position)).isExpanded)
            v1.expand.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24px);
        else
            v1.expand.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24px);
        v1.body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(items.get(position) instanceof Layer_two)
                if(((Layer_two)items.get(position)).tagList.size()>0){
//                    for(int i=0;i<((Layer_two)items.get(position)).tagList.size();i++){
//                        items.add(((Layer_two)items.get(position)).tagList.get(i));
//                    }
                    if(!((Layer_two)items.get(position)).isExpanded) {
                        items.addAll(position + 1, ((Layer_two) items.get(position)).tagList);
                        ((Layer_two)items.get(position)).setExpanded(true);
                        notifyItemRangeInserted(position+1,((Layer_two)items.get(position)).tagList.size());
                    }else{
                        items.removeAll(((Layer_two) items.get(position)).tagList);
                        notifyItemRangeRemoved(position+1,((Layer_two)items.get(position)).tagList.size());
                        ((Layer_two)items.get(position)).setExpanded(false);
                    }

                    notifyDataSetChanged();
                }
            }
        });

    }
    private void configureLayerThreeViewHolder(ThirdLayerViewHolder v1, final int position){
        v1.mTitle.setText ( ((Layer_three)items.get(position)).name);
        v1.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    ((Layer_three) items.get(position)).setChecked(true);
                    Log.d("selected", getCheckedItems().toString());
                } else
                    ((Layer_three) items.get(position)).setChecked(false);
            }
        });
        if(items.get(position) instanceof Layer_three) {
            if (((Layer_three) items.get(position)).isChecked)
                v1.mCheckBox.setChecked(true);
            else
                v1.mCheckBox.setChecked(false);


        }

    }

    public List<Layer_three> getCheckedItems(){
        List<Layer_three> selectedItems = null;
        if(items!=null){
            if(items.size()>0){
                selectedItems= new ArrayList<Layer_three>();
                for(int i=0;i<items.size();i++){
                    if(items.get(i) instanceof Layer_three){
                       if(((Layer_three) items.get(i)).isChecked) {
                           selectedItems.add(((Layer_three) items.get(i)));
                       }
                    }

                }

            }
        }

        return selectedItems;
    }

    public void search(String query){
        query=query.toLowerCase();
        if(query.length()>0) {
            Log.d("Entered","length"+Integer.toString(backupItems.size()));
            items.clear();
            Log.d("Entered","length"+Integer.toString(backupItems.size()));
            for (int i = 0; i < backupItems.size(); i++) {
                Log.d("name",">>"+((Layer_one)backupItems.get(i)).name.toLowerCase());
                if(((Layer_one)backupItems.get(i)).name.toLowerCase().contains(query)) {
                    items.add(backupItems.get(i));
                    Log.d("Entered","added header");
                }
                for(int j = 0; j<((Layer_one)backupItems.get(i)).reply.size(); j++){
                    if(((Layer_two)((Layer_one) backupItems.get(i)).reply.get(j)).name.toLowerCase().contains(query)) {

                        if(!items.contains(backupItems.get(i))){
                            items.add(backupItems.get(i));
                        }
                        if(items.get(i) instanceof Layer_one)
                        ((Layer_one)items.get(i)).setExpanded(true);

                        items.add(((Layer_two) ((Layer_one) backupItems.get(i)).reply.get(j)));
                        Log.d("Entered","added second header");

                    }

                    for(int k = 0; k<((Layer_two) ((Layer_one) backupItems.get(i)).reply.get(j)).tagList.size(); k++){
                       if(((Layer_two) ((Layer_one) backupItems.get(i)).reply.get(j)).tagList.get(k).name.toLowerCase().contains(query)){
                           if(!items.contains(backupItems.get(i))){
                               items.add(backupItems.get(i));
                               ((Layer_one)items.get(items.lastIndexOf(backupItems.get(i)))).setExpanded(true);
                           }
                           if(!items.contains(((Layer_two) ((Layer_one) backupItems.get(i)).reply.get(j)))){
                               items.add(((Layer_two) ((Layer_one) backupItems.get(i)).reply.get(j)));
                               ((Layer_two) items.get(items.lastIndexOf(((Layer_two) ((Layer_one) backupItems.get(i)).reply.get(j))))).setExpanded(true);
                           }
                           Log.d("Entered","added third header");
                           items.add(((Layer_two) ((Layer_one) backupItems.get(i)).reply.get(j)).tagList.get(k));
//                           ((Layer_one)items.get(i)).setExpanded(true);



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
