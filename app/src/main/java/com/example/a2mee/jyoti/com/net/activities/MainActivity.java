package com.example.a2mee.jyoti.com.net.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.a2mee.jyoti.R;
import com.example.a2mee.jyoti.com.net.adapters.MainMenuAdapter;
import com.example.a2mee.jyoti.com.net.utils.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.a2mee.jyoti.com.net.utils.Config.password_SHARED_PREF;

public class MainActivity extends Activity {
    final List<Username_list> Usernamelist = new ArrayList<>();
    String userid;
    Button Logout;
    String User_AxesList_Url = Config.BASE_URL + "user/permissions/";
    RecyclerView recyclerViewMainMenu;
    Button buttonGrnInspection,buttonLocationPutAway;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        recyclerViewMainMenu=(RecyclerView)findViewById(R.id.recyclerViewMainMenu);
//        buttonGrnInspection= findViewById(R.id.buttonGrnInspection);
//        buttonLocationPutAway= findViewById(R.id.buttonLocationPutAway);
//        buttonGrnInspection.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent Finel_material_inspActivity = new Intent(MainActivity.this, Finel_material_inspActivity.class);
//                MainActivity.this.startActivity(Finel_material_inspActivity);
//            }
//        });
//        buttonLocationPutAway.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,LocationPutAwayActivity.class));
//            }
//        });
        Logout = findViewById(R.id.scrollView2);
        SharedPreferences sharedPreferences = getSharedPreferences(Config.mylogin_PREF_NAME, Context.MODE_PRIVATE);
        userid = sharedPreferences.getString(password_SHARED_PREF, null);
        Toast.makeText(this, userid, Toast.LENGTH_SHORT).show();
        System.out.print(userid);
        getDataFromList();
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Logout!!!", Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences = getSharedPreferences(Config.mylogin_PREF_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(Config.password_SHARED_PREF);
                //editor.remove(configlogin.USER_SHARED_PREF);
                editor.clear();
                editor.commit();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void getDataFromList() {
        final ArrayList listMainMenu=new ArrayList<String>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, User_AxesList_Url+userid, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "onResponse: "+response);
                try {
                  JSONArray jsonArray=response.getJSONArray("permissions");

                   for (int i=0;i<jsonArray.length();i++){
                     String permissionValue=jsonArray.getJSONObject(i).getString("permissionValue");
                       if(permissionValue.equals("grnInspection")) {
                          listMainMenu.add(permissionValue);
                       }else if(permissionValue.equals("putaway")){
                           listMainMenu.add(permissionValue);
                       }else if(permissionValue.equals("picking")){
                           listMainMenu.add(permissionValue);
                       }
                   }
                    recyclerViewMainMenu.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewMainMenu.setAdapter(new MainMenuAdapter(MainActivity.this,listMainMenu));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(getApplicationContext(), "TimeoutError !!!", Toast.LENGTH_LONG).show();

                } else if (error instanceof AuthFailureError) {

                    Toast.makeText(getApplicationContext(), "AuthFailureError !!!", Toast.LENGTH_LONG).show();
                    //TODO
                } else if (error instanceof ServerError) {
                    Toast.makeText(getApplicationContext(), "ServerError !!!", Toast.LENGTH_LONG).show();
                    //TODO
                } else if (error instanceof NetworkError) {
                    Toast.makeText(getApplicationContext(), "NetworkError !!!", Toast.LENGTH_LONG).show();
                    //TODO
                } else if (error instanceof ParseError) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    //TODO
                }
                /*Log.d("@#@#", error.getMessage());*/
             //   Toast.makeText(MainActivity.this, "YOU DON'T HAVE ANY Permission !!!", Toast.LENGTH_SHORT).show();

            }
        });

       Volley.newRequestQueue(this).add(jsonObjectRequest);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}

