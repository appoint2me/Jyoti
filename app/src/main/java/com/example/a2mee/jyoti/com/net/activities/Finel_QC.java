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

public class Finel_QC extends Activity {

    Button scannbuttonost, saveButtonost, saveanotherost;
    TextView barcodedataafost, opraterost, LotQtyost;
    EditText holdresonost, holdQtyost, rejectqtyost, acceptQtyost;
    // CheckBox cko;
    Spinner RejectionResionost;
    double resultAo, resultBo, resultCo, totalqtyo, Lotqty1;
    int us1;
    String Sumofallqtyo;
    String holdreson1o;
    String holdQty1o;
    String rejectqty1o;
    String Acceptqtyo, qcInputId, userid, itemMstId, poActualQty;
    String BARCODEDATAo, Acceptqty1dd, lotQtyo, userido, Source, fffvo, Acceptqty1o, lotQtyqqo, reasonnameqo, ResionIdqo;
    String Qtkk;
    //String QRCODECODEQTY = Config.BASE_URL + "qualityCheck/qcScanQrCode";
    String SAVEDATA = Config.BASE_URL + "qualityCheck/qcSave";
    String SAVEANOTHER = Config.BASE_URL + "qualityCheck/qcSave";
    private ArrayList<String> ResionId;
    private ArrayList<String> reasonname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_finel__qc);

        SharedPreferences sharedPreferences = getSharedPreferences(Config.mylogin_PREF_NAME, Context.MODE_PRIVATE);
        userid = sharedPreferences.getString(password_SHARED_PREF, null);
        System.out.print(userid);
        barcodedataafost = findViewById(R.id.barcodedataafFC);
        //ck = findViewById(R.id.bagg1);
        scannbuttonost = findViewById(R.id.butscanfinelFC);
        saveButtonost = findViewById(R.id.SubmithhhfFC);
        saveanotherost = findViewById(R.id.SubmitkkfFC);
        LotQtyost = findViewById(R.id.lotqttyjFC);
        //Accept = findViewById(R.id.accepiidf);
        holdresonost = findViewById(R.id.holdresfFC);
        holdQtyost = findViewById(R.id.HoldfFC);
        rejectqtyost = findViewById(R.id.RejectfFC);
        acceptQtyost = findViewById(R.id.acceptQtyFC);
        opraterost = findViewById(R.id.opraterfFC);
        RejectionResionost = findViewById(R.id.RejectionfFC);

        scannbuttonost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator scan = new IntentIntegrator(Finel_QC.this);
                scan.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                scan.initiateScan();
            }
        });

//
        holdQtyost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*System.out.print(BARCODEDATAo);
                if (BARCODEDATAo == null) {

                    //    holdQty.setEnabled(false);
                    holdQtyost.setText("");
                    Toast.makeText(Finel_QC.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
                } else {
                    holdQtyost.setEnabled(true);
                    // showMyDialog();
                    holdQty1o = holdQtyost.getText().toString();
                    System.out.print(holdQty1o);
                    if (holdQty1o != null) {

                        //  double d = Double.parseDouble(number);
                        resultAo = Double.parseDouble(holdQty1o);
                    } else {
                        resultAo = 0;
                    }

                    System.out.print(holdQty1o);
                }*/
            }
        });

        holdresonost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /*    System.out.print(BARCODEDATAo);
                if (BARCODEDATAo == null) {
                    //    holdreson.setEnabled(false);
                    holdresonost.setText("");
                    Toast.makeText(Finel_QC.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
                } else {
                    holdresonost.setEnabled(true);
                    holdreson1o = holdresonost.getText().toString();
                }
            */
            }
        });
        rejectqtyost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.print(BARCODEDATAo);
                if (BARCODEDATAo == null) {
                    //  rej
                    // ectqty.setEnabled(false);
                    rejectqtyost.setText("");
                    Toast.makeText(Finel_QC.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
                } else {
                    rejectqtyost.setEnabled(true);
                    rejectqty1o = rejectqtyost.getText().toString();
                    System.out.print(rejectqty1o);
                    if (rejectqty1o.isEmpty()) {
                        resultBo = 0.0;
                        //
                    } else {
                        resultBo = Double.parseDouble(rejectqty1o);
                    }
                    Reasonlimbley();
                }
            }
        });
        acceptQtyost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* System.out.print(BARCODEDATAo);
                if (BARCODEDATAo == null) {
                    acceptQtyost.setText("");
                    Toast.makeText(Finel_QC.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
                } else {
                    Acceptqtyo = acceptQtyost.getText().toString();
                    System.out.print(Acceptqtyo);
                    if (Acceptqtyo.isEmpty()) {
                        resultCo = 0;
                    } else {
                        resultCo = Double.parseDouble(Acceptqtyo);
                    }
                    System.out.print(resultCo);
                }*/
            }
        });

        saveButtonost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAccept();
                getHoldReason();
                getHoldQty();

                System.out.print(BARCODEDATAo);
                if (BARCODEDATAo == null) {
                    Toast.makeText(Finel_QC.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
                } else {
                    System.out.print(rejectqty1o);
                    if (lotQtyo.equalsIgnoreCase(null)) {
                        Toast.makeText(Finel_QC.this, "No Data Found !! Pleasse Scan Again", Toast.LENGTH_SHORT).show();
                    } else {

                        // double d = Double.parseDouble(number);
                        Lotqty1 = Double.parseDouble(lotQtyo);
                    }
                    totalqtyo = resultAo + resultBo + resultCo;


                    if (Lotqty1 >= totalqtyo) {
                        us1 = (int) Double.parseDouble(String.valueOf(totalqtyo));
                        if (us1 == 0) {
                            Toast.makeText(Finel_QC.this, "Quantity is 0 ", Toast.LENGTH_SHORT).show();
                        } else {
                            if (Lotqty1 >= totalqtyo) {

                                SAvedatato();
                            }
                        }

                    } else {
                        Toast.makeText(Finel_QC.this, "Quantity is grater than Lot Quantity", Toast.LENGTH_SHORT).show();
                        Resett();
                    }
                }

            }
        });
        saveanotherost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAccept();
                getHoldReason();
                getHoldQty();

                System.out.print(BARCODEDATAo);
                if (BARCODEDATAo == null) {
                    Toast.makeText(Finel_QC.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
                } else {
                    System.out.print(rejectqty1o);
                    if (lotQtyo.equalsIgnoreCase(null)) {
                        Toast.makeText(Finel_QC.this, "No Data Found !! Pleasse Scan Again", Toast.LENGTH_SHORT).show();
                    } else {
                        Lotqty1 = Double.parseDouble(lotQtyo);
                    }
                    totalqtyo = resultAo + resultBo + resultCo;
                    if (Lotqty1 >= totalqtyo) {

                        us1 = (int) Double.parseDouble(String.valueOf(totalqtyo));
                        if (us1 == 0) {
                            Toast.makeText(Finel_QC.this, "Quantity is 0 ", Toast.LENGTH_SHORT).show();
                        } else {
                            if (Lotqty1 >= totalqtyo) {

                                SAveanotherto();
                            }
                        }

                    } else {
                        Toast.makeText(Finel_QC.this, "Quantity is grater than Lot Quantity", Toast.LENGTH_SHORT).show();
                        Resett();
                    }
                }
            }
        });
    }

    void getHoldQty() {
        System.out.print(BARCODEDATAo);
        if (BARCODEDATAo == null) {

            //    holdQty.setEnabled(false);
            holdQtyost.setText("");
            Toast.makeText(Finel_QC.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
        } else {
            holdQtyost.setEnabled(true);
            // showMyDialog();
            holdQty1o = holdQtyost.getText().toString();
            System.out.print(holdQty1o);
            if (holdQty1o.isEmpty()) {
                resultAo = 0.0;
                //  double d = Double.parseDouble(number);
                //resultAo = Double.parseDouble(holdQty1o);
            } else {
                resultAo = Double.parseDouble(holdQty1o);
            }

            System.out.print(holdQty1o);
        }
    }

    public void getHoldReason() {
        System.out.print(BARCODEDATAo);
        if (BARCODEDATAo == null) {
            //    holdreson.setEnabled(false);
            holdresonost.setText("");
            Toast.makeText(Finel_QC.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
        } else {
            holdresonost.setEnabled(true);
            holdreson1o = holdresonost.getText().toString();
            if (holdreson1o.isEmpty()) {
                holdreson1o = "";
            }
        }


    }

    public void getAccept() {
        System.out.print(BARCODEDATAo);
        if (BARCODEDATAo == null) {
            acceptQtyost.setText("");
            Toast.makeText(Finel_QC.this, "Please scan the QR code", Toast.LENGTH_SHORT).show();
        } else {
            Acceptqtyo = acceptQtyost.getText().toString();
            System.out.print(Acceptqtyo);
            if (Acceptqtyo.isEmpty()) {
                resultCo = 0.0;
            } else {
                resultCo = Double.parseDouble(Acceptqtyo);
            }
            System.out.print(resultCo);
        }

    }

    private void Resett() {
        /*Reasonlimbley();*/
        LotQtyost.setText("");
        holdresonost.setText("");
        holdQtyost.setText("");
        rejectqtyost.setText("");
        acceptQtyost.setText("");
        resultCo = 0;
        resultBo = 0;
        resultAo = 0;
        BARCODEDATAo = "";
        barcodedataafost.setText("");
    }

    private void SAveanotherto() {


        if (Lotqty1 >= totalqtyo) {
            us1 = (int) Double.parseDouble(String.valueOf(totalqtyo));
            if (us1 == 0) {
                Toast.makeText(Finel_QC.this, "Quantity is 0 ", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(Finel_QC.this, "Quantity is grater than Lot Quantity", Toast.LENGTH_SHORT).show();

        }
        if (ResionIdqo == "0") {
            Toast.makeText(Finel_QC.this, "Please Select the Reason ", Toast.LENGTH_SHORT).show();

        } else {


            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading Data !!");
            progressDialog.show();

            JSONObject userdatadata11 = new JSONObject();
            int userid1 = Integer.parseInt(userido);
            double poActualQtyq = Double.parseDouble(poActualQty);

            try {
                // userdatadata11.put("qcInputId", userid1);
                userdatadata11.put("flag", Source);
                userdatadata11.put("acceptQty", resultCo);
                userdatadata11.put("rejectQty", resultBo);
                userdatadata11.put("rejectReasonMst", ResionIdqo);
                userdatadata11.put("holdQty", resultAo);
                userdatadata11.put("holdReason", holdreson1o);
                userdatadata11.put("operator", userid);
                userdatadata11.put("itemMstId", itemMstId);
                userdatadata11.put("qcInputId", userid1);
                userdatadata11.put("qrcode", BARCODEDATAo);
                userdatadata11.put("poActualQty", poActualQtyq);

                Log.d("@#@#", userdatadata11.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestQueue Inspect = Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest stageRequestInspect_Materialdate1 = new JsonObjectRequest(Request.Method.POST, SAVEANOTHER, userdatadata11, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("@#@ggg#", response.toString());
                    progressDialog.dismiss();
                    System.out.print(response);
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            Qtkk = response.getString("remaingQty");
                            System.out.print(Qtkk);
                        }
                        Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_LONG).show();
                        /*Reasonlimbley();*/
                        RejectionResionost.setAdapter(null);
                        holdresonost.setText("");
                        holdQtyost.setText("");
                        rejectqtyost.setText("");
                        acceptQtyost.setText("");
                        resultCo = 0;
                        resultBo = 0;
                        ResionIdqo = null;
                        resultAo = 0;
                        LotQtyost.setText(Qtkk);
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
                    acceptQtyost.setText("");
                    barcodedataafost.setText("");
                    Toast.makeText(Finel_QC.this, "Something Went Wrong !!!" + error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            stageRequestInspect_Materialdate1.setRetryPolicy(new DefaultRetryPolicy(15000,
                    1,
                    1));
            Inspect.add(stageRequestInspect_Materialdate1);
        }
    }

    private void SAvedatato() {

        if (ResionIdqo == "0") {
            Toast.makeText(getApplicationContext(), "Please select the Reason", Toast.LENGTH_SHORT).show();
        } else {


            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading Data !!");
            progressDialog.show();
            JSONObject userdatadata1 = new JSONObject();
            int userid1 = Integer.parseInt(userido);
            double poActualQtyq = Double.parseDouble(poActualQty);

            try {
                //  userdatadata1.put("qcInputId", userid1);

                userdatadata1.put("flag", Source);
                userdatadata1.put("acceptQty", resultCo);
                userdatadata1.put("rejectQty", resultBo);
                userdatadata1.put("rejectReasonMst", ResionIdqo);
                userdatadata1.put("holdQty", resultAo);
                userdatadata1.put("holdReason", holdreson1o);
                userdatadata1.put("operator", userid);
                userdatadata1.put("itemMstId", itemMstId);
                userdatadata1.put("qcInputId", userid1);
                userdatadata1.put("qrcode", BARCODEDATAo);
                userdatadata1.put("poActualQty", poActualQtyq);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestQueue Inspect = Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest stageRequestInspect_Materialdateoo = new JsonObjectRequest(Request.Method.POST, SAVEDATA, userdatadata1, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("@#@ggg#", response.toString());
                    progressDialog.dismiss();

                    try {
                        for (int i = 0; i < response.length(); i++) {
                            Qtkk = response.getString("remaingQty");
                            System.out.print(Qtkk);
                            //System.out.print(iddkk);
                        }
                        Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_LONG).show();
                        /*Reasonlimbley();*/
                        RejectionResionost.setAdapter(null);
                        LotQtyost.setText("");
                        ResionIdqo = null;
                        holdresonost.setText("");
                        holdQtyost.setText("");
                        rejectqtyost.setText("");
                        acceptQtyost.setText("");
                        resultCo = 0.0;
                        resultBo = 0.0;
                        resultAo = 0.0;
                        BARCODEDATAo = "";
                        barcodedataafost.setText("");

                        //  LotQty.setText(lotQty);

                    } catch (Exception e) {
                        progressDialog.dismiss();

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

                    Toast.makeText(Finel_QC.this, "Something Went Wrong !!!", Toast.LENGTH_SHORT).show();
                    acceptQtyost.setText("");
                    barcodedataafost.setText("");
                }
            });
            stageRequestInspect_Materialdateoo.setRetryPolicy(new DefaultRetryPolicy(15000,
                    1,
                    1));
            Inspect.add(stageRequestInspect_Materialdateoo);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String resultContents = result.getContents();
        if (resultContents == null) {
            Toast.makeText(Finel_QC.this, "No Data Found !! Pleasse Scan Again", Toast.LENGTH_SHORT).show();
        } else {
            Seperate(resultContents);
            Toast.makeText(Finel_QC.this, resultContents, Toast.LENGTH_LONG).show();
            resultContents.length();
            StringTokenizer st = new StringTokenizer(resultContents, "\n|");
            BARCODEDATAo = st.nextToken();
            System.out.println(BARCODEDATAo);
            Acceptqty1dd = BARCODEDATAo.substring(4);
            barcodedataafost.setText(Acceptqty1dd);
            //Toast.makeText(Finel_material_inspActivity.this, BARCODEDATA, Toast.LENGTH_SHORT).show();
            Seperate(resultContents);
            GETMaterialQTY();
        }
        System.out.println("" + resultContents);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void GETMaterialQTY() {

        if (BARCODEDATAo.equalsIgnoreCase("false")) {
            Toast.makeText(Finel_QC.this, "No Data Found !! Pleasse Scan Again", Toast.LENGTH_SHORT).show();
        } else {
            String QRCODECODEQTY = Config.BASE_URL + "qualityCheck/qcScanQrCode";
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading Data !!");
            progressDialog.show();
            JSONObject userdatadata = new JSONObject();
            QRCODECODEQTY = QRCODECODEQTY + "?qrcode=" + BARCODEDATAo;
            JsonObjectRequest stageRequestdateqqq = new JsonObjectRequest(Request.Method.GET, QRCODECODEQTY, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    progressDialog.hide();
                    try {
                        Log.d("Rohitt", response.toString());
                        for (int i = 0; i < response.length(); i++) {
                            lotQtyo = response.getString("remaingQty");
                            userido = response.getString("qcInputId");
                            itemMstId = response.getString("itemMstId");
                            Source = response.getString("source");
                            poActualQty = response.getString("poActualQty");

                        }
                        LotQtyost.setText(lotQtyo);
                        opraterost.setText(userid);
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

                    } /*else if (error instanceof AuthFailureError) {

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
                    }*/
                    LotQtyost.setText("0");
                    Toast.makeText(getApplicationContext(), "Wrong_OR code", Toast.LENGTH_LONG).show();
                }
            });
            RequestQueue ddrq = Volley.newRequestQueue(getApplicationContext());
            ddrq.add(stageRequestdateqqq);
        }
    }

    private void Seperate(String resultContents) {
    }

    public void Reasonlimbley() {

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
                ArrayAdapter<String> spinnerArrayrr = new ArrayAdapter<String>(Finel_QC.this, android.R.layout.simple_spinner_dropdown_item, reasonname);
                RejectionResionost.setAdapter(spinnerArrayrr);
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
                Toast.makeText(getApplicationContext(), "Wrong_OR code", Toast.LENGTH_LONG).show();
                Log.d("@#@#", error.getMessage());
            }
        });

        ddrq.add(stageRequestdatezz);
        RejectionResionost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data = (String) parent.getItemAtPosition(position);
                Log.d("@@@@", data);
                if (data.equalsIgnoreCase("select Material")) {
                    ResionIdqo = "0";
                } else {
                    Log.d("@#@#1", String.valueOf(RejectionResionost.getItemIdAtPosition(position)));
                    ResionIdqo = ResionId.get(position);
                    reasonnameqo = reasonname.get((int) RejectionResionost.getItemIdAtPosition(position));
                    Log.d("@#", data);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
}
