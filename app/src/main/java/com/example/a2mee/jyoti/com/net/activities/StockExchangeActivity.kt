package com.example.a2mee.jyoti.com.net.activities

import android.app.AlertDialog
import android.app.DownloadManager
import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.a2mee.jyoti.R
import com.example.a2mee.jyoti.com.net.InputFilterMinMax
import com.example.a2mee.jyoti.com.net.utils.Config
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_stock_exchange.*
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class StockExchangeActivity : AppCompatActivity() {
    var storageBinCode=""
    var unUsedQuantity=""
    var requestedQuantity=""
    var componentId=""
    var stockInId=""
    var assemblyCode=""
    lateinit var progressDialog: ProgressDialog
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_exchange)
        progressDialog=ProgressDialog(this)
        progressDialog.setTitle("Message")
        progressDialog.setMessage("Please wait.....")
        progressDialog.create()
        supportActionBar?.title = "Update Stocks"
        unUsedQuantity = intent.getStringExtra("UNUSED_QUANTITY")
        requestedQuantity = intent.getStringExtra("REQUESTED_QUANTITY")
        storageBinCode = intent.getStringExtra("STORAGE_BIN_CODE")
        componentId = intent.getStringExtra("COMPONENT_ID")
        stockInId = intent.getStringExtra("STOCK_IN_ID")
        assemblyCode = intent.getStringExtra("ASSEMBLY_CODE")
        textViewStorageBinCode.setText(storageBinCode)
        textViewAvaliableQuantity.setText(requestedQuantity)
        textViewComponentQuantity.setText(unUsedQuantity)
        textInputLayoutQuantity.isEnabled= false
        buttonSend.isEnabled=false

        buttonScan.setOnClickListener {
            val scan = IntentIntegrator(this)
            scan.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES)
            scan.initiateScan()
        }
        buttonSend.setOnClickListener {
            when{
                editTextQuantity.text.toString().equals("")->editTextQuantity.setError("Please enter quantity")
                else->submitData()

            }
        }

    }
    private fun submitData(){
        buttonSend.isEnabled=false
       progressDialog.show()
        val jsonObject=JSONObject()
                jsonObject.put("componentId",componentId)
                jsonObject.put("pickedQty",editTextQuantity.text.toString())
                jsonObject.put("stockInId",stockInId)
                jsonObject.put("storageBinCode",storageBinCode)

        val jsonObjectRequest=JsonObjectRequest(Request.Method.POST,Config.POST_UPDATE_STOCKS,jsonObject,Response.Listener {
            response ->
            progressDialog.dismiss()
            if(response.getString("message").equals("Success")){
               startActivity(Intent(this,UnUsedComponentsActivity::class.java)
                       .putExtra("ASSEMBLY_CODE",assemblyCode))
            }
            Toast.makeText(this, response.toString(), Toast.LENGTH_SHORT).show();
        }, Response.ErrorListener {
            error->
            progressDialog.dismiss()
            buttonSend.isEnabled=true
            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show();
        })
        Volley.newRequestQueue(this).add(jsonObjectRequest)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        val resultContents = result.contents
        if (resultContents == null) run {
            Toast.makeText(this, "No Data Found !! Pleasse Scan Again", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, resultContents, Toast.LENGTH_SHORT).show();
            if(storageBinCode.equals(resultContents)){

                Toast.makeText(this, "matched", Toast.LENGTH_SHORT).show();
                textInputLayoutQuantity.isEnabled=true
                if(unUsedQuantity>requestedQuantity){
                    editTextQuantity.filters= arrayOf(InputFilterMinMax("1",requestedQuantity))
                    buttonSend.isEnabled=true

                }else if(requestedQuantity>unUsedQuantity){
                    editTextQuantity.filters= arrayOf(InputFilterMinMax("1",unUsedQuantity))
                    buttonSend.isEnabled=true

                }else if(requestedQuantity.equals(unUsedQuantity)){
                    editTextQuantity.filters= arrayOf(InputFilterMinMax("1",unUsedQuantity))
                    buttonSend.isEnabled=true
                }
            }
        }
    }
}
