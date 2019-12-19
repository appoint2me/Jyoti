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
import com.example.a2mee.jyoti.com.net.activities.Finel_QC;
import com.example.a2mee.jyoti.com.net.activities.MSendtoFG;
import com.example.a2mee.jyoti.com.net.activities.Mainonline_AssemblieInspection;
import com.example.a2mee.jyoti.com.net.activities.Packing_list;
import com.example.a2mee.jyoti.com.net.activities.ProducationOrdar_count;
import com.example.a2mee.jyoti.com.net.activities.Quaraqntine_srore;
import com.example.a2mee.jyoti.com.net.activities.Stagetwopackageing;
import com.example.a2mee.jyoti.com.net.activities.Username_list;
import com.example.a2mee.jyoti.com.net.activities.material_inspActivity;

import java.util.List;

/**
 * Created by A2mee on 9/12/2018.
 */

class NameMainMenuAdapter extends RecyclerView.Adapter<NameMainMenuAdapter.Namea_LIST_dapterViewHolder> {
    String Activet, Activetss;

    private List<String> usernamelistdemo;
    private Context Mctx;
    private List<Username_list> usernamelist;

    //  private MainActivity mainActivity;
    public NameMainMenuAdapter(List<Username_list> usernamelist, Context Mctx) {
        // this.mainActivity=mainActivity;
        this.usernamelist = usernamelist;
        this.Mctx = Mctx;
    }

    @NonNull
    @Override
    public NameMainMenuAdapter.Namea_LIST_dapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(Mctx);
        View v = layoutInflater.inflate(R.layout.user_name_card, parent, false);
        return new NameMainMenuAdapter.Namea_LIST_dapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NameMainMenuAdapter.Namea_LIST_dapterViewHolder holder, int position) {

        final Username_list UserList_listq = usernamelist.get(position);
        final String Typeid = String.valueOf(UserList_listq.getType());
        System.out.print(Typeid);
        holder.PermissionCode.setText(UserList_listq.getPermission());
        if (Typeid.equalsIgnoreCase("E")) {
            Activet = String.valueOf(UserList_listq.getPermission());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Activetss = String.valueOf(UserList_listq.getPermissionValue());
                    System.out.print(Activetss);

                    if (Activetss.equalsIgnoreCase("grn_inspection")) {
                        Intent Finel_material_inspActivity = new Intent(Mctx, com.example.a2mee.jyoti.com.net.activities.Finel_material_inspActivity.class);
                        Mctx.startActivity(Finel_material_inspActivity);
                    }
                    if (Activetss.equalsIgnoreCase("assembly_inspection")) {
                        Intent ProducationOrdar_countll = new Intent(Mctx, Mainonline_AssemblieInspection.class);
                        Mctx.startActivity(ProducationOrdar_countll);
                    }
                    if (Activetss.equalsIgnoreCase("store_operator")) {
                        Intent Packing_listll = new Intent(Mctx, Packing_list.class);
                        Mctx.startActivity(Packing_listll);
                    }
                    if (Activetss.equalsIgnoreCase("quarantine_store")) {
                        Intent ProducationOrdar_countll = new Intent(Mctx, Quaraqntine_srore.class);
                        Mctx.startActivity(ProducationOrdar_countll);
                    }
                    if (Activetss.equalsIgnoreCase("packaging")) {
                        Intent ProducationOrdar_countll = new Intent(Mctx, Stagetwopackageing.class);
                        Mctx.startActivity(ProducationOrdar_countll);
                    }
                    if (Activetss.equalsIgnoreCase("final_inspection")) {
                        Intent ProducationOrdar_countll = new Intent(Mctx, Finel_QC.class);
                        Mctx.startActivity(ProducationOrdar_countll);
                    }
                    if (Activetss.equalsIgnoreCase("production")) {
                        Intent ProducationOrdar_countll = new Intent(Mctx, ProducationOrdar_count.class);
                        Mctx.startActivity(ProducationOrdar_countll);
                    }
                    if (Activetss.equalsIgnoreCase("dispatch_operator")) {
                        Intent ProducationOrdar_countll = new Intent(Mctx, MSendtoFG.class);
                        Mctx.startActivity(ProducationOrdar_countll);
                    }
                    if (Activetss.equalsIgnoreCase("fg_store_operator")) {
                        Intent Pro_countll = new Intent(Mctx, material_inspActivity.class);
                        Mctx.startActivity(Pro_countll);
                    }
                }
            });
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return usernamelist.size();
    }

    public class Namea_LIST_dapterViewHolder extends RecyclerView.ViewHolder {

        public TextView PermissionCode;
        //ImageView Logo;

        public Namea_LIST_dapterViewHolder(View itemView) {
            super(itemView);

            PermissionCode = itemView.findViewById(R.id.notifacationnn);
            // Logo = itemView.findViewById(R.id.logoofact);


        }
    }
}
