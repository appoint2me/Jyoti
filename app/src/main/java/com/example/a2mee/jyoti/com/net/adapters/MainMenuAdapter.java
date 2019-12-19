package com.example.a2mee.jyoti.com.net.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a2mee.jyoti.R;
import com.example.a2mee.jyoti.com.net.activities.Finel_material_inspActivity;
import com.example.a2mee.jyoti.com.net.activities.LocationPutAwayActivity;
import com.example.a2mee.jyoti.com.net.activities.MainActivity;
import com.example.a2mee.jyoti.com.net.activities.PickingActivity;

import java.util.ArrayList;



public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.ViewHolder> {
    Context mContext;
    ArrayList listMainMenu=new ArrayList<String>();
    public MainMenuAdapter(Context context, ArrayList listMainMenu) {
        this.listMainMenu=listMainMenu;
        this.mContext=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_menu,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if(listMainMenu.get(position).equals("grnInspection")) {
            holder.textViewMenuTitle.setText("GRN INSPECTION");

        }else if(listMainMenu.get(position).equals("putaway")){

            holder.textViewMenuTitle.setText("PUT AWAY");
        }else if(listMainMenu.get(position).equals("picking")){
            holder.textViewMenuTitle.setText("PICKING");
        }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if(holder.textViewMenuTitle.getText().toString().equals("GRN INSPECTION")){
                       mContext.startActivity(new Intent(mContext, Finel_material_inspActivity.class));
                   }else if(holder.textViewMenuTitle.getText().toString().equals("PUT AWAY")){
                       mContext.startActivity(new Intent(mContext, LocationPutAwayActivity.class));
                   }else if(holder.textViewMenuTitle.getText().toString().equals("PICKING")){
                        mContext.startActivity(new Intent(mContext, PickingActivity.class));
                   }
                }
            });
        
    }

    @Override
    public int getItemCount() {
        return listMainMenu.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewMenuTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            textViewMenuTitle=itemView.findViewById(R.id.textViewTitle);
        }
    }
}
