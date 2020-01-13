package com.sgcreatives.a0004;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class DeathCertificate extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] sex = {"Male", "Female", "Others"};
    EditText txt13, txt14, txt15, txt16;
    Spinner gender1;
    Button btn5, btn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_death_certificate);
        txt13 = findViewById(R.id.bc_name);
        gender1 =findViewById(R.id.gender);
        txt14 = findViewById(R.id.bc_place);
        txt15 = findViewById(R.id.bc_dob);
        txt16 = findViewById(R.id.bc_fathername);
        btn5 = findViewById(R.id.bc_apply);
        gender1.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sex);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender1.setAdapter(aa);
        btn6 = findViewById(R.id.bc_pdfdwn);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(txt13.getText().toString().isEmpty() || txt14.getText().toString().isEmpty() || txt15.getText().toString().isEmpty() || txt16.getText().toString().isEmpty())) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://hastalavistaresto.000webhostapp.com/civilregistry/votersid.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

//If we are getting success from server
                                    if (response.equals("Success")) {

                                        Toast.makeText(DeathCertificate.this, "Your application for Death Certificate has been registered ", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(DeathCertificate.this, "Form not Submitted", Toast.LENGTH_SHORT).show();
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

                            params.put("name", txt13.getText().toString());
                            params.put("fhname", txt14.getText().toString());
                            params.put("dob", txt15.getText().toString());
                            params.put("sex",gender1.getSelectedItem().toString());
                            params.put("address", txt16.getText().toString());

//returning parameter
                            return params;
                        }
                    };

//Adding the string request to the queue
                    RequestQueue requestQueue = Volley.newRequestQueue(DeathCertificate.this);
                    requestQueue.add(stringRequest);

                } else {
                    Toast.makeText(DeathCertificate.this, "enter values correctly", Toast.LENGTH_SHORT).show();
                }

            }


        });


    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        Toast.makeText(DeathCertificate.this,"Sex not selected",Toast.LENGTH_SHORT).show();

    }
}






