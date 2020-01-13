package com.sgcreatives.a0004;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Votersid extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] sex = {"Male", "Female", "Others"};
    EditText txt17, txt18, txt19, txt20;
    Spinner gender3;
    Button btn7, btn8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votersid);
        txt17 = findViewById(R.id.bc_name);
        gender3 =findViewById(R.id.gender);
        txt18 = findViewById(R.id.bc_place);
        txt19 = findViewById(R.id.bc_dob);
        txt20 = findViewById(R.id.bc_fathername);
        btn7 = findViewById(R.id.bc_apply);
        gender3.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sex);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender3.setAdapter(aa);
        btn8 = findViewById(R.id.bc_pdfdwn);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(txt17.getText().toString().isEmpty() || txt18.getText().toString().isEmpty() || txt19.getText().toString().isEmpty() || txt20.getText().toString().isEmpty())) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

//If we are getting success from server
                                    if (response.equals("Success")) {

                                        Toast.makeText(Votersid.this, "Your application for Votersid has been registered ", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(Votersid.this, "Form not Submitted", Toast.LENGTH_SHORT).show();
                                    }
                                    try {
                                        JSONArray jsonArray = new JSONArray(response);
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject json_obj = jsonArray.getJSONObject(i);

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
//You can handle error here if you want
                                }

                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
//Adding parameters to request

                            params.put("name", txt17.getText().toString());
                            params.put("fhname", txt18.getText().toString());
                            params.put("DOB", txt19.getText().toString());
                            params.put("sex",gender3.getSelectedItem().toString());
                            params.put("address", txt20.getText().toString());

//returning parameter
                            return params;
                        }
                    };

//Adding the string request to the queue
                    RequestQueue requestQueue = Volley.newRequestQueue(Votersid.this);
                    requestQueue.add(stringRequest);

                } else {
                    Toast.makeText(Votersid.this, "enter values correctly", Toast.LENGTH_SHORT).show();
                }

            }


        });


    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        Toast.makeText(Votersid.this,"Sex not selected",Toast.LENGTH_SHORT).show();

    }
}






