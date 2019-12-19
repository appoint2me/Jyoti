package com.example.a2mee.jyoti.com.net.activities;

import android.app.Activity;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
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

import static com.example.a2mee.jyoti.com.net.utils.Config.password_SHARED_PREF;

public class Quaraqntine_srore extends Activity {
    EditText Accept, Rejection, oprater;
    TextView Qty;
    String userid;
    double Lotqty1;
    Spinner RejectionRR;
    Button Save, saveanother, scann;
    TextView Barcodedata;
    String BARCODEDATAQC, Qtkk, Acceptqty1dd;
    String lotQtyo, userido, itemMstId, quar_id, Rejectionqty, ResionIdqo, reasonnameqo, Acceptqtyo;
    Double resultBo, totalqtyo, resultCo;

    int us1;
    //  String REASONINSPECTION = Config.BASE_URL + "assembly/rejectResonListForOlInspection";

    private ArrayList<String> ResionId;
    private ArrayList<String> reasonname;
    private ArrayList<String> quar_idqq;
    private ArrayList<String> lotQtyo1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_quaraqntine_srore);
        Qty = findViewById(R.id.qtyyd);

        Accept = findViewById(R.id.acceoops);
        RejectionRR = findViewById(R.id.Rejectionfss);
        Rejection = findViewById(R.id.Rejectfdd);
        oprater = findViewById(R.id.opraterfd);
        Save = findViewById(R.id.opsavef);
        saveanother = findViewById(R.id.osaveaaa);
        scann = findViewById(R.id.qaeee);
        SharedPreferences sharedPreferences = getSharedPreferences(Config.mylogin_PREF_NAME, Context.MODE_PRIVATE);
        userid = sharedPreferences.getString(password_SHARED_PREF, null);
        System.out.print(userid);
        System.out.print(userid);
        Barcodedata = findViewById(R.id.qtrrr);

        scann.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Barcodedata.setText("");
                //  Resssonee();
                IntentIntegrator scan = new IntentIntegrator(Quaraqntine_srore.this);
                scan.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                scan.initiateScan();
            }
        });

        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*System.out.print(BARCODEDATAQC);
                if (BARCODEDATAQC == null) {
                    Accept.setText("");
                    Toast.makeText(Quaraqntine_srore.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
                } else {
                    Acceptqtyo = Accept.getText().toString();
                    System.out.print(Acceptqtyo);
                    if (Acceptqtyo.isEmpty()) {
                        resultCo = 0.0;
                        Toast.makeText(Quaraqntine_srore.this,"Accept QTY is Empty",Toast.LENGTH_SHORT).show();
                    } else {
                        resultCo = Double.parseDouble(Acceptqtyo);
                    }
                    System.out.print(resultCo);
                }*/
            }
        });
        Rejection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.print(BARCODEDATAQC);
                if (BARCODEDATAQC == null) {
                    Rejection.setText("");
                    Toast.makeText(Quaraqntine_srore.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
                } else {
                    Rejection.setEnabled(true);
                    Rejectionqty = Rejection.getText().toString();
                    System.out.print(Rejectionqty);

                    if (Rejectionqty.isEmpty()) {
                        resultBo = 0.0;
                        //
                    } else {
                        resultBo = Double.parseDouble(Rejectionqty);
                    }
                    Reasonlsembley();
                }
            }
        });
        saveanother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAccept();

                System.out.print(BARCODEDATAQC);
                if (BARCODEDATAQC == null) {
                    Toast.makeText(Quaraqntine_srore.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
                } else {
                    System.out.print(Rejectionqty);
                    if (lotQtyo.equalsIgnoreCase(null)) {
                        Toast.makeText(Quaraqntine_srore.this, "No Data Found !! Pleasse Scan Again", Toast.LENGTH_SHORT).show();
                    } else {
                        Lotqty1 = Double.parseDouble(lotQtyo);
                    }
                    if (resultBo == null) {
                        resultBo = 0.0;
                    }
                    totalqtyo = resultBo + resultCo;
                    System.out.print(Lotqty1);
                    System.out.print(totalqtyo);
                    if (Lotqty1 >= totalqtyo) {
                        us1 = (int) Double.parseDouble(String.valueOf(totalqtyo));
                        if (us1 == 0) {
                            Toast.makeText(Quaraqntine_srore.this, "Quantity is 0 ", Toast.LENGTH_SHORT).show();
                        } else {
                            SAveAnother();
                        }
                    } else {
                        Toast.makeText(Quaraqntine_srore.this, "Quantity is grater than Lot Quantity", Toast.LENGTH_SHORT).show();
                        Ressson();
                    }
                }

            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAccept();
                System.out.print(BARCODEDATAQC);
                if (BARCODEDATAQC == null) {
                    Toast.makeText(Quaraqntine_srore.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
                } else {
                    System.out.print(Rejectionqty);
                    if (lotQtyo.equalsIgnoreCase(null)) {
                        Toast.makeText(Quaraqntine_srore.this, "No Data Found !! Pleasse Scan Again", Toast.LENGTH_SHORT).show();
                    } else {
                        Lotqty1 = Double.parseDouble(lotQtyo);
                    }
                    if (resultBo == null) {
                        resultBo = 0.0;
                    }
                    totalqtyo = resultBo + resultCo;
                    if (Lotqty1 >= totalqtyo) {
                        us1 = (int) Double.parseDouble(String.valueOf(totalqtyo));
                        if (us1 == 0) {
                            Toast.makeText(Quaraqntine_srore.this, "Quantity is 0 ", Toast.LENGTH_SHORT).show();
                        } else {
                            SAvedatato();
                        }
                    } else {
                        Toast.makeText(Quaraqntine_srore.this, "Quantity is grater than Lot Quantity", Toast.LENGTH_SHORT).show();
                        Ressson();
                    }
                }

            }
        });

    }

    private void getAccept() {
        System.out.print(BARCODEDATAQC);
        if (BARCODEDATAQC == null) {
            Accept.setText("");
            Toast.makeText(Quaraqntine_srore.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
        } else {
            Acceptqtyo = Accept.getText().toString();
            System.out.print(Acceptqtyo);
            if (Acceptqtyo.isEmpty()) {
                resultCo = 0.0;
                Toast.makeText(Quaraqntine_srore.this, "Accept QTY is Empty", Toast.LENGTH_SHORT).show();
            } else {
                resultCo = Double.parseDouble(Acceptqtyo);
            }
            System.out.print(resultCo);
        }
    }

    private void Resssonee() {
        Qty.setText("");
        Rejection.setText("");
        Accept.setText("");
        resultBo = 0.0;
        resultCo = 0.0;
        BARCODEDATAQC = "";

    }

    private void Ressson() {

        Qty.setText("");
        Rejection.setText("");
        Accept.setText("");
        resultBo = 0.0;
        resultCo = 0.0;
        BARCODEDATAQC = "";
        Barcodedata.setText("");
        Reasonlsembley();
    }

    private void SAveAnother() {
        if (BARCODEDATAQC == null) {
            Toast.makeText(Quaraqntine_srore.this, "SCAN OR CODE", Toast.LENGTH_SHORT).show();
        }
        String SAVEANOTHER = Config.BASE_URL + "quarantine/saveQuarantineSummary";
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data !!");
        progressDialog.show();
        JSONObject userdatadata16 = new JSONObject();
        int userid1dd = Integer.parseInt(quar_id);
        try {
            // userdatadata16.put("qcInputId", userid1);
            userdatadata16.put("acceptQty", resultCo);
            userdatadata16.put("rejectQty", resultBo);
            userdatadata16.put("rejectReason", ResionIdqo);
            userdatadata16.put("operator", userid);
            userdatadata16.put("quarantineId", userid1dd);
            // userdatadata1.put("qrcode",BARCODEDATAo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue Inspect = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest stageRequestInspect_Materialdateoo = new JsonObjectRequest(Request.Method.POST, SAVEANOTHER, userdatadata16, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("@#@ggg#", response.toString());
                progressDialog.hide();

                try {
                    for (int i = 0; i < response.length(); i++) {
                        Qtkk = response.getString("holdQty");
                    }
                    Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_LONG).show();
                    /*Reasonlsembley();*/
                    RejectionRR.setAdapter(null);
                    Qty.setText(Qtkk);
                    Rejection.setText("");
                    Accept.setText("");
                    resultCo = 0.0;
                    resultBo = 0.0;
                    ResionIdqo = null;
                    //  Rejectionqty=null;
                    // BARCODEDATAQC = "";
                    //Barcodedata.setText("");
                    //   Reasonlist_of_onlineassembley();
                    //  LotQty.setText(lotQty);
                } catch (Exception e) {

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
                Toast.makeText(Quaraqntine_srore.this, "Something Went  WRONG !!!", Toast.LENGTH_SHORT).show();
            }
        });

        stageRequestInspect_Materialdateoo.setRetryPolicy(new DefaultRetryPolicy(12000,
                1,
                1));
        Inspect.add(stageRequestInspect_Materialdateoo);

    }

    private void SAvedatato() {
        if (ResionIdqo == "0") {
            Toast.makeText(getApplicationContext(), "Please Select a Reason", Toast.LENGTH_SHORT).show();
        }
        String SAVEDATA = Config.BASE_URL + "quarantine/saveQuarantineSummary";

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data !!");
        progressDialog.show();

        JSONObject userdatadata1 = new JSONObject();
        int userid1dd = Integer.parseInt(quar_id);
        try {
            //   userdatadata1.put("qcInputId", userid1);
            userdatadata1.put("acceptQty", resultCo);
            userdatadata1.put("rejectQty", resultBo);
            userdatadata1.put("rejectReason", ResionIdqo);
            userdatadata1.put("operator", userid);
            userdatadata1.put("quarantineId", userid1dd);
            // userdatadata1.put("qrcode",BARCODEDATAo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue Inspect = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest stageRequestInspect_Materialdateoo = new JsonObjectRequest(Request.Method.POST, SAVEDATA, userdatadata1, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("@#@ggg#", response.toString());
                progressDialog.hide();
                System.out.print("hellow");
                try {
                    for (int i = 0; i < response.length(); i++) {
                        Qtkk = response.getString("holdQty");

                    }
                    Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_LONG).show();
                    Qty.setText("");
                    Rejection.setText("");
                    Accept.setText("");
                    resultBo = 0.0;
                    resultCo = 0.0;
                    BARCODEDATAQC = "";
                    Barcodedata.setText("");
                    ResionIdqo = null;
                    /*Reasonlsembley();*/
                    RejectionRR.setAdapter(null);
                    //  LotQty.setText(lotQty);
                } catch (Exception e) {

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
                Toast.makeText(Quaraqntine_srore.this, "WRONG", Toast.LENGTH_SHORT).show();
            }
        });

        stageRequestInspect_Materialdateoo.setRetryPolicy(new DefaultRetryPolicy(12000,
                1,
                1));
        Inspect.add(stageRequestInspect_Materialdateoo);

    }

    public void Reasonlsembley() {
        String REASONINSPECTION = Config.BASE_URL + "assembly/rejectResonListForOlInspection";
        ResionId = new ArrayList<>();
        reasonname = new ArrayList<>();
        //   final ArrayList<String> Stringdatar = new ArrayList<String>();
        JSONObject userdatadata = new JSONObject();
        RequestQueue ddrq = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest stageRequestdatezz = new JsonArrayRequest(Request.Method.GET, REASONINSPECTION, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    reasonname.add("select Material");
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
                }
                ArrayAdapter<String> spinnerArrayrr = new ArrayAdapter<String>(Quaraqntine_srore.this, android.R.layout.simple_spinner_dropdown_item, reasonname);
                RejectionRR.setAdapter(spinnerArrayrr);
                System.out.print(response.toString());

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("@#@#", error.getMessage());
            }
        });

        ddrq.add(stageRequestdatezz);
        RejectionRR.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data = (String) parent.getItemAtPosition(position);
                Log.d("@@@@", data);
                if (data.equalsIgnoreCase("select Material")) {
                    ResionIdqo = "0";
                } else {
                    Log.d("@#@#1", String.valueOf(RejectionRR.getItemIdAtPosition(position)));
                    ResionIdqo = ResionId.get(position);
                    reasonnameqo = reasonname.get((int) RejectionRR.getItemIdAtPosition(position));
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
            Toast.makeText(Quaraqntine_srore.this, "No Data Found !! Pleasse Scan Again", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(Quaraqntine_srore.this, resultContents, Toast.LENGTH_LONG).show();
            resultContents.length();
            StringTokenizer st = new StringTokenizer(resultContents, "\n|");
            BARCODEDATAQC = st.nextToken();
            System.out.println(BARCODEDATAQC);
            Acceptqty1dd = BARCODEDATAQC.substring(4);
            Barcodedata.setText(Acceptqty1dd);
            //Toast.makeText(Finel_material_inspActivity.this, BARCODEDATA, Toast.LENGTH_SHORT).show();
            GETMaterialQTYQCooo();
        }
        System.out.println("" + resultContents);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void GETMaterialQTYQCooo() {

        if (BARCODEDATAQC.equalsIgnoreCase("false")) {
            Toast.makeText(Quaraqntine_srore.this, "No Data Found !! Pleasse Scan Again", Toast.LENGTH_SHORT).show();
        } else {

            String QRCODECODEQTYqc = Config.BASE_URL + "quarantine/scanQrcodeAtQuarantine";
            lotQtyo1 = new ArrayList<>();
            quar_idqq = new ArrayList<>();
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading Data !!");
            progressDialog.show();
            JSONArray userdatadata = new JSONArray();
            RequestQueue ddrqsss = Volley.newRequestQueue(getApplicationContext());
            QRCODECODEQTYqc = QRCODECODEQTYqc + "?qrcode=" + BARCODEDATAQC;
            JsonArrayRequest ddddddd = new JsonArrayRequest(Request.Method.GET, QRCODECODEQTYqc, null, new Response.Listener<JSONArray>() {
                @Override

                public void onResponse(JSONArray response) {
                    progressDialog.hide();
                    try {
                        Log.d("Rohitt", response.toString());

                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            lotQtyo1.add(jsonObject.getString("holdQty"));
                            quar_idqq.add(jsonObject.getString("quar_id"));
                            // lotQtyo = response.getString("holdQty");//quar_id = response.getString("quar_id");
                        }
                        String[] stringArray = lotQtyo1.toArray(new String[0]);
                        lotQtyo = Arrays.toString(stringArray);
                        lotQtyo = lotQtyo.substring(1, lotQtyo.length() - 1);
                        Qty.setText(lotQtyo);
                        oprater.setText(userid);


                        String[] stringArrayrr = quar_idqq.toArray(new String[0]);
                        quar_id = Arrays.toString(stringArrayrr);
                        quar_id = quar_id.substring(1, quar_id.length() - 1);
                        //    Qty.setText(quar_id);
                        //oprater.setText("1");


                        progressDialog.dismiss();
                    } catch (Exception e) {
                        progressDialog.dismiss();
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), " WRONG_QR code", Toast.LENGTH_LONG).show();
                }
            });

            ddrqsss.add(ddddddd);
        }
    }
}
