package com.example.a2mee.jyoti.com.net.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.a2mee.jyoti.R
import com.example.a2mee.jyoti.com.net.adapters.PendingComponentAdapter
import com.example.a2mee.jyoti.com.net.models.PendingComponentModel
import com.example.a2mee.jyoti.com.net.utils.Config
import kotlinx.android.synthetic.main.activity_pending_component.*

class PendingComponentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pending_component)
        val assemblyCode=intent.getStringExtra("ASSEMBLY_CODE")
        getPendingComponents(assemblyCode)
    }
    private fun getPendingComponents(assemblyCode:String){
        val listPendingComponent=ArrayList<PendingComponentModel>()
        val userId=getSharedPreferences(Config.mylogin_PREF_NAME, Context.MODE_PRIVATE).getString(Config.password_SHARED_PREF,null)
        val jsonArrayRequest=JsonArrayRequest(Config.GET_PENDING_COMPONENTS+userId+"&assemblyCode="+assemblyCode,Response.Listener {
            response->
            for (i in 0 until response.length()) {
                val pickingCompId = response.getJSONObject(i).getString("pickingCompId")
                val pickCompQty = response.getJSONObject(i).getString("pickCompQty")
                val status = response.getJSONObject(i).getString("status")
                val compCode = response.getJSONObject(i).getJSONObject("componentMst").getString("compCode")
                val compDesc = response.getJSONObject(i).getJSONObject("componentMst").getString("compDesc")
                val pendingComponentModel=PendingComponentModel(pickingCompId,pickCompQty,status,compCode,compDesc)
                listPendingComponent.add(pendingComponentModel)

            }
            rvPendingComponents.layoutManager=LinearLayoutManager(this)
            rvPendingComponents.adapter= PendingComponentAdapter(this, listPendingComponent, assemblyCode)
        }, Response.ErrorListener {
            error ->
            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show();
        })
        Volley.newRequestQueue(this).add(jsonArrayRequest)
    }
}
