package com.example.a2mee.jyoti.com.net.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.a2mee.jyoti.R;
import com.example.a2mee.jyoti.com.net.utils.Config;

import org.json.JSONException;

public class PickingActivity extends AppCompatActivity {
    ProgressBar progressBar;
    String quantity="0.0";
    TextView textViewQuantity;
    Button buttonOnGoingAssembly;
    Button buttonNewAssembly;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picking);
        progressBar=(ProgressBar)findViewById(R.id.progressBar2);
        textViewQuantity=(TextView)findViewById(R.id.textViewQuantity);
       // buttonOnGoingAssembly=(Button)findViewById(R.id.buttonOnGoingAssembly);
        buttonNewAssembly=(Button)findViewById(R.id.buttonNewAssembly);
//        buttonOnGoingAssembly.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(PickingActivity.this,OnGoingAssemblyActivity.class));
//            }
//        });
        buttonNewAssembly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PickingActivity.this,NewAssemblyActivity.class));
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        getCount();
    }

    private void getCount(){
        String userId=getSharedPreferences(Config.mylogin_PREF_NAME,MODE_PRIVATE).getString(Config.password_SHARED_PREF,null);
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest=new StringRequest(Config.GET_TOTAL_COMPLAINT_COUNT+userId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                quantity = response;
                textViewQuantity.setText(quantity);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PickingActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.VISIBLE);
            }
        });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,MainActivity.class));
    }
}
