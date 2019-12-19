package com.example.a2mee.jyoti.com.net.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
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
import java.util.StringTokenizer;

import static com.example.a2mee.jyoti.com.net.utils.Config.password_SHARED_PREF;

public class Finel_material_inspActivity extends Activity {
    int us1;
    Button scannbutton, saveButton, saveanother;
    TextView barcodedataaf, oprater, LotQty;
    EditText holdreson, holdQty, rejectqty, acceptQty;
    CheckBox ck;
    Spinner RejectionResion;
    String BARCODEDATA, lotQty, userid, fffv, Acceptqty1dd, lotQtyqq, ResionIdq;

    // String GrnQTY_URL = Config.BASE_URL + "grn/scanQr";
    String Inspect_Material_URL = Config.BASE_URL + "grn/inspectMateril";
    String Reasonlist_URL = Config.BASE_URL + "grn/rejectResonList";
    String Qtkk;
    String Sumofallqty;
    String holdreson1;
    String holdQty1;
    String rejectqty1;
    String Acceptqty;
    String reasonnameq, useridu;
    double Lotqty1, resultA, resultB, resultC, totalqty;
    int finelqty;
    private ArrayList<String> iddkk;
    private ArrayList<String> ResionId;
    private ArrayList<String> reasonname;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_finel_material_insp);
        barcodedataaf = findViewById(R.id.barcodedataaf);
        //ck = findViewById(R.id.bagg1);
        scannbutton = findViewById(R.id.butscanfinel);
        saveButton = findViewById(R.id.Submithhhf);
        saveanother = findViewById(R.id.Submitkkf);
        LotQty = findViewById(R.id.lotqttyj);
        //Accept = findViewById(R.id.accepiidf);
        holdreson = findViewById(R.id.holdresf);
        holdQty = findViewById(R.id.Holdf);
        rejectqty = findViewById(R.id.Rejectf);
        acceptQty = findViewById(R.id.acceptQty);
        oprater = findViewById(R.id.opraterf);
        RejectionResion = findViewById(R.id.Rejectionf);
        SharedPreferences sharedPreferences = getSharedPreferences(Config.mylogin_PREF_NAME, Context.MODE_PRIVATE);
        useridu = sharedPreferences.getString(password_SHARED_PREF, null);
        System.out.print(useridu);


        holdQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.print(BARCODEDATA);
               /* if (BARCODEDATA == null) {
                    //    holdQty.setEnabled(false);
                    holdQty.setText("");
                    Toast.makeText(Finel_material_inspActivity.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
                } else {
                    holdQty.setEnabled(true);
                    // showMyDialog();
                    holdQty1 = holdQty.getText().toString().trim();
                    System.out.print(holdQty1);
                    if (holdQty1 != null) {
                        resultA = Double.parseDouble(holdQty1);
                    } else {
                        resultA = 0;
                    }
                    System.out.print(holdQty1);
                }*/
            }
        });
        holdreson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* System.out.print(BARCODEDATA);
                if (BARCODEDATA == null) {
                    //    holdreson.setEnabled(false);
                    holdreson.setText("");
                    Toast.makeText(Finel_material_inspActivity.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
                } else {
                    holdreson.setEnabled(true);
                    holdreson1 = holdreson.getText().toString().trim();
                }*/
            }
        });

        rejectqty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.print(BARCODEDATA);
                if (BARCODEDATA == null) {
                    //  rej
                    // ectqty.setEnabled(false);
                    rejectqty.setText("");
                    Toast.makeText(Finel_material_inspActivity.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
                } else {
                    // rejectqty.setEnabled(true);
                    rejectqty1 = rejectqty.getText().toString().trim();
                    System.out.print(rejectqty1);
                    if (rejectqty1.isEmpty()) {
                        resultB = 0;

                    } else {
                        resultB = Double.parseDouble(rejectqty1);
                    }
                    Reasonlist_m();
                }
            }
        });
        acceptQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*System.out.print(BARCODEDATA);
                if (BARCODEDATA == null) {
                    acceptQty.setText("");
                    Toast.makeText(Finel_material_inspActivity.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
                } else {

                    Acceptqty = acceptQty.getText().toString().trim();
                    System.out.print(Acceptqty);
                    if (Acceptqty.isEmpty()) {
                        resultC = 0;
                    } else {
                        resultC = Double.parseDouble(Acceptqty);
                    }
                    System.out.print(resultC);
                }*/
            }
        });

        scannbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BARCODEDATA = null;

                IntentIntegrator scan = new IntentIntegrator(Finel_material_inspActivity.this);
                scan.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                scan.initiateScan();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getAcceptQTY();
                getHoldQty();
                getHoldreason();

                System.out.print(BARCODEDATA);
                if (BARCODEDATA == null) {
                    Toast.makeText(Finel_material_inspActivity.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
                } else {
                    System.out.print(rejectqty1);
                    if (lotQty.equalsIgnoreCase(null)) {
                        Toast.makeText(Finel_material_inspActivity.this, "No Data Found !! Pleasse Scan Again", Toast.LENGTH_SHORT).show();
                    } else {
                        Lotqty1 = Double.parseDouble(lotQty);
                    }
                    totalqty = resultA + resultB + resultC;
                    if (Lotqty1 >= totalqty) {
                        us1 = (int) Double.parseDouble(String.valueOf(totalqty));
                        if (us1 == 0) {
                            Toast.makeText(Finel_material_inspActivity.this, "Quantity is 0 ", Toast.LENGTH_SHORT).show();
                        } else {

/*
                            Toast.makeText(getApplicationContext(), holdreson1, Toast.LENGTH_SHORT).show();
*/
                            SAvedata();
                        }
                    } else {
                        Refresh();
                        Toast.makeText(Finel_material_inspActivity.this, "Quantity is grater than Lot Quantity", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        saveanother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAcceptQTY();
                getHoldQty();
                getHoldreason();
                System.out.print(BARCODEDATA);
                if (BARCODEDATA == null) {
                    Toast.makeText(Finel_material_inspActivity.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
                } else {
                    System.out.print(rejectqty1);
                    if (lotQty.equalsIgnoreCase(null)) {
                        Toast.makeText(Finel_material_inspActivity.this, "No Data Found !! Pleasse Scan Again", Toast.LENGTH_SHORT).show();
                    } else {
                        Lotqty1 = Double.parseDouble(lotQty);
                    }
                    totalqty = resultA + resultB + resultC;
                    if (Lotqty1 >= totalqty) {
                        us1 = (int) Double.parseDouble(String.valueOf(totalqty));
                        if (us1 == 0) {
                            Toast.makeText(Finel_material_inspActivity.this, "Quantity is 0 ", Toast.LENGTH_SHORT).show();
                        } else {
                            SAveanother();
                        }
                    } else {
                        Toast.makeText(Finel_material_inspActivity.this, "Quantity is grater than Lot Quantity", Toast.LENGTH_SHORT).show();

                        Refresh();
                    }
                }

            }
        });
    }

    private void getHoldQty() {

        if (BARCODEDATA == null) {
            //    holdQty.setEnabled(false);
            holdQty.setText("");
            Toast.makeText(Finel_material_inspActivity.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
        } else {
            holdQty.setEnabled(true);
            // showMyDialog();
            holdQty1 = holdQty.getText().toString().trim();
            System.out.print(holdQty1);
            if (holdQty1 == null || holdQty1.isEmpty()) {
                resultA = 0.0;
            } else {
                resultA = Double.parseDouble(holdQty1);
            }
            /*if (holdQty1 != null) {
                resultA = Double.parseDouble(holdQty1);
            } else {
                resultA = 0.0;
            }*/
            System.out.print(holdQty1);
        }
    }

    public void getHoldreason() {
        if (BARCODEDATA == null) {
            //    holdreson.setEnabled(false);
            holdreson.setText("");
            Toast.makeText(Finel_material_inspActivity.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
        } else {
            holdreson.setEnabled(true);
            holdreson1 = holdreson.getText().toString().trim();
        }
    }

    public void getAcceptQTY() {
        System.out.print(BARCODEDATA);
        if (BARCODEDATA == null) {
            acceptQty.setText("");
            Toast.makeText(Finel_material_inspActivity.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
        } else {

            Acceptqty = acceptQty.getText().toString().trim();
            System.out.print(Acceptqty);
            if (Acceptqty.isEmpty()) {
                resultC = 0;
            } else {
                resultC = Double.parseDouble(Acceptqty);
            }
            System.out.print(resultC);
        }
    }

    private void Refresh() {

        holdreson.setText("");
        holdQty.setText("");
        rejectqty.setText("");
        acceptQty.setText("");
        resultC = 0;
        resultB = 0;
        resultA = 0;
        /*Reasonlist_m();*/
    }

  /*  public void Acceptfun(View view) {
        if (BARCODEDATA != null) {
            EditText Accept = (EditText) findViewById(R.id.accepiidf);

            *//*Accept.setEnabled(true);*//*
            Accept.setInputType(InputType.TYPE_CLASS_TEXT);
            *//*Accept.setFocusable(true);*//*
        } else {
            EditText Accept = (EditText) findViewById(R.id.accepiidf);
            *//*Accept.setEnabled(false);*//*
            Accept.setInputType(InputType.TYPE_NULL);
            *//*Accept.setFocusable(false);*//*
        }

    }*/

    private void Reasonlist_m() {
        alertDialog=new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Loading...");
        alertDialog.setMessage("Please wait...");
        alertDialog.setCancelable(false);

        alertDialog.show();


        ResionId = new ArrayList<>();
        reasonname = new ArrayList<>();
        //   final ArrayList<String> Stringdatar = new ArrayList<String>();
        //JSONObject userdatadata = new JSONObject();
        RequestQueue ddrq = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest stageRequestdatezz = new JsonArrayRequest(Request.Method.GET, Reasonlist_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                alertDialog.dismiss();
                try {
                    reasonname.add("Please_Select");
                    // Log.d("@rOGH@", response.toString());
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObjects = response.getJSONObject(i);
                        ResionId.add(jsonObjects.getString("rejId"));
                        reasonname.add(jsonObjects.getString("reason"));
                        //Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_LONG).show();
                        System.out.print(ResionId);
                        System.out.print(reasonname);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    alertDialog.dismiss();
                }
                ArrayAdapter<String> spinnerArrayrr = new ArrayAdapter<String>(Finel_material_inspActivity.this, android.R.layout.simple_spinner_dropdown_item, reasonname);
                RejectionResion.setAdapter(spinnerArrayrr);
                System.out.print(response.toString());
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("@#@#", error.getMessage());
                alertDialog.dismiss();
            }
        });

        ddrq.add(stageRequestdatezz);
        RejectionResion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String datatt = (String) parent.getItemAtPosition(position);
                Log.d("@@@@", datatt);
                if (datatt == "Please_Select") {
                    ResionIdq = "0";
                } else {
                    Log.d("@#@#1", String.valueOf(RejectionResion.getItemIdAtPosition(position)));
                    ResionIdq = ResionId.get(position - 1);

                    //  reasonnameq = reasonname.get((int) RejectionResion.getItemIdAtPosition(position));
                    Log.d("@#", datatt);


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void SAveanother() {

        if (BARCODEDATA == null) {
            Toast.makeText(Finel_material_inspActivity.this, "SCAN QR CODE", Toast.LENGTH_SHORT).show();
        }

        if (Lotqty1 >= totalqty) {
            if (ResionIdq == "0") {
                Toast.makeText(Finel_material_inspActivity.this, "Please Select Reason ", Toast.LENGTH_SHORT).show();

            } else {

                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Loading Data !!");
                progressDialog.show();
                JSONObject userdatadata11 = new JSONObject();
                int userid1 = Integer.parseInt(userid);
                try {
                    userdatadata11.put("itmLotId", userid1);
                    userdatadata11.put("acceptqty", resultC);
                    userdatadata11.put("rejqty", resultB);
                    userdatadata11.put("rejtreasonid", ResionIdq);
                    userdatadata11.put("holdqty", resultA);
                    userdatadata11.put("holdreason", holdreson1);
                    userdatadata11.put("userId", useridu);
                    userdatadata11.put("barCode", BARCODEDATA);
                    Log.d("@#@#", userdatadata11.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest stageRequestInspect_Materialdate1 = new JsonObjectRequest(Request.Method.POST, Inspect_Material_URL, userdatadata11, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("@#@ggg#", response.toString());
                        progressDialog.dismiss();
                        System.out.print(response);
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                Qtkk = response.getString("qty");
                            }
                            if (Qtkk != null) {
                                Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
                            }

                            resultC = 0;
                            resultB = 0;
                            resultA = 0;
                            holdreson.setText("");
                            holdQty.setText("");
                            rejectqty.setText("");
                            acceptQty.setText("");
                            RejectionResion.setAdapter(null);

                            /*Reasonlist_m();*/
                            Qtkk.indexOf(".", 2);
                            LotQty.setText(Qtkk);
                            // Toast.makeText(Finel_material_inspActivity.this,"SUCCESS" , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            Toast.makeText(Finel_material_inspActivity.this, "Something  Exception !!!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
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
                            Toast.makeText(getApplicationContext(), "ParseError !!!", Toast.LENGTH_LONG).show();
                            //TODO
                        }
                        LotQty.setText("");
                        barcodedataaf.setText("");
                        progressDialog.dismiss();
                        Toast.makeText(Finel_material_inspActivity.this, "Something  Wrong !!!", Toast.LENGTH_SHORT).show();
                    }
                });
                RequestQueue Inspect = Volley.newRequestQueue(getApplicationContext());
                Inspect.add(stageRequestInspect_Materialdate1);

            }
        } else {
            Toast.makeText(Finel_material_inspActivity.this, "Quantity is grater than Lot Quantity", Toast.LENGTH_SHORT).show();
        }
    }

    private void SAvedata() {

        if (BARCODEDATA == null) {
            Toast.makeText(Finel_material_inspActivity.this, "SCAN QR CODE", Toast.LENGTH_SHORT).show();
        }
        if (Lotqty1 >= totalqty) {
            if (ResionIdq == "0") {
                Toast.makeText(getApplicationContext(), "Please Select Reason", Toast.LENGTH_SHORT).show();

            } else {


                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Loading Data !!");
                progressDialog.show();

                JSONObject userdatadata1 = new JSONObject();
                int userid1 = Integer.parseInt(userid);
                try {
                    userdatadata1.put("itmLotId", userid1);
                    userdatadata1.put("acceptqty", resultC);
                    userdatadata1.put("rejqty", resultB);
                    userdatadata1.put("rejtreasonid", ResionIdq);
                    userdatadata1.put("holdqty", resultA);
                    userdatadata1.put("holdreason", holdreson1);
                    userdatadata1.put("userId", useridu);
                    userdatadata1.put("barCode", BARCODEDATA);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest stageRequestInspect_Materialdateoo = new JsonObjectRequest(Request.Method.POST, Inspect_Material_URL, userdatadata1, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("@#@ggg#", response.toString());
                        progressDialog.hide();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                Qtkk = response.getString("qty");
                            }
                            System.out.print(Qtkk);
                            System.out.print(iddkk);

                            if (Qtkk != null) {
                                Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
                            }
                            //  Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_LONG).show();

                            LotQty.setText("");
                            holdreson.setText("");
                            holdQty.setText("");
                            rejectqty.setText("");
                            acceptQty.setText("");
                            resultC = 0;
                            resultB = 0;
                            resultA = 0;
                            RejectionResion.setAdapter(null);
                            /*Reasonlist_m();*/
                            BARCODEDATA = null;
                            barcodedataaf.setText("");
                            //barcodedataaf.setText("");
                            //  LotQty.setText(lotQty);
                        } catch (Exception e) {
                            Toast.makeText(Finel_material_inspActivity.this, "Something  Exception !!!", Toast.LENGTH_SHORT).show();
                        }
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
                        LotQty.setText("");
                        barcodedataaf.setText("");
                        Toast.makeText(Finel_material_inspActivity.this, "Something  Wrong", Toast.LENGTH_SHORT).show();
                    }
                });
                RequestQueue Inspect = Volley.newRequestQueue(getApplicationContext());
                Inspect.add(stageRequestInspect_Materialdateoo);
            }
        } else {
            Toast.makeText(Finel_material_inspActivity.this, "Quantity is grater than Lot Quantity", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String resultContents = result.getContents();
        if (resultContents == null) {
            Toast.makeText(Finel_material_inspActivity.this, "No Data Found !! Pleasse Scan Again", Toast.LENGTH_SHORT).show();
        } else {
            Seperate(resultContents);

            Toast.makeText(Finel_material_inspActivity.this, resultContents, Toast.LENGTH_LONG).show();
            resultContents.length();
            StringTokenizer st = new StringTokenizer(resultContents, "\n|");
            BARCODEDATA = st.nextToken();
            System.out.println(BARCODEDATA);
            System.out.println(BARCODEDATA);
            System.out.println(BARCODEDATA);
  /*
            Acceptqty1dd=BARCODEDATA;
          String  Acceptqty1ddq[] = Acceptqty1dd.split("-",1);
            System.out.println(Acceptqty1ddq);*/
            Acceptqty1dd = BARCODEDATA.substring(4);
            barcodedataaf.setText(Acceptqty1dd);
            System.out.println(Acceptqty1dd);
            //Toast.makeText(Finel_material_inspActivity.this, BARCODEDATA, Toast.LENGTH_SHORT).show();
            Seperate(resultContents);
            MaterialQTY();
        }
        System.out.println("" + resultContents);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void MaterialQTY() {
        if (BARCODEDATA.equalsIgnoreCase("false")) {
            Toast.makeText(Finel_material_inspActivity.this, "No Data Found !! Pleasse Scan Again", Toast.LENGTH_SHORT).show();
        } else {
            String GrnQTY_URL = Config.BASE_URL + "grn/scanQr";
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading Data !!");
            progressDialog.show();

            //  JSONObject userdatadata = new JSONObject();

            GrnQTY_URL = GrnQTY_URL + "?qrCodeNo=" + BARCODEDATA;
            JsonObjectRequest stageRequestdate = new JsonObjectRequest(Request.Method.GET, GrnQTY_URL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    progressDialog.hide();
                    try {
                        Log.d("Rohitt", response.toString());
                        for (int i = 0; i < response.length(); i++) {
                            /*JSONObject jsonObject = response.getJSONObject(i);*/
                            lotQty = response.getString("qty");
                            //     Toast.makeText(getApplicationContext(), lotQty, Toast.LENGTH_LONG).show();
                            userid = response.getString("id");
                        }
                        LotQty.setText(lotQty);
                        oprater.setText(useridu);
                        progressDialog.dismiss();
                    } catch (JSONException e) {
                        progressDialog.dismiss();
                        e.printStackTrace();
                    }
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
