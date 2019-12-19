package com.example.a2mee.jyoti.com.net.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import java.util.StringTokenizer;

public class MSendtoFG extends Activity {
    Button butscanfinelqc, Submithhhfqc;
    TextView barcodedataafqc, qtty;
    String BARCODEDATA, BARCODEDATAa, tlotQty, tlotQtyvv;
    EditText LotQty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_mainline_finel_inspection);
        butscanfinelqc = findViewById(R.id.butscanfinelqc);
        Submithhhfqc = findViewById(R.id.Submithhhfqc);
        barcodedataafqc = findViewById(R.id.barcodedataafqc);
        LotQty = findViewById(R.id.qtyatstoesed);
        qtty = findViewById(R.id.qoesed);
        butscanfinelqc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LotQty.setText("");
                IntentIntegrator scan = new IntentIntegrator(MSendtoFG.this);
                scan.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                scan.initiateScan();

            }
        });

        Submithhhfqc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLotQty();


                if (tlotQty.isEmpty() || tlotQty.equals("") || tlotQty.equals("0")) {
                    Toast.makeText(getApplicationContext(), "Please Insert Qty", Toast.LENGTH_SHORT).show();
                } else {

                    int A = Integer.parseInt(tlotQty);
                    int B = Integer.parseInt(tlotQtyvv);

                    if (B >= A) {
                        Submitedatatoserverr();
                    } else {
                        Toast.makeText(getApplicationContext(), "QTY IS Grater than Available", Toast.LENGTH_LONG).show();
                        tlotQty = "0";
                    }
                }

            }
        });

        LotQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /*    System.out.print(BARCODEDATA);
                if (BARCODEDATA == null) {
                    LotQty.setText("");
                    Toast.makeText(MSendtoFG.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
                } else {
                    tlotQty = LotQty.getText().toString();
                    System.out.print(tlotQty);
                    if (tlotQty.isEmpty()) {
                        tlotQty ="0";
                        LotQty.setText("");
                    }
                }
            */
            }
        });
    }

    private void Submitedatatoserverr() {

      int  validation =Integer.parseInt(tlotQty);
      if(validation > 0){
        String Submit_URL = Config.BASE_URL + "finishGood";
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data !!");
        progressDialog.show();
        Submit_URL = Submit_URL + "?qrcode=" + BARCODEDATA + "&quantity=" + tlotQty;
        JsonArrayRequest stageRequestdateque = new JsonArrayRequest(Request.Method.PUT, Submit_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressDialog.hide();
                try {
                    Log.d("Rohitt", response.toString());
                    barcodedataafqc.setText("");
                    qtty.setText("");
                    LotQty.setText("");
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                }

                catch (Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Something  Wrong", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Something  Wrong", Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue qddd = Volley.newRequestQueue(getApplicationContext());
        qddd.add(stageRequestdateque);
      }else {
          Toast.makeText(getApplicationContext(), "Please Insert Qty", Toast.LENGTH_LONG).show();
        }

    }

    private void getLotQty() {
        System.out.print(BARCODEDATA);
        if (BARCODEDATA == null) {
            LotQty.setText("");
            Toast.makeText(MSendtoFG.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
            tlotQty = LotQty.getText().toString();
        } else {
            tlotQty = LotQty.getText().toString();
            System.out.print("@#@#" + tlotQty);
            if (tlotQty.isEmpty()) {
                tlotQty = "0";
                LotQty.setText("");
            }
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String resultContents = result.getContents();
        if (resultContents == null) {
            Toast.makeText(MSendtoFG.this, "No Data Found !! Pleasse Scan Again", Toast.LENGTH_SHORT).show();
        } else {
            Seperate(resultContents);
            Toast.makeText(MSendtoFG.this, resultContents, Toast.LENGTH_LONG).show();
            resultContents.length();
            StringTokenizer st = new StringTokenizer(resultContents, "\n|");
            BARCODEDATA = st.nextToken();
            System.out.println(BARCODEDATA);
            BARCODEDATAa = BARCODEDATA.substring(4);
            barcodedataafqc.setText(BARCODEDATAa);
            //Toast.makeText(Finel_material_inspActivity.this, BARCODEDATA, Toast.LENGTH_SHORT).show();
            Seperate(resultContents);
            GETMaterialQTYq();
        }
        System.out.println("" + resultContents);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void GETMaterialQTYq() {
        if (BARCODEDATA.equalsIgnoreCase("false")) {
            Toast.makeText(MSendtoFG.this, "No Data Found !! Pleasse Scan Again", Toast.LENGTH_SHORT).show();
        } else {

            String GrnQTY_URL = Config.BASE_URL + "finishGood/qty";
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading Data !!");
            progressDialog.show();
            //  JSONObject userdatadata = new JSONObject();
            GrnQTY_URL = GrnQTY_URL + "?qrcode=" + BARCODEDATA;
            JsonObjectRequest stageRequestdate = new JsonObjectRequest(Request.Method.GET, GrnQTY_URL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    progressDialog.hide();
                    try {
                        Log.d("Rohitt", response.toString());
                        for (int i = 0; i < response.length(); i++) {
                            /*JSONObject jsonObject = response.getJSONObject(i);*/
                            tlotQtyvv = response.getString("qty");
                        }
                        Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
                        //   LotQty.setText(tlotQty);
                        progressDialog.dismiss();
                    } catch (JSONException e) {
                        progressDialog.dismiss();
                        e.printStackTrace();
                    }

                    qtty.setText(tlotQtyvv);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Something  Wrong", Toast.LENGTH_LONG).show();

                }
            });
            RequestQueue ddrq = Volley.newRequestQueue(getApplicationContext());
            ddrq.add(stageRequestdate);


        }
    }

    private void Seperate(String resultContents) {
    }


}
