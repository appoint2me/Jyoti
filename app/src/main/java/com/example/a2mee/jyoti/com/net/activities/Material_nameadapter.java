package com.example.a2mee.jyoti.com.net.activities;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a2mee.jyoti.R;

import java.util.List;

/**
 * Created by A2mee on 7/27/2018.
 */

class Material_nameadapter extends RecyclerView.Adapter<Material_nameadapter.MaterialnameViewHolder> {

   private List<Material_list> materialname1;
   private Packing_list packing_list;

    public Material_nameadapter(List<Material_list> materialname1, Packing_list packing_list) {

       this.materialname1=materialname1;
       this.packing_list= packing_list;
    }



    @Override
    public MaterialnameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(packing_list);
        View v = layoutInflater.inflate(R.layout.materialmname_card, parent, false);
        return new MaterialnameViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialnameViewHolder holder, int position) {

         Material_list material_list = materialname1.get(position);
        holder.Mateerialname.setText(material_list.getItemName());
        holder.BarCode.setText(material_list.getBarCode());
        holder.qty.setText(material_list.getQty());
        holder.Status.setText(material_list.getStatus());

        String dataid = String.valueOf(material_list.getStatus());
        System.out.print(dataid);
        if(dataid.equalsIgnoreCase("valid") ){
            holder.Status.setBackgroundColor(Color.parseColor("#38b64a"));
            holder.Status.setTextColor(Color.parseColor("#38b64a"));
        }else {
            holder.Status.setBackgroundColor(Color.parseColor("#ed2831"));
            holder.Status.setTextColor(Color.parseColor("#ed2831"));
        }


        String Status = String.valueOf(material_list.getStatus());
        String qty1 = String.valueOf(material_list.getQty());

     /* Intent mIntent = new Intent(packing_list,Packing_list.class);

        mIntent.putExtra("Status",Status);
        mIntent.putExtra("qty12", qty1);
        packing_list.startActivity(mIntent);*/

        Log.d("123456", dataid);

    }

    @Override
    public int getItemCount() {
        return materialname1.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class MaterialnameViewHolder extends RecyclerView.ViewHolder {

        public TextView Mateerialname;
        public TextView BarCode;
        public TextView qty;
        public TextView Status;

        public MaterialnameViewHolder(View itemView) {
            super(itemView);

            Mateerialname = itemView.findViewById(R.id.materialnamme_Name);
            BarCode = itemView.findViewById(R.id.barcodenp_Name);
            qty = itemView.findViewById(R.id.Qttyu);
            Status = itemView.findViewById((R.id.Statusii));



        }
    }

    //extends RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>

}
