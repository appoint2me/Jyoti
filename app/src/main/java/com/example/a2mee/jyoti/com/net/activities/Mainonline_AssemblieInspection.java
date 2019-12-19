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
import java.util.StringTokenizer;

import static com.example.a2mee.jyoti.com.net.utils.Config.password_SHARED_PREF;

public class Mainonline_AssemblieInspection extends Activity {

    Button scannbuttono, saveButtono, saveanothero;
    TextView barcodedataafo, opratero, LotQtyo;
    EditText holdresono, holdQtyo, rejectqtyo, acceptQtyo;
    // CheckBox cko;
    Spinner RejectionResiono;
    int resultAo, resultBo, resultCo, Lotqty1, totalqtyo;

    String Sumofallqtyo;
    String holdreson1o;
    String holdQty1o;
    String rejectqty1o;
    String Acceptqtyo;

    String BARCODEDATAo, Acceptqty1dd, userid, lotQtyo, userido, fffvo, Acceptqty1o, lotQtyqqo, reasonnameqo, ResionIdqo;
    String Qtkk;
    String REASONINSPECTION = Config.BASE_URL + "assembly/rejectResonListForOlInspection";
    String SAVEDATA = Config.BASE_URL + "assembly/assemblyOlInspectionDtail";
    String SAVEANOTHER = Config.BASE_URL + "assembly/assemblyOlInspectionDtail";
    private ArrayList<String> ResionId;
    private ArrayList<String> reasonname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_mainonline__assemblie_inspection);

        SharedPreferences sharedPreferences = getSharedPreferences(Config.mylogin_PREF_NAME, Context.MODE_PRIVATE);
        userid = sharedPreferences.getString(password_SHARED_PREF, null);
        System.out.print(userid);

        barcodedataafo = findViewById(R.id.barcodedataafqq);
        //ck = findViewById(R.id.bagg1);
        scannbuttono = findViewById(R.id.butscanfinelqq);
        saveButtono = findViewById(R.id.Submithhhfqq);
        saveanothero = findViewById(R.id.Submitkkfqq);
        LotQtyo = findViewById(R.id.lotqttyjqq);
        //Accept = findViewById(R.id.accepiidf);
        holdresono = findViewById(R.id.holdresfqq);
        holdQtyo = findViewById(R.id.Holdfqq);
        rejectqtyo = findViewById(R.id.Rejectfqq);
        acceptQtyo = findViewById(R.id.acceptQtyqq);
        opratero = findViewById(R.id.opraterfqq);
        RejectionResiono = findViewById(R.id.Rejectionfqq);
       /* acceptQtyo.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                return false;
            }
        });*/
        scannbuttono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator scan = new IntentIntegrator(Mainonline_AssemblieInspection.this);
                scan.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                scan.initiateScan();
            }
        });

//
        holdQtyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            /*    System.out.print(BARCODEDATAo);

                if (BARCODEDATAo == null) {

                    //    holdQty.setEnabled(false);
                    holdQtyo.setText("");
                    Toast.makeText(Mainonline_AssemblieInspection.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
                } else {
                    holdQtyo.setEnabled(true);
                    // showMyDialog();
                    holdQty1o = holdQtyo.getText().toString();
                    System.out.print(holdQty1o);
                    if (holdQty1o != null) {
                        resultAo = Integer.parseInt(holdQty1o);
                    } else {
                        resultAo = 0;
                    }

                    System.out.print(holdQty1o);
                }
            */
            }
        });

        holdresono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           /*     System.out.print(BARCODEDATAo);
                if (BARCODEDATAo == null) {
                    //    holdreson.setEnabled(false);
                    holdresono.setText("");
                    Toast.makeText(Mainonline_AssemblieInspection.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
                } else {
                    holdresono.setEnabled(true);
                    holdreson1o = holdresono.getText().toString();
                }
            */
            }
        });

        rejectqtyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.print(BARCODEDATAo);
                if (BARCODEDATAo == null) {
                    //  rej
                    // ectqty.setEnabled(false);
                    rejectqtyo.setText("");
                    Toast.makeText(Mainonline_AssemblieInspection.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
                } else {
                    rejectqtyo.setEnabled(true);
                    rejectqty1o = rejectqtyo.getText().toString();
                    System.out.print(rejectqty1o);
                    if (rejectqty1o != null) {
                        resultBo = Integer.parseInt(rejectqty1o);
                    } else {
                        resultBo = 0;
                    }
                    Reasoembley();
                }
            }
        });
        acceptQtyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /*    System.out.print(BARCODEDATAo);
                if (BARCODEDATAo == null) {
                    acceptQtyo.setText("");
                    Toast.makeText(Mainonline_AssemblieInspection.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
                } else {
                    Acceptqtyo = acceptQtyo.getText().toString();
                    System.out.print(Acceptqtyo);
                    if (Acceptqtyo.isEmpty()) {
                        resultCo = 0;
                    } else {
                        resultCo = Integer.parseInt(Acceptqtyo);
                    }
                    System.out.print(resultCo);
                }
            */
            }
        });

        saveButtono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAcceptQTY();
                getHoldreason();
                getHoldQTY();
                System.out.print(BARCODEDATAo);
                if (BARCODEDATAo == null) {
                    Toast.makeText(Mainonline_AssemblieInspection.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
                } else {
                    System.out.print(rejectqty1o);
                    if (lotQtyo.equalsIgnoreCase(null)) {
                        Toast.makeText(Mainonline_AssemblieInspection.this, "No Data Found !! Pleasse Scan Again", Toast.LENGTH_SHORT).show();
                    } else {
                        Lotqty1 = Integer.parseInt(lotQtyo);
                    }
                    totalqtyo = resultAo + resultBo + resultCo;
                    if (Lotqty1 >= totalqtyo) {
                        SAvedatato();
                    } else {
                        Toast.makeText(Mainonline_AssemblieInspection.this, "Quantity is grater than Lot Quantity", Toast.LENGTH_SHORT).show();
                        reaggg();
                    }
                }

            }
        });
        saveanothero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAcceptQTY();
                getHoldreason();
                getHoldQTY();
                System.out.print(BARCODEDATAo);
                if (BARCODEDATAo == null) {
                    Toast.makeText(Mainonline_AssemblieInspection.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
                } else {
                    System.out.print(rejectqty1o);
                    if (lotQtyo.equalsIgnoreCase(null)) {
                        Toast.makeText(Mainonline_AssemblieInspection.this, "No Data Found !! Pleasse Scan Again", Toast.LENGTH_SHORT).show();
                    } else {
                        Lotqty1 = Integer.parseInt(lotQtyo);
                    }
                    totalqtyo = resultAo + resultBo + resultCo;
                    if (Lotqty1 >= totalqtyo) {
                        SAveanotherto();
                    } else {
                        Toast.makeText(Mainonline_AssemblieInspection.this, "Quantity is grater than Lot Quantity", Toast.LENGTH_SHORT).show();
                        reaggg();
                    }
                }
            }
        });

    }

    private void getHoldQTY() {
        System.out.print(BARCODEDATAo);

        if (BARCODEDATAo == null) {

            //    holdQty.setEnabled(false);
            holdQtyo.setText("");
            Toast.makeText(Mainonline_AssemblieInspection.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
        } else {
            holdQtyo.setEnabled(true);
            // showMyDialog();
            holdQty1o = holdQtyo.getText().toString();
            System.out.print(holdQty1o);
            if (holdQty1o.isEmpty()) {
                resultAo = 0;
                //
            } else {
                resultAo = Integer.parseInt(holdQty1o);
            }

            System.out.print(holdQty1o);
        }

    }

    private void getHoldreason() {
        System.out.print(BARCODEDATAo);
        if (BARCODEDATAo == null) {
            //    holdreson.setEnabled(false);
            holdresono.setText("");
            Toast.makeText(Mainonline_AssemblieInspection.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
        } else {
            holdresono.setEnabled(true);
            holdreson1o = holdresono.getText().toString();

        }

    }

    private void getAcceptQTY() {
        System.out.print(BARCODEDATAo);
        if (BARCODEDATAo == null) {
            acceptQtyo.setText("");
            Toast.makeText(Mainonline_AssemblieInspection.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
        } else {
            Acceptqtyo = acceptQtyo.getText().toString();
            System.out.print(Acceptqtyo);
            if (Acceptqtyo.isEmpty()) {
                resultCo = 0;
            } else {
                resultCo = Integer.parseInt(Acceptqtyo);
            }
            System.out.print(resultCo);
        }

    }

    private void reaggg() {

        holdresono.setText("");
        holdQtyo.setText("");
        rejectqtyo.setText("");
        acceptQtyo.setText("");
        resultCo = 0;
        resultBo = 0;
        resultAo = 0;
        Reasoembley();
        LotQtyo.setText(Qtkk);
    }

    private void SAveanotherto() {

        if (Lotqty1 >= totalqtyo) {
            if (ResionIdqo == "0") {
                Toast.makeText(getApplicationContext(), "Please Select A Reason", Toast.LENGTH_SHORT).show();
            } else {


                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Loading Data !!");
                progressDialog.show();

                JSONObject userdatadata11 = new JSONObject();
                int userid1 = Integer.parseInt(userido);

                try {
                    userdatadata11.put("id", userid1);
                    userdatadata11.put("acceptqty", resultCo);
                    userdatadata11.put("rejqty", resultBo);
                    userdatadata11.put("rejtreasonid", ResionIdqo);
                    userdatadata11.put("holdqty", resultAo);
                    userdatadata11.put("holdreason", holdreson1o);
                    userdatadata11.put("userId", userid);
                    userdatadata11.put("barCode", BARCODEDATAo);

                    Log.d("@#@#", userdatadata11.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest stageRequestInspect_Materialdate1 = new JsonObjectRequest(Request.Method.POST, SAVEANOTHER, userdatadata11, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("@#@ggg#", response.toString());
                        progressDialog.dismiss();
                        System.out.print(response);
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                Qtkk = response.getString("qty");
                                //  iddkk.add(response.getString("id"));
                                Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_LONG).show();
                                System.out.print(Qtkk);
                                //    System.out.print(iddkk);
                            }

                            holdresono.setText("");
                            holdQtyo.setText("");
                            rejectqtyo.setText("");
                            acceptQtyo.setText("");
                            resultCo = 0;
                            resultBo = 0;
                            resultAo = 0;
                            ResionIdqo = null;
                            RejectionResiono.setAdapter(null);
                            /*Reasoembley();*/
                            LotQtyo.setText(Qtkk);
                            // Toast.makeText(Finel_material_inspActivity.this, massage, Toast.LENGTH_SHORT).show();


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
                        Toast.makeText(Mainonline_AssemblieInspection.this, "Something Went Wrong !!!", Toast.LENGTH_SHORT).show();
                    }
                });
                RequestQueue Inspect = Volley.newRequestQueue(getApplicationContext());
                stageRequestInspect_Materialdate1.setRetryPolicy(new DefaultRetryPolicy(12000,
                        1,
                        1));
                Inspect.add(stageRequestInspect_Materialdate1);
            }
        } else {
            Toast.makeText(Mainonline_AssemblieInspection.this, "Quantity is grater than Lot Quantity", Toast.LENGTH_SHORT).show();
        }

    }

    private void SAvedatato() {
        if (Lotqty1 >= totalqtyo) {
            if (ResionIdqo == "0") {
                Toast.makeText(getApplicationContext(), "Please Select a Reason", Toast.LENGTH_SHORT).show();

            } else {


                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Loading Data !!");
                progressDialog.show();
                JSONObject userdatadata1 = new JSONObject();
                int userid1 = Integer.parseInt(userido);
                try {
                    userdatadata1.put("id", userid1);
                    userdatadata1.put("acceptqty", resultCo);
                    userdatadata1.put("rejqty", resultBo);
                    userdatadata1.put("rejtreasonid", ResionIdqo);
                    userdatadata1.put("holdqty", resultAo);
                    userdatadata1.put("holdreason", holdreson1o);
                    userdatadata1.put("userId", userid);
                    userdatadata1.put("barCode", BARCODEDATAo);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest stageRequestInspect_Materialdateoo = new JsonObjectRequest(Request.Method.POST, SAVEDATA, userdatadata1, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("@#@ggg#", response.toString());
                        progressDialog.hide();

                        try {
                            for (int i = 0; i < response.length(); i++) {
                                Qtkk = response.getString("qty");
                                Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_LONG).show();
                                System.out.print(Qtkk);
                                //System.out.print(iddkk);
                            }

                            LotQtyo.setText("");
                            holdresono.setText("");
                            holdQtyo.setText("");
                            rejectqtyo.setText("");
                            acceptQtyo.setText("");
                            resultCo = 0;
                            resultBo = 0;
                            resultAo = 0;
                            ResionIdqo = null;
                            /*Reasoembley();*/
                            RejectionResiono.setAdapter(null);
                            BARCODEDATAo = "";
                            barcodedataafo.setText("");
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
                        Toast.makeText(Mainonline_AssemblieInspection.this, "Something Went Wrong !!!", Toast.LENGTH_SHORT).show();
                    }
                });
                RequestQueue Inspect = Volley.newRequestQueue(getApplicationContext());
                stageRequestInspect_Materialdateoo.setRetryPolicy(new DefaultRetryPolicy(12000,
                        1,
                        1));

                Inspect.add(stageRequestInspect_Materialdateoo);
            }
        } else {
            Toast.makeText(Mainonline_AssemblieInspection.this, "Quantity is grater than Lot Quantity", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String resultContents = result.getContents();
        if (resultContents == null) {
            Toast.makeText(Mainonline_AssemblieInspection.this, "No Data Found !! Pleasse Scan Again", Toast.LENGTH_SHORT).show();
        } else {
            Seperate(resultContents);
            Toast.makeText(Mainonline_AssemblieInspection.this, resultContents, Toast.LENGTH_LONG).show();
            resultContents.length();
            StringTokenizer st = new StringTokenizer(resultContents, "\n|");
            BARCODEDATAo = st.nextToken();
            System.out.println(BARCODEDATAo);
            Acceptqty1dd = BARCODEDATAo.substring(4);
            barcodedataafo.setText(Acceptqty1dd);
            //Toast.makeText(Finel_material_inspActivity.this, BARCODEDATA, Toast.LENGTH_SHORT).show();
            Seperate(resultContents);

            GETMaterialQTY();
        }
        System.out.println("" + resultContents);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void GETMaterialQTY() {

        if (BARCODEDATAo.equalsIgnoreCase("false")) {
            Toast.makeText(Mainonline_AssemblieInspection.this, "No Data Found !! Please Scan Again", Toast.LENGTH_SHORT).show();
        } else {

            String QRCODECODEQTY = Config.BASE_URL + "assembly/assemblyOlInspection";
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading Data !!");
            progressDialog.show();
            JSONObject userdatadata = new JSONObject();
            QRCODECODEQTY = QRCODECODEQTY + "?qrCode=" + BARCODEDATAo;
            JsonObjectRequest stageRequestdate = new JsonObjectRequest(Request.Method.GET, QRCODECODEQTY, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    progressDialog.dismiss();
                    try {
                        Log.d("Rohitt", response.toString());
                        for (int i = 0; i < response.length(); i++) {
                            lotQtyo = response.getString("qty");
                            userido = response.getString("id");
                        }
                        LotQtyo.setText(lotQtyo);
                        opratero.setText(userid);
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
                    Toast.makeText(getApplicationContext(), "Wrong_OR code", Toast.LENGTH_LONG).show();
                }
            });
            RequestQueue ddrq = Volley.newRequestQueue(getApplicationContext());
            ddrq.add(stageRequestdate);


        }
    }

    private void Seperate(String resultContents) {
    }

    private void Reasoembley() {
        ResionId = new ArrayList<>();
        reasonname = new ArrayList<>();
        ResionId.add("1");
        reasonname.add("Select Reason");
        //   final ArrayList<String> Stringdatar = new ArrayList<String>();
        JSONObject userdatadata = new JSONObject();
        RequestQueue ddrq = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest stageRequestdatezz = new JsonArrayRequest(Request.Method.GET, REASONINSPECTION, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
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
                ArrayAdapter<String> spinnerArrayrr = new ArrayAdapter<String>(Mainonline_AssemblieInspection.this, android.R.layout.simple_spinner_dropdown_item, reasonname);
                RejectionResiono.setAdapter(spinnerArrayrr);
                System.out.print(response.toString());
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
                Toast.makeText(getApplicationContext(), "Something Went Wrong !!!", Toast.LENGTH_LONG).show();
                /*Log.d("@#@#", error.getMessage());*/
            }
        });

        ddrq.add(stageRequestdatezz);
        RejectionResiono.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data = (String) parent.getItemAtPosition(position);
                Log.d("@@@@", data);
                if (data.equalsIgnoreCase("Select Reason")) {
                    ResionIdqo = "0";
                } else {
                    Log.d("@#@#1", String.valueOf(RejectionResiono.getItemIdAtPosition(position)));
                    ResionIdqo = ResionId.get(position);
                    reasonnameqo = reasonname.get((int) RejectionResiono.getItemIdAtPosition(position));
                    Log.d("@#", data);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

}
