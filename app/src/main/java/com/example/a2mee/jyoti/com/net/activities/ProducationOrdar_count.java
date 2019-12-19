package com.example.a2mee.jyoti.com.net.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.example.a2mee.jyoti.com.net.utils.Config.password_SHARED_PREF;


public class ProducationOrdar_count extends Activity {

    String savegoodbadcount = Config.BASE_URL + "proOrd/saveGoodBadCount";
    TextView datee1;
    EditText goodpris1;
    Spinner mechinno1, hourr1, Modulno1, itemdiscription1;
    Button butttonsub;
    String datestring, MaterialId1,good_count, ddddddd, MOdulID1dd, IDDss, useridu, MOdulID1, reasonname1, hourmaterialIdqq, houridqqa, houridqq, goodpris1count, badpices1count, IDDgg;

    private ArrayList<String> MaterialId;
    private ArrayList<String> ID;
    private ArrayList<String> Numberk;
    private ArrayList<String> dtl;
    private ArrayList<String> MOdulmaterialId1;
    private ArrayList<String> MOdulID;
    private ArrayList<String> MOduldtl;
    private ArrayList<String> MOdulnumber;
    private ArrayList<String> MOdulmaterialId111;
    private ArrayList<String> MOdulID11;
    private ArrayList<String> MOdulnumber11;
    private ArrayList<String> MOduldtl11;
    private ArrayList<String> hourmaterialId;
    private ArrayList<String> hourid;
    private ArrayList<String> hournumbe;
    private ArrayList<String> hourdtl;
    private ArrayList<String> count_good;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_producation_ordar_count);

        SharedPreferences sharedPreferences = getSharedPreferences(Config.mylogin_PREF_NAME, Context.MODE_PRIVATE);
        useridu = sharedPreferences.getString(password_SHARED_PREF, null);
        System.out.print(useridu);
        goodpris1 = findViewById(R.id.goodpris1);
        // badpices1 = findViewById(R.id.badpices1);
        // Spinner
        mechinno1 = findViewById(R.id.mechinno1);
        hourr1 = findViewById(R.id.hourr1);
        Modulno1 = findViewById(R.id.Modulno1);
        itemdiscription1 = findViewById(R.id.itemdiscription1);
        //Button
        butttonsub = findViewById(R.id.butttonsub);
        //EditText
        datee1 = findViewById(R.id.datee1);
        datee1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();
                int yy = calendar.get(Calendar.YEAR);
                int MM = calendar.get(Calendar.MONTH);
                int dd = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePicker1 = new DatePickerDialog(ProducationOrdar_count.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        datestring = year + "-" + (monthOfYear + 1)
                                + "-" + dayOfMonth;
                        datee1.setText(datestring);

                        System.out.print(datestring);
                        Mechineno_select();
                        // Toast.makeText(getApplication(), "date " + datestring, Toast.LENGTH_SHORT).show();
                    }
                }, yy, MM, dd);

                datePicker1.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePicker1.show();


            }

        });

        if (datestring == null) {
            Toast.makeText(getApplicationContext(), "Select Date Please", Toast.LENGTH_LONG).show();
            // datestring = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        }
        butttonsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ddddddd == null && IDDss == null) {
                    Toast.makeText(getApplicationContext(), "Select all  Please", Toast.LENGTH_LONG).show();

                } else {
                    Submitproduction_count();
                }

            }
        });
    }

    public void Mechineno_select() {

        String Materialname_Url = Config.BASE_URL + "proOrd/getMachineNumber";
        if (datestring == null) {
            Toast.makeText(getApplicationContext(), "Select Date Please", Toast.LENGTH_LONG).show();
        } else {
            MaterialId = new ArrayList<>();
            ID = new ArrayList<>();
            Numberk = new ArrayList<>();
            dtl = new ArrayList<>();

            JSONObject userdatadata = new JSONObject();
            RequestQueue ddrqsadd = Volley.newRequestQueue(getApplicationContext());
            Materialname_Url = Materialname_Url + "?date=" + datestring;
            JsonArrayRequest stageRequestdateggzz = new JsonArrayRequest(Request.Method.GET, Materialname_Url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    if (response.equals(null)) {
                        System.out.print(response);
                    } else {
                        try {
                            ID.add("Please Select ");
                            dtl.add("Please Select ");
                            // Log.d("@rOGH@", response.toString());
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObjects = response.getJSONObject(i);
                                MaterialId.add(jsonObjects.getString("materialId"));
                                ID.add(jsonObjects.getString("id"));
                                Numberk.add(jsonObjects.getString("number"));
                                dtl.add(jsonObjects.getString("dtl"));
                                //Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_LONG).show();
                                System.out.print(MaterialId);
                                System.out.print(ID);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    ArrayAdapter<String> spinnerArrayrr = new ArrayAdapter<String>(ProducationOrdar_count.this, android.R.layout.simple_spinner_dropdown_item, dtl);
                    mechinno1.setAdapter(spinnerArrayrr);
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


                    Log.d("@#@#", error.getMessage());
                }
            });
            ddrqsadd.add(stageRequestdateggzz);

            mechinno1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String data1 = (String) parent.getItemAtPosition(position);
                    Log.d("@@@@", data1);
                    System.out.print(data1);
                    if (data1 == "Please Select ") {

                    } else {
                        Log.d("@#@#1", String.valueOf(mechinno1.getItemIdAtPosition(position)));
                        //  MaterialId1 = MaterialId.get(position);
                        IDDss = ID.get(position);
                        Log.d("@#", IDDss);
                        Log.d("@#", IDDss);

                        System.out.print(IDDss);

                        //  IDDgg  = dtl.get((int) mechinno1.getItemIdAtPosition(position));

                        Log.d("@rigg", IDDss);
                        System.out.print(IDDss);
                        MOdulNOLIST();
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
    }

    private void MOdulNOLIST() {

        String MouldNoList_URL = Config.BASE_URL + "proOrd/getMouldNumber";
        MOdulmaterialId1 = new ArrayList<>();
        MOdulID = new ArrayList<>();
        MOdulnumber = new ArrayList<>();
        MOduldtl = new ArrayList<>();
        //   final ArrayList<String> Stringdatar = new ArrayList<String>();
        JSONObject userdatadata = new JSONObject();
        RequestQueue ddrqss = Volley.newRequestQueue(getApplicationContext());
        MouldNoList_URL = MouldNoList_URL + "?machineId=" + IDDss + "&date=" + datestring;
        JsonArrayRequest stageRequestdatezzddd = new JsonArrayRequest(Request.Method.GET, MouldNoList_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    MOduldtl.add("Please Select ");
                    // Log.d("@rOGH@", response.toString());
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObjects1 = response.getJSONObject(i);
                        MOdulmaterialId1.add(jsonObjects1.getString("materialId"));
                        MOdulID.add(jsonObjects1.getString("id"));
                        MOdulnumber.add(jsonObjects1.getString("number"));
                        MOduldtl.add(jsonObjects1.getString("dtl"));
                        //Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_LONG).show();
                    }
                    System.out.print(MOdulmaterialId1);
                    System.out.print(MOduldtl);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ArrayAdapter<String> spinnerArrayrr = new ArrayAdapter<String>(ProducationOrdar_count.this, android.R.layout.simple_spinner_dropdown_item, MOduldtl);
                Modulno1.setAdapter(spinnerArrayrr);
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
                Log.d("@#@#", error.getMessage());
            }
        });

        ddrqss.add(stageRequestdatezzddd);

        Modulno1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data2 = (String) parent.getItemAtPosition(position);
                Log.d("@@@@", data2);

                if (data2.equalsIgnoreCase("Please Select ")) {
                } else {
                    Log.d("@#@#1", String.valueOf(Modulno1.getItemIdAtPosition(position)));
                    MOdulID1dd = MOduldtl.get(position);
                    System.out.print(MOdulID1dd);
                    System.out.print(MOdulID1dd);
                    //  reasonname1 = MOdulnumber.get((int) Modulno1.getItemIdAtPosition(position));
                    Log.d("@#", data2);
                    ItemListSelect();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void ItemListSelect() {

        String ItemList1_URL = Config.BASE_URL + "proOrd/getItemDetail";
        MOdulmaterialId111 = new ArrayList<>();
        MOdulID11 = new ArrayList<>();
        MOdulnumber11 = new ArrayList<>();
        MOduldtl11 = new ArrayList<>();
        //   final ArrayList<String> Stringdatar = new ArrayList<String>();
        //  JSONObject userdatadata = new JSONObject();
        RequestQueue ddrqssss = Volley.newRequestQueue(getApplicationContext());
        ItemList1_URL = ItemList1_URL + "?mouldId=" + MOdulID1dd + "&date=" + datestring;
        JsonArrayRequest stageRequestdatezzdddddd = new JsonArrayRequest(Request.Method.GET, ItemList1_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    MOduldtl11.add("Please Select ");
                    // Log.d("@rOGH@", response.toString());
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObjects1a = response.getJSONObject(i);
                        MOdulmaterialId111.add(jsonObjects1a.getString("materialId"));
                        MOdulID11.add(jsonObjects1a.getString("id"));
                        MOdulnumber11.add(jsonObjects1a.getString("number"));
                        MOduldtl11.add(jsonObjects1a.getString("dtl"));

                        //Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_LONG).show();
                        System.out.print(MOdulmaterialId111);
                        System.out.print(MOduldtl11);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ArrayAdapter<String> spinnerArrayrr = new ArrayAdapter<String>(ProducationOrdar_count.this, android.R.layout.simple_spinner_dropdown_item, MOduldtl11);
                itemdiscription1.setAdapter(spinnerArrayrr);
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
                Log.d("@#@#", error.getMessage());
            }
        });
        ddrqssss.add(stageRequestdatezzdddddd);
        itemdiscription1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data3 = (String) parent.getItemAtPosition(position);
                Log.d("@@@@", data3);
                if (data3.equalsIgnoreCase("Please Select ")) {
                } else {
                    Log.d("@#@#1", String.valueOf(Modulno1.getItemIdAtPosition(position)));
                    ddddddd = MOdulmaterialId111.get(position - 1);
                    //   reasonname1 = MOdulmaterialId111.get((int) Modulno1.getItemIdAtPosition(position));
                    Log.d("@#", data3);
                    hoursloteList();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void hoursloteList() {

        String hoursloteList = Config.BASE_URL +"proOrd/getHourSlote";
        hourmaterialId = new ArrayList<>();
        hourid = new ArrayList<>();
        hournumbe = new ArrayList<>();
        hourdtl = new ArrayList<>();
        count_good = new ArrayList<>();
        //   final ArrayList<String> Stringdatar = new ArrayList<String>();
        // JSONObject userdatadata = new JSONObject();
        RequestQueue ddrq = Volley.newRequestQueue(getApplicationContext());
        hoursloteList = hoursloteList + "?itemId=" + ddddddd + "&date=" + datestring;
        hoursloteList = hoursloteList.replace(" ", "%20");
        JsonArrayRequest stageRequestdatezz = new JsonArrayRequest(Request.Method.GET, hoursloteList, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    hourid.add("Please Select ");
                    hournumbe.add("Please Select ");
                    count_good.add("Please Select");
                    // Log.d("@rOGH@", response.toString());
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObjects = response.getJSONObject(i);
                        hourmaterialId.add(jsonObjects.getString("materialId"));
                        hourid.add(jsonObjects.getString("id"));
                        hournumbe.add(jsonObjects.getString("number"));
                        hourdtl.add(jsonObjects.getString("dtl"));
                        count_good.add(jsonObjects.getString("avalibleGoodPices"));
                        //Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_LONG).show();
                        System.out.print(hourmaterialId);
                        System.out.print(hourid);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ArrayAdapter<String> spinnerArrayrr = new ArrayAdapter<String>(ProducationOrdar_count.this, android.R.layout.simple_spinner_dropdown_item, hournumbe);
                hourr1.setAdapter(spinnerArrayrr);
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
                Log.d("@#@#", error.getMessage());
            }
        });

        ddrq.add(stageRequestdatezz);
        hourr1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data5 = (String) parent.getItemAtPosition(position);
                Log.d("@@@@", data5);
                if (data5.equalsIgnoreCase("Please Select ")) {
                } else {
                    Log.d("@#@#1", String.valueOf(hourr1.getItemIdAtPosition(position)));
                    houridqq = hourid.get(position);
                    System.out.print(houridqq);
                    good_count = count_good.get(position);
                    if (good_count == null || good_count.equals("")) {
                        goodpris1.setText("");
                    } else {
                       goodpris1.setText(good_count);
                    }
                    Log.d("good_count", good_count);
                    Log.d("@#", houridqq);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void Submitproduction_count() {
        goodpris1count = goodpris1.getText().toString();
        //  badpices1count = badpices1.getText().toString();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data !!");
        progressDialog.show();
        JSONObject userdatadata11 = new JSONObject();

        //  int proOrdShiftCodeIdq = Integer.parseInt(hourmaterialIdqq);
        System.out.println(hourmaterialIdqq);

        int goodCountq = Integer.parseInt(goodpris1count);

        System.out.println(goodpris1count);
        System.out.println(goodCountq);

        // int badCountq = Integer.parseInt(badpices1count);
//        //  int ResionIdqq = Integer.parseInt(userid);

        try {
            userdatadata11.put("proOrdShiftCodeId", houridqq);
            userdatadata11.put("goodCount", goodCountq);
            userdatadata11.put("badCount", 0);
            userdatadata11.put("uId", useridu);
            //  Log.d("@#@#", userdatadata11.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest stageRequestInspect_Materialdate1 = new JsonObjectRequest(Request.Method.POST, savegoodbadcount, userdatadata11, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("@#@ggg#", response.toString());
                progressDialog.dismiss();

                try {
                    hoursloteList();
                    Toast.makeText(ProducationOrdar_count.this, " Submit !!!", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {

                }
          //      goodpris1.setText(good_count);


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
                Toast.makeText(ProducationOrdar_count.this, "Something Went Wrong !!!", Toast.LENGTH_SHORT).show();
            }

        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("Accept", "application/json");
                return params;
            }
        };
        RequestQueue Inspect = Volley.newRequestQueue(getApplicationContext());
        Inspect.add(stageRequestInspect_Materialdate1);
///




    }
}
