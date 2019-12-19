package com.example.a2mee.jyoti.com.net.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.a2mee.jyoti.R;
import com.example.a2mee.jyoti.com.net.adapters.OnGoingAssemblyAdapter;
import com.example.a2mee.jyoti.com.net.models.OnGoingAssemblyModel;
import com.example.a2mee.jyoti.com.net.utils.Config;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import static com.example.a2mee.jyoti.com.net.utils.Config.password_SHARED_PREF;

public class OnGoingAssemblyActivity extends AppCompatActivity {
    RecyclerView rvOnGoingAssembly;
    ProgressBar progressBar;
    ArrayList listOnGoingAssembly=new ArrayList<OnGoingAssemblyModel>();
    private static final String TAG = "OnGoingAssemblyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_going_assembly);
        getSupportActionBar().setTitle("On Going Assembly");
        rvOnGoingAssembly=(RecyclerView)findViewById(R.id.rvOnGoingAssembly);
        progressBar=findViewById(R.id.progressBar);
        getonGoingAssembly();
    }
    private void getonGoingAssembly(){

        String userId= getSharedPreferences(Config.mylogin_PREF_NAME, Context.MODE_PRIVATE).getString(password_SHARED_PREF,null);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Config.GET_ON_GOING_ASSEMBLY + userId, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.GONE);
                Log.d(TAG, "onResponse: "+response);
                try {
                    for (int i=0;i<response.length();i++) {

                        String assemblyCode = response.getJSONObject(i).getJSONObject("assemblyMst").getString("assemblyCode");
                        String assemblyDesc = response.getJSONObject(i).getJSONObject("assemblyMst").getString("assemblyDesc");
                        String pickAssmQty = response.getJSONObject(i).getString("pickAssmQty");
                        String status = response.getJSONObject(i).getString("status");
                        listOnGoingAssembly.add(new OnGoingAssemblyModel(assemblyCode,assemblyDesc,pickAssmQty,status));

                    }
                    rvOnGoingAssembly.setLayoutManager(new LinearLayoutManager(OnGoingAssemblyActivity.this));
                    rvOnGoingAssembly.setAdapter(new OnGoingAssemblyAdapter(listOnGoingAssembly,OnGoingAssemblyActivity.this));
                }catch (JSONException e){
                    e.printStackTrace();
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(OnGoingAssemblyActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OnGoingAssemblyActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }
}
