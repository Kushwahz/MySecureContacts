package com.wordpress.helpmevishal.mycontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Vishal on 27-06-2016.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyContacts.db";
    public static final String TABLE_NAME = "CONTACTS";
    public static final String COL_1 = "SERIAL";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "PHONE";
    public static final String COL_4 = "EMAIL";
    String[] filterAttribute = {"SERIAL", "NAME", "PHONE", "EMAIL"};

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table "+ TABLE_NAME+ " ( SERIAL INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, PHONE TEXT, EMAIL TEXT ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF INSERTED "+TABLE_NAME);
        onCreate(db);
    }

    public void insertContact(String name, String phone, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2,name);
        cv.put(COL_3,phone);
        cv.put(COL_4,email);
        db.insert(TABLE_NAME, null, cv);
    }

    public Cursor getContact(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cr = db.rawQuery(" select * from "+TABLE_NAME,null);
        return cr;
    }

    public boolean delete(String serial) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COL_1 + "=" + serial, null) > 0;
    }

    public int getProfilesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    public String[] getFilterContact(Context con, int filtered,Spinner spinner){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cr;
        int contacts = getProfilesCount();
        String[] attribute = new String[contacts];
        int index = 0;

        switch (filtered){
            case 0 : cr = db.rawQuery("SELECT "+COL_1+" FROM "+ TABLE_NAME ,null);
                while(cr.moveToNext()){
                    attribute[index] = cr.getString(0);
                    index++;
                }
                break;
            case 1 : cr = db.rawQuery("SELECT "+COL_2+" FROM "+ TABLE_NAME ,null);
                while(cr.moveToNext()){
                    attribute[index] = cr.getString(0);
                    index++;
                }
                break;
            case 2 : cr = db.rawQuery("SELECT "+COL_3+" FROM "+ TABLE_NAME,null);
                while(cr.moveToNext()){
                    attribute[index] = cr.getString(0);
                    index++;
                }
                break;
            case 3 : cr = db.rawQuery("SELECT "+COL_4+" FROM "+ TABLE_NAME,null);
                while(cr.moveToNext()){
                    attribute[index] = cr.getString(0);
                    index++;
                }
                break;
        }
        return attribute;
    }

    public void editContact(String serial,String name, String phone, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1,serial);
        cv.put(COL_2,name);
        cv.put(COL_3,phone);
        cv.put(COL_4,email);
        db.update(TABLE_NAME,cv,"SERIAL=?",new String[]{serial});
    }

    public static void searchFilter(Context con,Spinner spinner, String[] arr){
        ArrayAdapter<CharSequence> spinnerArrayAdapter = new ArrayAdapter(con,
                android.R.layout.simple_spinner_item,arr);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
        spinnerArrayAdapter.notifyDataSetChanged();
    }

    public Cursor getContact(String select,String filter){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cr = db.rawQuery(" select * from "+TABLE_NAME+" WHERE "+select+"=?",new String[]{filter});
        return cr;
    }
}
