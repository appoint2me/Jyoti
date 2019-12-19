package com.example.a2mee.jyoti.com.net.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.a2mee.jyoti.R;
import com.example.a2mee.jyoti.com.net.utils.Config;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LocationPutAwayScanActivity extends AppCompatActivity {
    Button buttonScanStorageBin,buttonScanItem,buttonCheck,buttonSave;
    private static final String TAG = "LocationPutAwayActivity";
    int id;
    String resourceName;
    String itemMstId,storageBinId,storageBinCode;
    String grnNo,qrCodeString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_put_away_scan);
        Intent intent=getIntent();
        grnNo=intent.getExtras().getString("GR_NO");
        qrCodeString=intent.getExtras().getString("QR_CODE");
        String item = qrCodeString;
        try {
            String[] itemArray = item.split("//");
            grnNo = itemArray[2];
            itemMstId = itemArray[3];
        }
        catch (ArrayIndexOutOfBoundsException e){

        }catch (NullPointerException e){

        }

        buttonScanStorageBin= findViewById(R.id.buttonStorageBin);
        buttonCheck= findViewById(R.id.buttonCheck);
        buttonSave= findViewById(R.id.buttonSave);
        buttonCheck.setEnabled(false);
        buttonSave.setEnabled(false);
        buttonScanStorageBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resourceName = v.getResources().getResourceEntryName(v.getId());
                IntentIntegrator scan = new IntentIntegrator(LocationPutAwayScanActivity.this);
                scan.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                scan.initiateScan();
            }
        });

        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkQrCode();
            }
        });
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeQrCode();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String resultContents = result.getContents();
        try {
//            if (resultContents == null) {
//            } else {
//                if (resourceName.equalsIgnoreCase("buttonItem")) {
//                    String item = resultContents;
//                    String[] itemArray = item.split("-");
//                    grnNo = itemArray[2].toString();
//                    itemMstId = itemArray[3].toString();
//                    Toast.makeText(this, itemArray[2], Toast.LENGTH_SHORT).show();
//                    Toast.makeText(this, itemArray[3], Toast.LENGTH_SHORT).show();
//
//                } else if (resourceName.equalsIgnoreCase("buttonStorageBin")) {
                    String item = resultContents;
                    String[] itemArray = item.split("-");
                    storageBinCode= itemArray[0]+"-"+itemArray[1]+"-"+itemArray[2];
                    buttonCheck.setEnabled(true);


               // }

        }catch (ArrayIndexOutOfBoundsException e){
            Toast.makeText(this, "Wrong Qr code", Toast.LENGTH_SHORT).show();
        }
        catch (NullPointerException e){
            Toast.makeText(this, "Scan again", Toast.LENGTH_SHORT).show();

        }
        System.out.println("" + resultContents);
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void checkQrCode(){
        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET
                , Config.URL_CHECK_STORAGE_BIN_QR_CODE + "itemMstId=" + itemMstId + "&storageBinCode=" + storageBinCode
                , null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response.length()==0){

                }else{
                    Toast.makeText(LocationPutAwayScanActivity.this, "Matched", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: Response"+response.toString());
                    buttonSave.setEnabled(true);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error.getMessage());
                Toast.makeText(LocationPutAwayScanActivity.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsonArrayRequest);

    }
    private void storeQrCode(){
        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET
                , Config.URL_CHECK_ITEM_QR_CODE + "grnNo=" + grnNo + "&storageBinCode=" + storageBinCode
                , new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
               // Toast.makeText(LocationPutAwayScanActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                try {
                    String message=response.getString("message");
                    if(message.equalsIgnoreCase("Success")){
                        Toast.makeText(LocationPutAwayScanActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LocationPutAwayScanActivity.this,LocationPutAwayActivity.class));
                    }else{
                        Toast.makeText(LocationPutAwayScanActivity.this, "Failed", Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LocationPutAwayScanActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(jsonObjectRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LocationPutAwayScanActivity.this,LocationPutAwayActivity.class));

    }
}
