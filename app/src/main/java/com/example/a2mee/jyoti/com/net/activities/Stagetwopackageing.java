package com.example.a2mee.jyoti.com.net.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class Stagetwopackageing extends Activity {
    public List<itemDetails_listw> Materi1 = new ArrayList<>();
    float ssss;
    float igi;
    Button packingSCan, Stagesubmt, clearsubmt, GETList;
    TextView barcodedatapack, datepackd, itemdesp;
    RecyclerView recyclerView;
    RecyclerView stagepackingView;
    EditText noofpackp;
    String BARCODEDATA, Acceptqty1dd, datestring, ITEAMSESCRIPATION;
    String itemDetails1, packetSizeQty1, stageOnePackagingId1;
    Material_info_Adapter material_info_adapter;
    //    String  QRCODECODEDETAL = Config.BASE_URL + "packaging/scanPacketQrCodeByMobile";
    String packaging_addStage = Config.BASE_URL + "packaging/addStageTwoQrCodeDetailsFromMoileToWeb";
    private ArrayList<String> itemDetails;
    private ArrayList<String> itemId;
    private ArrayList<String> stageOnePackagingId;
    private ArrayList<String> packetSizeQty;
    private ArrayList<String> totall;
    private ArrayList<String> qtttty;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_stagetwopackageing);

        recyclerView = findViewById(R.id.stagepackingView);
        //Button
        GETList = findViewById(R.id.scannedp);
        clearsubmt = findViewById(R.id.clearsubmt);
        packingSCan = findViewById(R.id.packingSCan);
        Stagesubmt = findViewById(R.id.Stagesubmt);
        //TextView
        barcodedatapack = findViewById(R.id.barcodedatapack);
        datepackd = findViewById(R.id.datepackd);
        itemdesp = findViewById(R.id.itemdesp);
        // EditText
        noofpackp = findViewById(R.id.noofpackp);
        //code
        datestring = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        packingSCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BARCODEDATA = null;
                IntentIntegrator scan = new IntentIntegrator(Stagetwopackageing.this);
                scan.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                scan.initiateScan();
            }
        });

        clearsubmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Materi1.size();
                Materi1.clear();
                Materi1.removeAll(Materi1);
                material_info_adapter = new Material_info_Adapter(Materi1, Stagetwopackageing.this);
                recyclerView.setAdapter(material_info_adapter);
                material_info_adapter.notifyDataSetChanged();

                Toast.makeText(Stagetwopackageing.this, "Clear data ", Toast.LENGTH_SHORT).show();
            }
        });

        GETList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (barcodedatapack.getText().toString() == null || barcodedatapack.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Scan QR Code ", Toast.LENGTH_SHORT).show();
                } else {


                    /*Toast.makeText(Stagetwopackageing.this, "Get List", Toast.LENGTH_SHORT).show();*/
                    getlistofmaterial_deatal();
                    barcodedatapack.setText("");

                }
            }
        });
        noofpackp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.print(BARCODEDATA);
                if (BARCODEDATA == null) {
                    // noofpackp.setEnabled(false);
                    noofpackp.setText("");
                    Toast.makeText(Stagetwopackageing.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();

                }
                if (BARCODEDATA != null) {
                    //   noofpackp.setEnabled(true);
                    ITEAMSESCRIPATION = noofpackp.getText().toString().trim();
                    System.out.print(ITEAMSESCRIPATION);

                }
                if (ITEAMSESCRIPATION.isEmpty()) {

                    ITEAMSESCRIPATION = "1";

                }
                if (ITEAMSESCRIPATION == null) {
                    ITEAMSESCRIPATION = "1";
                }
            }
        });
        Stagesubmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Materi1.size();
                if (Materi1.size() == 0) {
                    Toast.makeText(Stagetwopackageing.this, "No Data Available !!", Toast.LENGTH_SHORT).show();

                } else {
                    submitdatatoserver();

                    Toast.makeText(Stagetwopackageing.this, "Submit", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void getlistofmaterial_deatal() {

        String QRCODECODEDETALqq = Config.BASE_URL + "packaging/scanPacketQrCodeByMobile";
        recyclerView = findViewById(R.id.stagepackingView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        if (BARCODEDATA != null) {
            //   noofpackp.setEnabled(true);
            ITEAMSESCRIPATION = noofpackp.getText().toString().trim();
            System.out.print(ITEAMSESCRIPATION);

        } else {
            Toast.makeText(getApplicationContext(), "Please Scan Qr Code ", Toast.LENGTH_SHORT).show();
        }
        if (ITEAMSESCRIPATION.isEmpty()) {

            ITEAMSESCRIPATION = "1";

        }
        if (ITEAMSESCRIPATION == null) {
            ITEAMSESCRIPATION = "1";
        }

        if (ITEAMSESCRIPATION != null) {

            final double qtyq = Double.parseDouble(ITEAMSESCRIPATION);
            double pk = Double.parseDouble(packetSizeQty1);
            final double catculation;
            catculation = qtyq * pk;

            ssss = (float) catculation;
            igi = (float) qtyq;

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading Data !!");
            progressDialog.show();
            //  JSONObject userdatadata = new JSONObject();
            QRCODECODEDETALqq = QRCODECODEDETALqq + "?qrCode=" + BARCODEDATA;
            JsonArrayRequest stqrrrqq = new JsonArrayRequest(Request.Method.GET, QRCODECODEDETALqq, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    progressDialog.hide();

                    try {
                        Log.d("Rot", response.toString());
    /*[{"stageTwoPackagingId":0,"itemDetails":"Ryton R-4-220NA","itemId":"RM0024",
            "stageOneQrCode":"005-180907-gr-3-123-RM0024","stageOnePackagingId":12,"packetQty":123,
            "itemAtualQty":0,"packetSizeQty":0,"shiftId":3,"date":null,"stageTwoQrCode":null}]*/
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObjectrrr = response.getJSONObject(i);
                            itemDetails_listw itemlist = new itemDetails_listw(
                                    jsonObjectrrr.getString("itemDetails"),
                                    jsonObjectrrr.getInt("packetQty"),
                                    jsonObjectrrr.getString("stageOneQrCode"),
                                    jsonObjectrrr.getInt("stageOnePackagingId"),
                                    igi,
                                    ssss
                            );

                            //    Materi1.clear();
                            Materi1.add(itemlist);
                            System.out.println(Materi1.get(i));
                            noofpackp.setText("");
/*
                            BARCODEDATA = null;
*/
                            itemdesp.setText("");
                            ITEAMSESCRIPATION = null;
                        }
                        progressDialog.dismiss();
                    } catch (JSONException e) {
                        progressDialog.dismiss();
                        e.printStackTrace();
                    }

                    material_info_adapter = new Material_info_Adapter(Materi1, Stagetwopackageing.this);
                    recyclerView.setAdapter(material_info_adapter);
                    material_info_adapter.notifyDataSetChanged();
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
                        Toast.makeText(getApplicationContext(), "ParseError !!!", Toast.LENGTH_LONG).show();
                        //TODO
                    }
                    Toast.makeText(getApplicationContext(), "Please Select Packet Qty", Toast.LENGTH_LONG).show();
                }
            });
            RequestQueue editel = Volley.newRequestQueue(getApplicationContext());
            editel.add(stqrrrqq);
        } else {
            Toast.makeText(getApplicationContext(), "Wrong_OR code", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String resultContents = result.getContents();
        if (resultContents == null) {
            Toast.makeText(Stagetwopackageing.this, "No Data Found !! Pleasse Scan Again", Toast.LENGTH_SHORT).show();
        } else {
            Seperate(resultContents);
            Toast.makeText(Stagetwopackageing.this, resultContents, Toast.LENGTH_LONG).show();
            resultContents.length();
            StringTokenizer st = new StringTokenizer(resultContents, "\n|");
            BARCODEDATA = st.nextToken();
            System.out.println(BARCODEDATA);
            Acceptqty1dd = BARCODEDATA.substring(4);
            barcodedatapack.setText(Acceptqty1dd);
            datepackd.setText(datestring);
            grtBarcodedetal();
            noofpackp.setText("");
            //Toast.makeText(Finel_material_inspActivity.this, BARCODEDATA, Toast.LENGTH_SHORT).show();
            Seperate(resultContents);
        }
        System.out.println("" + resultContents);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void Seperate(String resultContents) {
    }

    private void grtBarcodedetal() {

        if (BARCODEDATA.equalsIgnoreCase("false")) {
            Toast.makeText(Stagetwopackageing.this, "No Data Found !! Pleasse Scan Again", Toast.LENGTH_SHORT).show();
        } else {

            String QRCODECODEDETAL = Config.BASE_URL + "packaging/scanPacketQrCodeByMobile";
            //   String[] QRCOL = QRCODECODEDETAL.split("=");
            // QRCODECODEDETAL.indexOf(1);
            itemDetails = new ArrayList<>();
            itemId = new ArrayList<>();
            stageOnePackagingId = new ArrayList<>();
            packetSizeQty = new ArrayList<>();
            System.out.print(QRCODECODEDETAL);
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading Data !!");
            progressDialog.show();
            //  JSONObject userdatadata = new JSONObject();
            QRCODECODEDETAL = QRCODECODEDETAL + "?qrCode=" + BARCODEDATA;
            JsonArrayRequest stqqq = new JsonArrayRequest(Request.Method.GET, QRCODECODEDETAL, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    progressDialog.hide();
                    try {
                        Log.d("Rohitt", response.toString());
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            itemDetails.add(jsonObject.getString("itemDetails"));
                            itemId.add(jsonObject.getString("itemId"));
                            stageOnePackagingId.add(jsonObject.getString("stageOnePackagingId"));
                            packetSizeQty.add(jsonObject.getString("packetQty"));
                        }
                        progressDialog.dismiss();
                    } catch (JSONException e) {
                        progressDialog.dismiss();
                        e.printStackTrace();
                    }
                    String[] stringArrayrr = itemDetails.toArray(new String[0]);
                    itemDetails1 = Arrays.toString(stringArrayrr);
                    itemDetails1 = itemDetails1.substring(1, itemDetails1.length() - 1);

                    String[] stringArrayrr1 = stageOnePackagingId.toArray(new String[0]);
                    stageOnePackagingId1 = Arrays.toString(stringArrayrr1);
                    stageOnePackagingId1 = stageOnePackagingId1.substring(1, stageOnePackagingId1.length() - 1);

                    String[] stringArrayrr2 = packetSizeQty.toArray(new String[0]);
                    packetSizeQty1 = Arrays.toString(stringArrayrr2);
                    packetSizeQty1 = packetSizeQty1.substring(1, packetSizeQty1.length() - 1);

                    System.out.print(packetSizeQty1);
                    System.out.print(itemDetails1);
                    System.out.print(stageOnePackagingId1);
                    itemdesp.clearComposingText();
                    itemdesp.setText(itemDetails1);
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
                    Toast.makeText(getApplicationContext(), "Wrong_OR code", Toast.LENGTH_LONG).show();
                }
            });
            RequestQueue Qrcodeditel = Volley.newRequestQueue(getApplicationContext());
            Qrcodeditel.add(stqqq);
        }
    }

    private void submitdatatoserver() {

        if (BARCODEDATA.equalsIgnoreCase("false")) {
            Toast.makeText(Stagetwopackageing.this, "No Data Found !! Pleasse Scan Again", Toast.LENGTH_SHORT).show();
        }
        if (BARCODEDATA != null) {

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading Data !!");
            progressDialog.show();
            JSONObject scannedQrCode = new JSONObject();
            JSONArray jsonArrayq = new JSONArray();

            for (itemDetails_listw m : Materi1) {
                scannedQrCode = new JSONObject();
                try {
                    scannedQrCode.put("packetSizeQty", m.getPacketSizeQty());
                    scannedQrCode.put("stageOnePackagingId", m.getStageOnePackagingId());
                    scannedQrCode.put("itemAtualQty", m.getIgi());
                    scannedQrCode.put("packetQty", m.getSsss());
                    //itemAtualQty
                    jsonArrayq.put(scannedQrCode);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            JSONObject usata1 = new JSONObject();
            try {
                progressDialog.dismiss();
                usata1.put("list", jsonArrayq);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            RequestQueue ssssss = Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest ffffff = new JsonObjectRequest(Request.Method.POST, packaging_addStage, usata1, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("@#@ggg#", response.toString());
                    progressDialog.dismiss();
                    try {
                        Materi1.size();
                        Materi1.clear();
                        Materi1.removeAll(Materi1);
                        material_info_adapter = new Material_info_Adapter(Materi1, Stagetwopackageing.this);
                        recyclerView.setAdapter(material_info_adapter);
                        material_info_adapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Exception", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }


                }
            }, new Response.ErrorListener() {


                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Log.d("@#@#", error.getMessage());
                    //int c = error.networkResponse.statusCode;
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
                    Toast.makeText(getApplicationContext(), "Something is wrong", Toast.LENGTH_LONG).show();
                }
            });
            ssssss.add(ffffff);
        } else {
            Toast.makeText(getApplicationContext(), "Please Scan QR code", Toast.LENGTH_LONG).show();
        }
    }

}
