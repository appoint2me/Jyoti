package com.example.a2mee.jyoti.com.net.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.example.a2mee.jyoti.com.net.utils.Config;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends Activity implements View.OnClickListener {
    private static final int REQUEST = 112;
    String Username, Usernameuuu;
    Context mContext = this;
    EditText pass, username;
    Button login;
    ImageView Im;
    String LOGIN_URL = Config.BASE_URL + "login/authenticate";
    private boolean loginn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        Im = findViewById(R.id.wwDD);
        username = findViewById(R.id.eid);
        pass = findViewById(R.id.pass);
        if (Build.VERSION.SDK_INT >= 23) {
            String[] PERMISSIONS = {android.Manifest.permission.CAMERA,
                    android.Manifest.permission.INTERNET,
                    android.Manifest.permission.ACCESS_NETWORK_STATE,
                    android.Manifest.permission.ACCESS_WIFI_STATE,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            };
            if (!hasPermissions(mContext, PERMISSIONS)) {
                Log.d("@#@#", "@@@ IN IF hasPermissions");
                ActivityCompat.requestPermissions((Activity) mContext, PERMISSIONS, REQUEST);
            } else {
                Log.d("@#@#", "@@@ IN ELSE hasPermissions");
                isInternetOn();
            }
        } else {
            Log.d("@#@#", "@@@ IN ELSE  Build.VERSION.SDK_INT >= 23");
            isInternetOn();
        }

        login = findViewById(R.id.LOGING);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginn();


            }
        });
        login.setOnClickListener(this);
    }

    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        try {
            if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                    connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                    connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                    connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {

                // if connected with internet

                Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
                return true;

            } else if (
                    connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                            connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {

                Toast.makeText(this, " Not Connected ", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        catch (NullPointerException e){

        }
        return false;
    }


    public void loginn() {
        final ProgressDialog dialog = ProgressDialog.show(LoginActivity.this, "Login ",
                "Please wait...", true);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.show();
        //Getting values from edit texts
        final String uname = username.getText().toString();
        final String upassword = pass.getText().toString();
        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest stageRequest = new JsonObjectRequest(Request.Method.POST, LOGIN_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.print(response);
                try {
                    dialog.dismiss();
                    Log.d("@#@#", response.toString());
                    String userMenu = response.getString("id");
                    Usernameuuu = response.getString("id");
                    Log.d("@#@#", userMenu);
                    SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences(Config.mylogin_PREF_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(Config.password_SHARED_PREF, Usernameuuu);
                    System.out.print(Config.password_SHARED_PREF);
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("ID", uname);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(LoginActivity.this);
                dialog.dismiss();
                if (error instanceof TimeoutError) {
                    Toast.makeText(getApplicationContext(), "TimeoutError !!!", Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(getApplicationContext(), "AuthFailureError !!!", Toast.LENGTH_LONG).show();
                    //TODO
                } else if (error instanceof ServerError) {
                    Toast.makeText(getApplicationContext(), "Username and Password Does Not Match", Toast.LENGTH_LONG).show();
                    //Toast.makeText(getApplicationContext(), "ServerError !!!", Toast.LENGTH_LONG).show();
                    //TODO
                } else if (error instanceof NetworkError) {
                    Toast.makeText(getApplicationContext(), "NetworkError !!!", Toast.LENGTH_LONG).show();
                    //TODO
                } else if (error instanceof ParseError) {
                    Toast.makeText(getApplicationContext(), "ParseError !!!", Toast.LENGTH_LONG).show();
                    //TODO
                } else if (error instanceof NoConnectionError) {
                    Toast.makeText(getApplicationContext(), "Connection to Server Failed !! Please Contact Admin", Toast.LENGTH_LONG).show();
                }
             /*   alert.setTitle("Alert");
                alert.setMessage("Error Please Try Again !!!");
                alert.setPositiveButton("OK", null);
                alert.show();*/
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("id", uname);
                headers.put("password", upassword);
                return headers;
            }
        };
        rq.add(stageRequest);

    }

    @Override
    public void onClick(View view) {
        loginn();

    }

    private boolean hasPermissions(Context context, String[] permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("@#@#", "@@@ PERMISSIONS grant");
                    isInternetOn();
                } else {
                    Log.d("@#@#", "@@@ PERMISSIONS Denied");
                    Toast.makeText(mContext, "PERMISSIONS Denied", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}
