package com.example.a2mee.jyoti.com.net.adapters

import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a2mee.jyoti.R
import com.example.a2mee.jyoti.com.net.activities.PendingComponentActivity
import com.example.a2mee.jyoti.com.net.activities.UnUsedComponentsActivity
import com.example.a2mee.jyoti.com.net.models.NewAssemblyModel
import com.example.a2mee.jyoti.com.net.models.PendingComponentModel
import kotlinx.android.synthetic.main.item_new_assembly.view.*
import kotlinx.android.synthetic.main.item_pending_components.view.textViewPickQty

class NewAssemblyAdapter(val context: Context, val listNewAssembly: ArrayList<NewAssemblyModel>) : RecyclerView.Adapter<NewAssemblyAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_new_assembly,parent,false))

    }

    override fun getItemCount(): Int {
        return listNewAssembly.size
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
       val newAssemblyModel=listNewAssembly[position]
        holder.textViewAssemblyCode.text=newAssemblyModel.assemblyCode
        holder.textViewAssemblyDesc.text=newAssemblyModel.assemblyDesc
        holder.textViewComponentQty.text=newAssemblyModel.pickCompQty
        if(newAssemblyModel.status.equals("2")) {
            holder.cardView.setBackgroundColor(context.resources.getColor(R.color.colorAccent))
        }else if(newAssemblyModel.status.equals("1")){
            holder.cardView.setBackgroundColor(context.resources.getColor(android.R.color.holo_green_light))
        }else if(newAssemblyModel.status.equals("0")){
            holder.cardView.setBackgroundColor(context.resources.getColor(android.R.color.white))
        }

        holder.itemView.setOnClickListener{
            if(newAssemblyModel.status.equals("1")){

            }else {
                context.startActivity(Intent(context, UnUsedComponentsActivity::class.java).putExtra("ASSEMBLY_CODE", newAssemblyModel.assemblyCode))
            }
        }
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textViewAssemblyCode=itemView.textViewAssemblyCode
        val textViewAssemblyDesc=itemView.textViewAssemblyDesc
        val textViewComponentQty=itemView.textViewPickQty
        var cardView=itemView.cardView
    }

}
