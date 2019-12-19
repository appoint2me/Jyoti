package com.example.a2mee.jyoti.com.net.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.a2mee.jyoti.R;
import com.example.a2mee.jyoti.com.net.adapters.ItemAdapter;
import com.example.a2mee.jyoti.com.net.models.ItemDetailsModel;
import com.example.a2mee.jyoti.com.net.utils.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LocationPutAwayActivity extends AppCompatActivity {
    RecyclerView recyclerViewItemDetails;
    ArrayList<ItemDetailsModel> listItemDetails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_put_away);
        recyclerViewItemDetails = findViewById(R.id.recyclerViewItemDetails);
        getItemDetails();

    }

    private void getItemDetails() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Config.URL_GET_ITEM_DETAILS, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String grnNo = jsonObject.getString("grnNo");
                        String grnEntryDate = jsonObject.getString("grnEntryDate");
                        String itemDetails = jsonObject.getString("itemDetails");
                        String itemId = jsonObject.getString("itemId");
                        String qrCode = jsonObject.getString("qrCode");
                        ItemDetailsModel itemDetailsModel = new ItemDetailsModel(qrCode, itemDetails, grnEntryDate, grnNo,itemId);
                        listItemDetails.add(itemDetailsModel);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(LocationPutAwayActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                ItemAdapter itemDetailsAdapter = new ItemAdapter(LocationPutAwayActivity.this, listItemDetails);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(LocationPutAwayActivity.this);
                recyclerViewItemDetails.setLayoutManager(layoutManager);
                recyclerViewItemDetails.setAdapter(itemDetailsAdapter);
                recyclerViewItemDetails.setHasFixedSize(true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LocationPutAwayActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(LocationPutAwayActivity.this,MainActivity.class));
    }
}
