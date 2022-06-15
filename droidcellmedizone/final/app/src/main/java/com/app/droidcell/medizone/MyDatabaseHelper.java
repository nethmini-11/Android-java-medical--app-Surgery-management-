package com.app.droidcell.medizone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "patient.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "patient_Surgery";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_Pid= "patient_pid";
    private static final String COLUMN_Name= "patient_name";
    private static final String COLUMN_con = "patient_con";
    private static final String COLUMN_typ= "patient_typ";
    private static final String COLUMN_phone = "patient_phone";
    private static final String COLUMN_trustee= "patient_trustee";
    private static final String  COLUMN_img= "image";


    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_Pid+ " TEXT, " +
                COLUMN_Name+ " TEXT, " +
                COLUMN_con+ " TEXT, " +
                COLUMN_typ + " TEXT, " +
                COLUMN_phone + " INTEGER,"+
                COLUMN_trustee + " TEXT);";

        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
//insert data ;;;; cv=ContentValues
    void addPatient(String pid,String fname, String dob, String address,int phone,String trustee){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_Pid, pid);
        cv.put(COLUMN_Name, fname);
        cv.put(COLUMN_con, dob);
        cv.put(COLUMN_typ, address);
        cv.put(COLUMN_phone, phone);
        cv.put(COLUMN_trustee, trustee);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null; //read all data row by row
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String pid, String fname, String dob, String address, String phone, String trustee){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_Pid, pid);
        cv.put(COLUMN_Name, fname);
        cv.put(COLUMN_con, dob);
        cv.put(COLUMN_typ, address);
        cv.put(COLUMN_phone, phone);
        cv.put(COLUMN_trustee, trustee);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}
