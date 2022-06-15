package com.app.droidcell.medizone;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;



public class LetterMain extends AppCompatActivity {

    ListView letterList;
    Button btnAdd;

    DbLetter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surgeryhome);

        db = new DbLetter(this);

        letterList = (ListView) findViewById(R.id.letterList);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LetterMain.this, AddLetter.class);
                startActivity(intent);

            }
        });


        letterList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Letter selected_letter = (Letter) parent.getItemAtPosition(position);

                Intent intent = new Intent(LetterMain.this, UpdateLetter.class);

                intent.putExtra("id", selected_letter.getId());

                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Letter> letters = db.getAllLetters();

        letterAdapter letterAdapter = new letterAdapter(this, R.layout.letter_list, letters);

        letterList.setAdapter(letterAdapter);

    }
}
