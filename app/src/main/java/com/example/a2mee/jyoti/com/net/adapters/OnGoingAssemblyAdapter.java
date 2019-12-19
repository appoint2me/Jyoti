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
import com.example.a2mee.jyoti.com.net.activities.PendingComponentActivity;
import com.example.a2mee.jyoti.com.net.models.OnGoingAssemblyModel;
import java.util.ArrayList;
import java.util.List;

public class OnGoingAssemblyAdapter extends RecyclerView.Adapter<OnGoingAssemblyAdapter.ViewHolder> {
    private List<OnGoingAssemblyModel> list=new ArrayList<OnGoingAssemblyModel>();
    private Context mContext;

    public OnGoingAssemblyAdapter(ArrayList list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ongoing_assembly,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final OnGoingAssemblyModel onGoingAssemblyModel=list.get(position);
        holder.textViewAssemblyCode.setText(onGoingAssemblyModel.getAssemblyCode());
       // holder.textViewAssemblyId.setText(onGoingAssemblyModel.getAssmblyId());
        holder.textViewAssemblyDesc.setText(onGoingAssemblyModel.getAssemblyDesc());
        holder.textViewAssemblyQty.setText(onGoingAssemblyModel.getPickAssmQty());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, PendingComponentActivity.class).putExtra("ASSEMBLY_CODE",onGoingAssemblyModel.getAssemblyCode()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewAssemblyCode=itemView.findViewById(R.id.textViewAssemblyCode);
        TextView textViewAssemblyDesc=itemView.findViewById(R.id.textViewAssemblyDesc);
        TextView textViewAssemblyQty=itemView.findViewById(R.id.textViewAssemblyQty);
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
