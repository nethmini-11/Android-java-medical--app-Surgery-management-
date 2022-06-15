package com.app.droidcell.medizone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class DbLetter extends SQLiteOpenHelper {

    private static final String DB_NAME = "insurance_db";
    private static final int DB_VESION = 2;

    private static final String KEY_ID = "id";
    private static final String KEY_NOTE = "note";
    private static final String KEY_IMG = "image";

    private static final String TABLE_CONTACT = "contacts";


    public DbLetter(Context context) {
        super(context, DB_NAME, null, DB_VESION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_table = "create table " + TABLE_CONTACT + "(" + KEY_ID + " integer primary key autoincrement,"
                + KEY_NOTE + " varchar(255) DEFAULT'',"
                + KEY_IMG + " blob)";

        Log.d("create", create_table);
        db.execSQL(create_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String delete_query = "DROP table if exists " + TABLE_CONTACT;
        db.execSQL(delete_query);

        onCreate(db);

    }


    public void addLetter(Letter letter) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOTE, letter.getNote());
        values.put(KEY_IMG, letter.getImage());

        db.insert(TABLE_CONTACT, null, values);

    }

    public ArrayList<Letter> getAllLetters() {
        ArrayList<Letter> letters = new ArrayList<>();

        String select_query = "select * from " + TABLE_CONTACT;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {

            do {

                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                String note = cursor.getString(cursor.getColumnIndex(KEY_NOTE));
                byte[] image = cursor.getBlob(cursor.getColumnIndex(KEY_IMG));

                Letter letter = new Letter(id, note, image);

                letters.add(letter);

            } while (cursor.moveToNext());

        }

        return letters;
    }

    public Letter getLetterById(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        String select_query = "select * from " + TABLE_CONTACT + "where id=" + id;

        Cursor cursor = db.rawQuery(select_query, null);

        Letter letter = null;

        if (cursor.moveToFirst()) {

            int id_letter = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            String note = cursor.getString(cursor.getColumnIndex(KEY_NOTE));
            byte[] image = cursor.getBlob(cursor.getColumnIndex(KEY_IMG));


            letter = new Letter(id, note, image);

        }
        return letter;
    }

    public Letter getLetterById2(int id) {

        Letter letter = null;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACT, new String[]{"id", "note", "image"}, "id=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor.moveToFirst()) {

            int id_contact = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            String note = cursor.getString(cursor.getColumnIndex(KEY_NOTE));
            byte[] image = cursor.getBlob(cursor.getColumnIndex(KEY_IMG));

            letter = new Letter(id, note, image);

        }

        return letter;

    }
//UPDATE LETTER DETAILS
    public void updateLetter(Letter letter) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOTE, letter.getNote());
        values.put(KEY_IMG, letter.getImage());

        db.update(TABLE_CONTACT, values, "id=?", new String[]{String.valueOf(letter.getId())});

    }

//DELETE LETTER DETAILS
    public void deletLetter(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_CONTACT, "id=?", new String[]{String.valueOf(id)});

    }


}
