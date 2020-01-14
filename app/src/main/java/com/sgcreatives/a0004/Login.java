package com.sgcreatives.a0004;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class Login extends AppCompatActivity  {
    EditText txt7,txt8;
    TextView tv2, tv3;
    SharedPreferences sharedPreferences;
    Button btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
          txt7 =findViewById(R.id.login_email);
          txt8 =findViewById(R.id.login_pass);
         tv2= findViewById(R.id.register_now);
         tv3 = findViewById(R.id.forgot_pass);
          btn2 =findViewById(R.id.login_proceed);
          sharedPreferences=getSharedPreferences("asd",MODE_PRIVATE);
          btn2.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  if(!(txt7.getText().toString().isEmpty()||txt8.getText().toString().isEmpty()))
                  {

                      StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://hastalavistaresto.000webhostapp.com/civilregistry/crlogin.php",
                              new Response.Listener<String>() {
                                  @Override
                                  public void onResponse(String response) {
//If we are getting success from server
                                      if (response.equals("Success")) {
                                          Toast.makeText(Login.this,"Logged in Succesfully", Toast.LENGTH_LONG).show();

                                          Intent ii = new Intent(getApplicationContext(),optionspage.class);
                                          startActivity(ii);
                                      }
                                      else
                                      {
                                          Toast.makeText(Login.this,"Unsuccessfull attempt",Toast.LENGTH_SHORT).show();
                                      }
                                      try {
                                          JSONArray jsonArray=new JSONArray(response);
                                          for(int i=0;i<jsonArray.length();i++){
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

                              }){
                          @Override
                          protected Map<String, String> getParams() throws AuthFailureError {
                              Map<String,String> params = new HashMap<>();
//Adding parameters to request

                              params.put("email_id",txt7.getText().toString());
                              params.put("password",txt8.getText().toString());

//returning parameter
                              return params;
                          }
                      };

//Adding the string request to the queue
                      RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
                      requestQueue.add(stringRequest);

                  }
                  else
                  {
                      Toast.makeText(Login.this,"enter values correctly", Toast.LENGTH_LONG).show();

                  }
              }
          });
          tv3.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  SharedPreferences.Editor editor = sharedPreferences.edit();
                  editor.putString("email",txt7.getText().toString());
                  editor.apply();
                  Intent io= new Intent(getApplicationContext(),forgotpassword.class);
                  startActivity(io);
              }
          });
          tv2.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent is= new Intent(getApplicationContext(),Registration.class);
                  startActivity(is);

              }
          });
    }
}

