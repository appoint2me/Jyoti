package com.example.a2mee.jyoti.com.net.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a2mee.jyoti.R;

import java.util.List;

/**
 * Created by A2mee on 9/5/2018.
 */

class Material_info_Adapter extends RecyclerView.Adapter<Material_info_Adapter.Material_infoViewHolder> {

    private List<itemDetails_listw> materi1;
    private Context Stagetwopackageing;

    public Material_info_Adapter(List<itemDetails_listw> materi1, Context stagetwopackageing) {
        this.materi1 = materi1;
        this.Stagetwopackageing = stagetwopackageing;

    }


    @Override
    public Material_infoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(Stagetwopackageing);
        View v = layoutInflater.inflate(R.layout.materialditelforpack_card, parent, false);
        return new Material_info_Adapter.Material_infoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Material_infoViewHolder holder, int position) {

        itemDetails_listw material_listrr = materi1.get(position);
        holder.qty.setText(String.valueOf(material_listrr.getSsss()));
        holder.BarCode.setText(material_listrr.getStageOneQrCode());
        holder.size.setText(String.valueOf(material_listrr.getPacketSizeQty()));
        try {
            holder.Total.setText(String.valueOf(material_listrr.getIgi()));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return materi1.size();
    }

    public class Material_infoViewHolder extends RecyclerView.ViewHolder {
        TextView size, BarCode, qty, Total;

        public Material_infoViewHolder(View itemView) {
            super(itemView);
            BarCode = itemView.findViewById(R.id.barcode_name_data);
            qty = itemView.findViewById(R.id.material_name_qty);
            size = itemView.findViewById(R.id.sixxzq);
            Total = itemView.findViewById(R.id.material_name_total);

        }
    }
}
