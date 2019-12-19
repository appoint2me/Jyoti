package com.example.a2mee.jyoti.com.net.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.a2mee.jyoti.R
import com.example.a2mee.jyoti.com.net.adapters.StorageStockAdapter
import com.example.a2mee.jyoti.com.net.models.StorageStockModel
import com.example.a2mee.jyoti.com.net.utils.Config
import kotlinx.android.synthetic.main.activity_storage_stock.*

class StorageStockActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage_stock)
        supportActionBar?.title ="Storage Stock"
        textViewNothing.visibility=View.GONE
        val componentCode=intent.getStringExtra("COMPONENT_CODE")
        val unusedQuantity=intent.getStringExtra("UNUSED_QUANTITY")
        val componentId=intent.getStringExtra("COMPONENT_ID")
        val assemblyCode=intent.getStringExtra("ASSEMBLY_CODE")
        getStorageStock(componentCode,unusedQuantity,componentId,assemblyCode)
    }
    private fun getStorageStock(componentCode:String,unusedQuantity:String,componentId:String,assemblyCode:String){
        var listStorageStock=ArrayList<StorageStockModel>()
        val jsonArrayRequest=JsonArrayRequest(Config.GET_STORAGE_STOCK_BY_COMPONENT_CODE+componentCode,Response.Listener {
          response ->
            if(response.isNull(0)){
                textViewNothing.visibility=View.VISIBLE
            }else {


                for (i in 0 until response.length()) {
                    val itemMstId = response.getJSONObject(i).getString("itemMstId")
                    val itemDtl = response.getJSONObject(i).getString("itemDtl")
                    val storageBinCode = response.getJSONObject(i).getString("storageBinCode")
                    val qty = response.getJSONObject(i).getString("qty")
                    val mtlStockInId = response.getJSONObject(i).getString("mtlStockInId")
                    val storageStockModel = StorageStockModel(itemDtl, itemMstId, mtlStockInId, qty, storageBinCode)
                    listStorageStock.add(storageStockModel)

                }
            }
            rvStorageStock.layoutManager=LinearLayoutManager(this)
            rvStorageStock.adapter=StorageStockAdapter(this,listStorageStock,unusedQuantity,componentId,assemblyCode)
        }, Response.ErrorListener {
            error ->
            textViewNothing.visibility=View.GONE
            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show();
        })
        Volley.newRequestQueue(this).add(jsonArrayRequest)
    }
}
