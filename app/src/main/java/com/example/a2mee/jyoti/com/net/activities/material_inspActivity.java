package com.example.a2mee.jyoti.com.net.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class material_inspActivity extends Activity {

    String BARCODEDATA, Acceptqty1dd, matitemId;
    TextView BARCODEE;
    Button butscanonline, Submithhh;
    String Inspect_Material_URL = Config.BASE_URL + "packaging/addFinalGoodData";
    String matitemDetails, matpacketQty, matstageTwoQrCode, matpoMstId;
    private ArrayList<String> itemId;
    private ArrayList<String> itemDetails;
    private ArrayList<String> packetQty;
    private ArrayList<String> stageTwoQrCode;
    private ArrayList<String> poMstId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.material_insp);

        BARCODEE = findViewById(R.id.barcodedataa);
        butscanonline = findViewById(R.id.butscanonline);
        Submithhh = findViewById(R.id.Submithhh);

        butscanonline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BARCODEDATA = null;
                IntentIntegrator scan = new IntentIntegrator(material_inspActivity.this);
                scan.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                scan.initiateScan();

            }
        });

        Submithhh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Submitetofg();
            }
        });

    }

    private void Submitetofg() {

        if (BARCODEDATA == null) {
            Toast.makeText(getApplicationContext(), "Please Scan QR Code", Toast.LENGTH_SHORT).show();
        } else {


            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading Data !!");
            progressDialog.show();
            JSONObject userdatadata1 = new JSONObject();
            try {
                userdatadata1.put("itemId", matitemId);
                userdatadata1.put("itemName", matitemDetails);
                userdatadata1.put("itemQuantity", matpacketQty);
                userdatadata1.put("boxQrcode", BARCODEDATA);
                userdatadata1.put("poMstId", matpoMstId);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            JsonObjectRequest stageRequestInspect_Materialdateoo = new JsonObjectRequest(Request.Method.POST, Inspect_Material_URL, userdatadata1, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("@#@ggg#", response.toString());
                    progressDialog.hide();
                    try {
                        Toast.makeText(material_inspActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                        BARCODEDATA = null;
                        BARCODEE.setText("");
                    } catch (Exception e) {
                        Toast.makeText(material_inspActivity.this, "Something  Exception !!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    BARCODEE.setText("");
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
                        Toast.makeText(getApplicationContext(), "ParseError !!!", Toast.LENGTH_LONG).show();
                        //TODO
                    }
                    Toast.makeText(material_inspActivity.this, "Something  Wrong", Toast.LENGTH_SHORT).show();
                }
            });
            RequestQueue Inspect = Volley.newRequestQueue(getApplicationContext());
            Inspect.add(stageRequestInspect_Materialdateoo);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String resultContents = result.getContents();
        if (resultContents == null) {
            Toast.makeText(material_inspActivity.this, "No Data Found !! Pleasse Scan Again", Toast.LENGTH_SHORT).show();
        } else {
            Seperate(resultContents);

            Toast.makeText(material_inspActivity.this, resultContents, Toast.LENGTH_LONG).show();
            resultContents.length();
            StringTokenizer st = new StringTokenizer(resultContents, "\n|");
            BARCODEDATA = st.nextToken();
            System.out.println(BARCODEDATA);
            System.out.println(BARCODEDATA);
            System.out.println(BARCODEDATA);
            Acceptqty1dd = BARCODEDATA.substring(4);
            BARCODEE.setText(Acceptqty1dd);
            System.out.println(Acceptqty1dd);
            //Toast.makeText(Finel_material_inspActivity.this, BARCODEDATA, Toast.LENGTH_SHORT).show();
            Seperate(resultContents);
            GETMaterialQTY();
        }
        System.out.println("" + resultContents);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void GETMaterialQTY() {

        itemId = new ArrayList<>();
        itemDetails = new ArrayList<>();
        packetQty = new ArrayList<>();
        stageTwoQrCode = new ArrayList<>();
        poMstId = new ArrayList<>();

        if (BARCODEDATA.equalsIgnoreCase("false")) {
            Toast.makeText(material_inspActivity.this, "No Data Found !! Pleasse Scan Again", Toast.LENGTH_SHORT).show();
        } else {
            String GrnQTY_URL = Config.BASE_URL + "packaging/getGeneratedStageTwoQrCodeForFGscreen";
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading Data !!");
            progressDialog.show();
            GrnQTY_URL = GrnQTY_URL + "?qrCode=" + BARCODEDATA;
            JsonArrayRequest stageRequestdate = new JsonArrayRequest(Request.Method.GET, GrnQTY_URL, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    progressDialog.hide();
                    JSONArray jr = response;
                    try {
                        Log.d("Rohitt", response.toString());
                        for (int i = 0; i < jr.length(); i++) {
                            JSONObject jsonObject = jr.getJSONObject(i);
                            itemId.add(jsonObject.getString("itemId"));
                            itemDetails.add(jsonObject.getString("itemDetails"));
                            packetQty.add(jsonObject.getString("packetQty"));
                            stageTwoQrCode.add(jsonObject.getString("stageTwoQrCode"));
                            poMstId.add(jsonObject.getString("poMstId"));
                        }
                        progressDialog.dismiss();
                    } catch (JSONException e) {
                        progressDialog.dismiss();
                        e.printStackTrace();
                    }

                    String[] stringArrayrr = itemId.toArray(new String[0]);
                    matitemId = Arrays.toString(stringArrayrr);
                    matitemId = matitemId.substring(1, matitemId.length() - 1);

                    String[] stringArrayrr1 = itemDetails.toArray(new String[0]);
                    matitemDetails = Arrays.toString(stringArrayrr1);
                    matitemDetails = matitemDetails.substring(1, matitemDetails.length() - 1);

                    String[] stringArrayrr2 = packetQty.toArray(new String[0]);
                    matpacketQty = Arrays.toString(stringArrayrr2);
                    matpacketQty = matpacketQty.substring(1, matpacketQty.length() - 1);

                    String[] stringArrayrr3 = stageTwoQrCode.toArray(new String[0]);
                    matstageTwoQrCode = Arrays.toString(stringArrayrr3);
                    matstageTwoQrCode = matstageTwoQrCode.substring(1, matstageTwoQrCode.length() - 1);

                    String[] stringArrayrr4 = poMstId.toArray(new String[0]);
                    matpoMstId = Arrays.toString(stringArrayrr4);
                    matpoMstId = matpoMstId.substring(1, matpoMstId.length() - 1);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    progressDialog.dismiss();
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
                        Toast.makeText(getApplicationContext(), "ParseError !!!", Toast.LENGTH_LONG).show();
                        //TODO
                    }
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
