package com.sgcreatives.a0004;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class optionspage extends AppCompatActivity {
    Button op1,op2,op3,op4;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optionspage);
        op1=findViewById(R.id.birthcertificate);
        op2=findViewById(R.id.deathcertificate);
        op3=findViewById(R.id.Votersid);
        op4=findViewById(R.id.Incometax);
        op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ione= new Intent(getApplicationContext(),BirthCertficate.class);
                startActivity(ione);
            }
        });
        op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itwo= new Intent(getApplicationContext(),DeathCertificate.class);
                startActivity(itwo);
            }
        });
        op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ithree= new Intent(getApplicationContext(),Votersid.class);
                startActivity(ithree);
            }
        });
        op4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ifour= new Intent(getApplicationContext(),IncomeTax.class);
                startActivity(ifour);
            }
        });
    }
}
