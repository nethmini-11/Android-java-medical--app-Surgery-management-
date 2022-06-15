package com.app.droidcell.medizone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class surgeryHomepage extends AppCompatActivity {

    Button surbut,billbut,letterbut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surgry_homepage);


        surbut= findViewById(R.id.surbut);

        surbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(surgeryHomepage.this,front.class);
                startActivity(intent);



            }
        });


        billbut = findViewById(R.id.billbut);

        billbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(surgeryHomepage.this,splashScreen.class);
                startActivity(intent);



            }
        });


        letterbut = findViewById(R.id.letterbut);

        letterbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(surgeryHomepage.this,LetterMain.class);
                startActivity(intent);



            }
        });










    }
}