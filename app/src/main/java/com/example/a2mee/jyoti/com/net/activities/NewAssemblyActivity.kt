package com.example.a2mee.jyoti.com.net.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.a2mee.jyoti.R
import com.example.a2mee.jyoti.com.net.adapters.NewAssemblyAdapter
import com.example.a2mee.jyoti.com.net.adapters.OnGoingAssemblyAdapter
import com.example.a2mee.jyoti.com.net.models.NewAssemblyModel
import com.example.a2mee.jyoti.com.net.models.OnGoingAssemblyModel
import com.example.a2mee.jyoti.com.net.utils.Config
import com.example.a2mee.jyoti.com.net.utils.Config.password_SHARED_PREF
import kotlinx.android.synthetic.main.activity_new_assembly.*
import kotlinx.android.synthetic.main.activity_on_going_assembly.*
import org.json.JSONArray
import org.json.JSONException

class NewAssemblyActivity : AppCompatActivity() {
    val TAG = "NewAssemblyActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_assembly)
        supportActionBar?.setTitle("Pick Assemblies")
        textViewNothing.visibility=View.GONE
        getNewAssembly()
    }

    private fun getNewAssembly() {
        val listNewAssembly = ArrayList<NewAssemblyModel>()
        val userId = getSharedPreferences(Config.mylogin_PREF_NAME, Context.MODE_PRIVATE).getString(password_SHARED_PREF, null)
        val jsonArrayRequest = JsonArrayRequest(Config.GET_NEW_ASSEMBLY + userId!!, Response.Listener { response ->

            Log.d(TAG, "onResponse: $response")
            try {
                if (response.isNull(0)) {
                    textViewNothing.visibility=View.VISIBLE
                } else {
                    textViewNothing.visibility=View.GONE

                    for (i in 0 until response.length()) {

                        val assemblyCode = response.getJSONObject(i).getJSONObject("assemblyMst").getString("assemblyCode")
                        val assemblyDesc = response.getJSONObject(i).getJSONObject("assemblyMst").getString("assemblyDesc")
                        val pickAssmQty = response.getJSONObject(i).getString("pickAssmQty")
                        val status = response.getJSONObject(i).getString("status")
                        listNewAssembly.add(NewAssemblyModel(pickAssmQty, status, assemblyCode, assemblyDesc))

                    }
                    rvNewAssembly.setLayoutManager(LinearLayoutManager(this))
                    rvNewAssembly.setAdapter(NewAssemblyAdapter(this, listNewAssembly))
                }
            } catch (e: JSONException) {
                e.printStackTrace()
                textViewNothing.visibility=View.GONE

                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }

        }, Response.ErrorListener { error ->
            textViewNothing.visibility=View.GONE
            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()

        })
        Volley.newRequestQueue(this).add(jsonArrayRequest)
    }

    override fun onBackPressed() {
        startActivity(Intent(this,PickingActivity::class.java))
    }
}
