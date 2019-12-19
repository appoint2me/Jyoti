package com.example.a2mee.jyoti.com.net.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.a2mee.jyoti.R
import com.example.a2mee.jyoti.com.net.adapters.PendingComponentAdapter
import com.example.a2mee.jyoti.com.net.models.PendingComponentModel
import com.example.a2mee.jyoti.com.net.utils.Config
import kotlinx.android.synthetic.main.activity_un_used_components.*

class UnUsedComponentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_un_used_components)
        supportActionBar?.title ="Pick Components"
        textViewNothing.visibility= View.GONE
        val assemblyCode=intent?.getStringExtra("ASSEMBLY_CODE")!!
        getCompByAssembly(assemblyCode)
    }
    private fun getCompByAssembly(assemblyCode:String){
        val listPendingComponent=ArrayList<PendingComponentModel>()
        val userId=getSharedPreferences(Config.mylogin_PREF_NAME, Context.MODE_PRIVATE).getString(Config.password_SHARED_PREF,null)
        val jsonArrayRequest=JsonArrayRequest(Config.GET_PENDING_COMPONENTS+userId+"&assemblyCode="+assemblyCode, Response.Listener {
            response->
            if(response.isNull(0)) {
                textViewNothing.visibility= View.VISIBLE

            }else {
                textViewNothing.visibility= View.GONE

                for (i in 0 until response.length()) {
                    val pickCompQty = response.getJSONObject(i).getString("pickCompQty")
                    val pickingCompId = response.getJSONObject(i).getString("pickingCompId")
                    val status = response.getJSONObject(i).getString("status")
                    val compCode = response.getJSONObject(i).getJSONObject("componentMst").getString("compCode")
                    val compDesc = response.getJSONObject(i).getJSONObject("componentMst").getString("compDesc")
                    val pendingComponentModel = PendingComponentModel(pickingCompId, pickCompQty, status, compCode, compDesc)
                    listPendingComponent.add(pendingComponentModel)

                }
            }
            rvUnUsedComponents.layoutManager= LinearLayoutManager(this)
            rvUnUsedComponents.adapter= PendingComponentAdapter(this,listPendingComponent,assemblyCode)
        }, Response.ErrorListener {
            error ->
            textViewNothing.visibility= View.GONE

            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show();
        })
        Volley.newRequestQueue(this).add(jsonArrayRequest)

    }

    override fun onBackPressed() {
        startActivity(Intent(this,NewAssemblyActivity::class.java))
    }
}
