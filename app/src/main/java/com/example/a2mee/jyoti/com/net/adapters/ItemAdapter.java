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
import com.example.a2mee.jyoti.com.net.models.ItemDetailsModel;
import com.example.a2mee.jyoti.com.net.activities.LocationPutAwayScanActivity;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{
    private Context context;
    private ArrayList<ItemDetailsModel> itemDetailsModels = new ArrayList<>();
    ItemDetailsModel itemDetailsModel;

    public ItemAdapter(Context context, ArrayList<ItemDetailsModel> itemDetailsModels) {
        this.context = context;
        this.itemDetailsModels = itemDetailsModels;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_details, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        itemDetailsModel=itemDetailsModels.get(position);
        holder.textViewGrnno.setText(itemDetailsModel.getGrnno());
        holder.textViewItemDetail.setText(itemDetailsModel.getItemdetails());
        holder.textViewItemId.setText(itemDetailsModel.getItemId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemDetailsModel itemDetailsModel1=itemDetailsModels.get(position);
                Intent intent=new Intent(context, LocationPutAwayScanActivity.class);
                intent.putExtra("GRN_NO",itemDetailsModel1.getGrnno());
                intent.putExtra("QR_CODE",itemDetailsModel1.getQrcode());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemDetailsModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewGrnno, textViewItemDetail,textViewItemId;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewGrnno = itemView.findViewById(R.id.textViewGrnNo);
            textViewItemDetail = itemView.findViewById(R.id.textViewItemDetail);
            textViewItemId = itemView.findViewById(R.id.textViewItemId);
        }

    }
}
