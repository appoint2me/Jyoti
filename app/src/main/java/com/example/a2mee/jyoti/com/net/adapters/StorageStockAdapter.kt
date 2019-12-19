package com.example.a2mee.jyoti.com.net.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a2mee.jyoti.R
import com.example.a2mee.jyoti.com.net.activities.StockExchangeActivity
import com.example.a2mee.jyoti.com.net.models.StorageStockModel
import kotlinx.android.synthetic.main.item_pending_components.view.textViewPickQty
import kotlinx.android.synthetic.main.item_storage_stock.view.*

class StorageStockAdapter(val context: Context, val list: ArrayList<StorageStockModel>, val unusedQuantity: String, val componentId: String, val assemblyCode: String) : RecyclerView.Adapter<StorageStockAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_storage_stock,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
       val storageStockModel=list[position]
        holder.textViewItemMstId.text=storageStockModel.itemMstId
        holder.textViewItemDtl.text=storageStockModel.itemDtl
        holder.textViewStorageBinCode.text=storageStockModel.storageBinCode
        holder.textViewQty.text=storageStockModel.qty
        holder.itemView.setOnClickListener {
          context.startActivity(Intent(context,StockExchangeActivity::class.java)
                  .putExtra("UNUSED_QUANTITY",unusedQuantity)
                  .putExtra("REQUESTED_QUANTITY",storageStockModel.qty)
                  .putExtra("STORAGE_BIN_CODE",storageStockModel.storageBinCode)
                  .putExtra("COMPONENT_ID",componentId)
                  .putExtra("STOCK_IN_ID",storageStockModel.mtlStockInId)
                  .putExtra("ASSEMBLY_CODE",assemblyCode))
        }
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textViewItemMstId=itemView.textViewItemMstId
        val textViewItemDtl=itemView.textViewItemDtl
        val textViewStorageBinCode=itemView.textViewStorageBinCode
        val textViewQty=itemView.textViewPickQty
    }

}
