package com.app.droidcell.medizone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;




public class AddActivity extends AppCompatActivity {

    EditText pid_input,fname_input,con_input,typ_input,phone_input,trustee_input;
    Button add_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        pid_input = findViewById(R.id.pid_input);
        fname_input = findViewById(R.id.fname_input);
        con_input = findViewById(R.id.con_input);
        typ_input = findViewById(R.id.typ_input);
        phone_input = findViewById(R.id.phone_input);
        trustee_input= findViewById(R.id.trustee_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(pid_input.getText()) ){
                    pid_input.setError("Required");
                    pid_input.requestFocus();
                }
                if (TextUtils.isEmpty(fname_input.getText()) ){
                    fname_input.setError("Required");
                    fname_input.requestFocus();
                }
                if (TextUtils.isEmpty(con_input.getText()) ){
                    con_input.setError("Required");
                    con_input.requestFocus();
                }
                if (TextUtils.isEmpty(typ_input.getText()) ){
                    typ_input.setError("Required");
                    typ_input.requestFocus();
                }
                if (TextUtils.isEmpty(phone_input.getText()) ){
                    phone_input.setError("Required");
                    phone_input.requestFocus();
                }
                if (TextUtils.isEmpty(trustee_input.getText()) ){
                    trustee_input.setError("Required");
                    trustee_input.requestFocus();
                }
                else {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                    myDB.addPatient(pid_input.getText().toString().trim(),
                            fname_input.getText().toString().trim(),
                            con_input.getText().toString().trim(),
                            typ_input.getText().toString().trim(),
                            Integer.valueOf(phone_input.getText().toString().trim()),
                            trustee_input.getText().toString().trim());


                }
            }
        });
    }
}

