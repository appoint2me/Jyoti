package com.example.a2mee.jyoti.com.net.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a2mee.jyoti.R
import com.example.a2mee.jyoti.com.net.activities.StorageStockActivity
import com.example.a2mee.jyoti.com.net.models.PendingComponentModel
import kotlinx.android.synthetic.main.item_pending_components.view.*

class PendingComponentAdapter(val context: Context, val listPendingComponent: ArrayList<PendingComponentModel>, val assemblyCode: String) : RecyclerView.Adapter<PendingComponentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pending_components,parent,false))
    }

    override fun getItemCount(): Int {
        return listPendingComponent.size
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
       val pendingComponentModel=listPendingComponent[position]
        holder.textViewComponentCode.text=pendingComponentModel.compCode
        holder.textViewComponentDesc.text=pendingComponentModel.compDesc
        holder.textViewComponentQty.text=pendingComponentModel.pickCompQty
        if(pendingComponentModel.status.equals("2")){
            holder.cardView.setBackgroundColor(context.resources.getColor(R.color.colorAccent))
        }else if(pendingComponentModel.status.equals("1")){
            holder.cardView.setBackgroundColor(context.resources.getColor(android.R.color.holo_green_light))
        }else if(pendingComponentModel.status.equals("0")){
            holder.cardView.setBackgroundColor(context.resources.getColor(android.R.color.white))
        }
        holder.itemView.setOnClickListener {
            if(!pendingComponentModel.status.equals("1")) {
                context.startActivity(Intent(context, StorageStockActivity::class.java).putExtra("COMPONENT_CODE", pendingComponentModel.compCode)
                        .putExtra("UNUSED_QUANTITY", pendingComponentModel.pickCompQty)
                        .putExtra("COMPONENT_ID", pendingComponentModel.pickingCompId)
                        .putExtra("ASSEMBLY_CODE", assemblyCode))
            }
        }
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textViewComponentCode=itemView.textViewComponenCode
        val textViewComponentDesc=itemView.textViewComponentDesc
        val textViewComponentQty=itemView.textViewPickQty
        var cardView=itemView.cardView
    }

}
