package com.sgcreatives.a0004;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
                Thread spalsher = new Thread() {
                    public void run() {
                        try {
                            sleep(5*1000);
                            Intent i = new Intent(getBaseContext(), Login.class);
                            startActivity(i);

                            finish();
                        }
                        catch(Exception e)
                        {

                        }

                    }

                };
                spalsher.start();

            }

        }