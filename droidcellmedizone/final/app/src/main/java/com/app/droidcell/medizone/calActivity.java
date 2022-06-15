package com.app.droidcell.medizone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class calActivity extends AppCompatActivity {
//total values
    ImageView img;
    TextView tv1,tv2,tv3,curt;
    float totalVal;
    Button summary;
    String str="";
    private long backPressedTime ;
    private  Toast backToast;

    //Exit from the app if back pressed from home screen
    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis() )
        {
            backToast.cancel();
            super.onBackPressed();
            return;
        }
        else
        {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }





    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {



            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);

        img = findViewById(R.id.plusSign);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        summary = findViewById(R.id.summary);
        curt = findViewById(R.id.curt);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View view = LayoutInflater.from(calActivity.this).inflate(R.layout.activity_alert_box,null);
                final EditText doc = (EditText) view.findViewById(R.id.doc);
                final EditText rate = (EditText) view.findViewById(R.id.rate);
                final EditText qty = (EditText) view.findViewById(R.id.qty);
                final EditText nm = (EditText) view.findViewById(R.id.name);
                final EditText ins = (EditText) view.findViewById(R.id.ins);

                 AlertDialog.Builder builder = new AlertDialog.Builder(calActivity.this);
                 builder.setMessage("    INPUT YOUR DETAILS")
                         .setView(view)
                         .setPositiveButton("CALCULATE", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {

                         //Entries calculation part
                         String r1 = rate.getText().toString();
                         String q1 = qty.getText().toString();
                         String i1 = ins.getText().toString();
                         String t1= doc.getText().toString();
                         String name = nm.getText().toString();

                         int x1=0,x2=0,x3=0,x4 = 0;
                         if(r1.length()==0)
                         {
                             r1 = "0";
                             x1=1;
                         }
                         if(q1.length()==0)
                         {
                             q1 = "0";
                             x2=1;
                         }
                         if(i1.length()==0)
                         {
                             i1 = "0";
                             x3=1;
                         }
                         
                         if(name.length()==0)
                         {
                             name = "Empty name";
                         }
                         if(t1.length()==0)
                         {

                             t1 = "0";
                             x4=1;
                         }

                         if(x1==1 ||x2==1 ||x3==1 ||x4==1)
                         {
                             String addon="";
                             if(x1==0)
                             {
                                 addon="in quantity";
                             }
                             else if(x2==0)
                             {
                                 addon="in rate";
                             }
                             else if(x3==0)
                             {
                                 addon="in insurance";
                             }
                             else if(x3==0)
                             {
                                 addon="in doc";
                             }
                             
                             Toast.makeText(calActivity.this, " Must enter a value next time "+ addon , Toast.LENGTH_SHORT).show();
                         }

                         double r = Double.parseDouble(r1);
                         int q = Integer.parseInt(q1);
                         double i=Double.parseDouble(i1);
                         double t = Double.parseDouble(t1);
                         double c=10000;

                         Double k = (r*q)+t-i+c;
                         tv1.setText("> "+k+" <");
                         totalVal+=k;

                         str+="\n" + name + ":    " + t1 + "+" + r1 + " X " + q1 + "-" + i1 + "+" + c + " =  \u20A8 " + k;
                         str+="\n ----------------------------------------------------------- ";
                         str+="\n Doctor Fee : " +t;
                         str+="\n Selected Package Fee : " +r;
                         str+="\n Number of days prescribed for Surgery : " +q;
                         str+="\n Insurance Coverage : " +i;
                         str+="\n Hospital Charge : " +c;
                         str+="\n ----------------------------------------------------------- ";
                         tv3.setVisibility(View.VISIBLE);
                         curt.setVisibility(View.VISIBLE);
                         summary.setVisibility(View.VISIBLE);
                         tv2.setText(totalVal+"");


                     }
                 }).setNegativeButton("CANCEL",null).setCancelable(false);

                 AlertDialog alert = builder.create();
                 alert.show();


            }
        });

        summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(calActivity.this,summary.class);
                String strNew = str+ "\n\nTotal Amount:  \u20A8 " + totalVal;

                intent.putExtra("detailSummary",strNew);
                startActivity(intent);
            }
        });

    }
}