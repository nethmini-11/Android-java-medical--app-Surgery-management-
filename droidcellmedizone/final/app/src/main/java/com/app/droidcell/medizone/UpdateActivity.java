package com.app.droidcell.medizone;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class UpdateActivity extends AppCompatActivity {

    EditText pid_input,fname_input, con_input, typ_input,phone_input,trustee_input;
    Button update_button, delete_button;

    String id,pid,fname, con, typ,phone,trustee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        pid_input = findViewById(R.id.pid_input2);
        fname_input = findViewById(R.id.fname_input2);
        con_input = findViewById(R.id.con_input2);
        typ_input = findViewById(R.id.typ_input2);
        phone_input = findViewById(R.id.phone_input2);
        trustee_input = findViewById(R.id.trustee_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);


        getAndSetIntentData();

         
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(pid);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                pid = pid_input.getText().toString().trim();
                fname = fname_input.getText().toString().trim();
                con = con_input.getText().toString().trim();
                typ = typ_input.getText().toString().trim();
                phone = phone_input.getText().toString().trim();
                trustee = trustee_input.getText().toString().trim();
                myDB.updateData(id, pid, fname, con, typ, phone, trustee);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("fname") &&
                getIntent().hasExtra("con") && getIntent().hasExtra("typ") &&
                getIntent().hasExtra("phone")&& getIntent().hasExtra("trustee") ){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            pid = getIntent().getStringExtra("pid");
            fname= getIntent().getStringExtra("fname");
            con = getIntent().getStringExtra("con");
            typ = getIntent().getStringExtra("typ");
            phone = getIntent().getStringExtra("phone");
            trustee = getIntent().getStringExtra("trustee");

            //Setting Intent Data
            pid_input.setText(pid);
            fname_input.setText(fname);
            con_input.setText(con);
            typ_input.setText(typ);
            phone_input.setText(phone);
            trustee_input.setText(trustee);
            Log.d("stev", pid+" "+fname+" "+ con +" "+ typ +""+phone+""+trustee);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + pid + " ?");
        builder.setMessage("Are you sure you want to delete " + pid + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
