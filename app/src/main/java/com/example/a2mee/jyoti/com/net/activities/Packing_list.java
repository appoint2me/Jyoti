package com.example.a2mee.jyoti.com.net.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
import java.util.List;
import java.util.StringTokenizer;

public class Packing_list extends Activity {
    public List<Material_list> Materialname1 = new ArrayList<>();
    Button buttonscanppiki, Pikingsubmtv;
    Spinner Material_name;
    String BARCODEDATAp, Material_name1, Material_nameselect, mtlIssuSummeryIdaa, status11, mtlQtyaa, mtlQty11, status112;
    // String Materialname_listURL = Config.BASE_URL + "materialIssue/itemListforPiking";
    // String MaterialIssue = Config.BASE_URL + "materialIssue/matlIssuSummeryList";
    //String MaterialSubmit_Url = Config.BASE_URL + "materialIssue/stockOutMaterial";
    String Responseff;
    ArrayList<Integer> mtlQty = new ArrayList<Integer>();
    ArrayList<Integer> stockInId = new ArrayList<Integer>();
    ArrayList<String> status = new ArrayList<String>();
    Material_nameadapter adapter;
    /*public List<Material_list> Materialname1;*/
    private ArrayList<String> materialId;
    private ArrayList<String> mtlIssuSummeryId;
    private ArrayList<String> materialDtl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_packing_list);
        Material_name();
        buttonscanppiki = findViewById(R.id.buttonscanppiki);
        buttonscanppiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator scan = new IntentIntegrator(Packing_list.this);
                scan.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                scan.initiateScan();
            }
        });

/*        status11 = getIntent().getStringExtra("status");
        System.out.println(status);
        mtlQtyaa = getIntent().getStringExtra("qty12");
        System.out.println(status);*/
        Pikingsubmtv = findViewById(R.id.Pikingsubmt);
        Pikingsubmtv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Submitebutton();
            }
        });
    }

    private void Submitebutton() {


        String MaterialSubmit_Url = Config.BASE_URL + "materialIssue/stockOutMaterial";
        Materialname1.size();
        if (Materialname1.size() == 0) {
            Toast.makeText(getApplicationContext(), "Please select Valid QR CODE", Toast.LENGTH_LONG).show();
        } else {
            Materialname1.size();
            System.out.print(Materialname1.get(0).getItemName());
            for (Material_list m : Materialname1) {
                mtlQty11 = m.getQty();
                status11 = m.getStatus();
                System.out.println(mtlQty11);
                System.out.println(status11);
            }
            if (mtlQty11.equalsIgnoreCase("")) {
                Materialname1.clear();
                Toast.makeText(getApplicationContext(), "invalid QR CODE of material", Toast.LENGTH_LONG).show();
            }

            if (status11.equalsIgnoreCase("invalid")) {
                Materialname1.clear();
                Toast.makeText(getApplicationContext(), "Reject_ QR CODE material", Toast.LENGTH_LONG).show();
            }
            if (status11.equalsIgnoreCase("reject")) {
                Materialname1.clear();
                Toast.makeText(getApplicationContext(), "invalid QR CODE material", Toast.LENGTH_LONG).show();
            }
            if (mtlQty11.equalsIgnoreCase("0")) {
                Materialname1.clear();
                Toast.makeText(getApplicationContext(), "Reject QR CODE material", Toast.LENGTH_LONG).show();
            } else {
                JSONObject scannedQrCode = new JSONObject();
                JSONArray jsonArray = new JSONArray();
                for (Material_list m : Materialname1) {
                    scannedQrCode = new JSONObject();
                    try {
                        scannedQrCode.put("mtlQty", m.getQty());
                        scannedQrCode.put("status", m.getStatus());
                        jsonArray.put(scannedQrCode);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                int resultmtlIssuSummeryIdaa = Integer.parseInt(mtlIssuSummeryIdaa);
                JSONObject userdatadata1 = new JSONObject();
                try {
                    userdatadata1.put("mtlIsuuSummeryId", resultmtlIssuSummeryIdaa);
                    userdatadata1.put("materialId", Material_nameselect);
                    userdatadata1.put("qrcode", BARCODEDATAp);
                    userdatadata1.put("list", jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestQueue ddrq = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest stageRequestdatezz = new JsonObjectRequest(Request.Method.POST, MaterialSubmit_Url, userdatadata1, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("@#@ggg#", response.toString());
                        // Responseff= response.toString().substring(0,200);
                        try {
                            // String Responseffg = response.getString("http respons");
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Exception", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                        Material_name();
                        Materialname1.clear();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //   Log.d("@#@#", error.getMessage());
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


                        Toast.makeText(getApplicationContext(), "Something Went Wrong !!!", Toast.LENGTH_LONG).show();
                    }
                });

                ddrq.add(stageRequestdatezz);
            }

        }
    }

    private void Material_name() {
        String Materialname_listURL = Config.BASE_URL + "materialIssue/itemListforPiking";
        Material_name = findViewById(R.id.spinddd);
        materialId = new ArrayList<>();
        mtlIssuSummeryId = new ArrayList<>();
        materialDtl = new ArrayList<>();

        //   final ArrayList<String> Stringdatar = new ArrayList<String>();
        JSONObject userdatadata = new JSONObject();
        RequestQueue ddrq = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest stageRequestdate = new JsonArrayRequest(Request.Method.GET, Materialname_listURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    Log.d("@rOGH@", response.toString());
                    JSONArray jsonArray = response;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        materialId.add(jsonObject.getString("materialId"));
                        mtlIssuSummeryId.add(jsonObject.getString("mtlIssuSummeryId"));
                        materialDtl.add(jsonObject.getString("materialDtl"));
                        //  Toast.makeText(getApplicationContext(),"SUCCESS", Toast.LENGTH_LONG).show();
                        System.out.print(materialId);
                        System.out.print(materialDtl);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ArrayAdapter<String> spinnerArrayrr = new ArrayAdapter<String>(Packing_list.this, android.R.layout.simple_spinner_dropdown_item, materialDtl
                );
                Material_name.setAdapter(spinnerArrayrr);
                System.out.print(response.toString());
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Packing_list.this, "Something Went Wrong !!!", Toast.LENGTH_SHORT).show();
                Log.d("@#@#", error.getMessage());
            }
        });
        ddrq.add(stageRequestdate);
        Material_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data = (String) parent.getItemAtPosition(position);
                Log.d("@@@@", data);
                if (data.equalsIgnoreCase("select Material")) {
                } else {
                    Log.d("@#@#1", String.valueOf(Material_name.getItemIdAtPosition(position)));
                    Material_nameselect = materialId.get(position);
                    mtlIssuSummeryIdaa = mtlIssuSummeryId.get(position);
                    Material_name1 = materialDtl.get((int) Material_name.getItemIdAtPosition(position));
                    Log.d("@#", data);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String resultContents = result.getContents();
        if (resultContents == null) {
            Toast.makeText(Packing_list.this, "No Data Found !! Pleasse Scan Again", Toast.LENGTH_SHORT).show();
        } else {
            Seperate(resultContents);
            // Toast.makeText(Packing_list.this, resultContents, Toast.LENGTH_LONG).show();
            resultContents.length();
            StringTokenizer st = new StringTokenizer(resultContents, "\n|");
            BARCODEDATAp = st.nextToken();
            System.out.println(BARCODEDATAp);
            // Toast.makeText(Packing_list.this, BARCODEDATAp, Toast.LENGTH_SHORT).show();
            Seperate(resultContents);
        }
        if (BARCODEDATAp != null) {
            Material_nameLIst();
        }
        System.out.println("" + resultContents);

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void Material_nameLIst() {

        String MaterialIssue = Config.BASE_URL + "materialIssue/matlIssuSummeryList";
        if (BARCODEDATAp != null) {
            // get the reference of RecyclerView
            final RecyclerView recyclerView = findViewById(R.id.PickingView);
            // set a LinearLayoutManager with default orientation
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            JSONObject userdatadata = new JSONObject();
            MaterialIssue = MaterialIssue + "?" + "qrCode=" + BARCODEDATAp + "&" + "itemMStId=" + Material_nameselect;
            JsonArrayRequest MaterialIssueRequestdate = new JsonArrayRequest(Request.Method.GET, MaterialIssue, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        Log.d("@#@#", response.toString());
                        JSONArray jsonArray = response;
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObjectss = jsonArray.getJSONObject(i);
                            Material_list item = new Material_list
                                    (jsonObjectss.getString("status"),
                                            jsonObjectss.getString("qty"),
                                            jsonObjectss.getString("itemName"),
                                            jsonObjectss.getString("itemId"),
                                            jsonObjectss.getString("barCode"),
                                            jsonObjectss.getString("mtlIssuSumId"));
                            Materialname1.clear();
                            Materialname1.add(item);
                            System.out.println(Materialname1.get(i));
                        }
                        adapter = new Material_nameadapter(Materialname1, Packing_list.this);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        //lv.setDivider(new ColorDrawable(Color.parseColor("#FF4A4D93")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Packing_list.this, "Something Went Wrong !!!", Toast.LENGTH_SHORT).show();
                    Log.d("@#@#", error.getMessage());
                }
            });
            RequestQueue materiallist = Volley.newRequestQueue(getApplicationContext());
            materiallist.add(MaterialIssueRequestdate);
        }
    }

    private void Seperate(String resultContents) {

    }

    @Override
    public void onBackPressed() {
        this.finish();
        Intent ProducationOrdar_countll = new Intent(Packing_list.this, MainActivity.class);
        startActivity(ProducationOrdar_countll);
        super.onBackPressed();
    }
}
