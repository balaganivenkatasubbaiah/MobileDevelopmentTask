package com.example.mobiledevelopmenttask;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Subbu on 16-07-2017.
 */

public class MyDataBase extends SQLiteOpenHelper
{
    Context context;
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "registerManagerr.db";

    // Contacts table name
    private static final String TABLE_CONTACTS = "employeedetails";
    private static final String KEY_NAME= "ename";
    private static final String KEY_PHONE= "ephone";
    private static final String KEY_PASSWORD= "epassword";

    public MyDataBase(MainActivity mainActivity)
    {
        super(mainActivity, DATABASE_NAME, null, DATABASE_VERSION);
        context=mainActivity;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_NAME + " TEXT," + KEY_PHONE + " TEXT," +  KEY_PASSWORD + " TEXT"+")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertRecord(String empname, String empcellno, String emppass)
    {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, empname);
        values.put(KEY_PHONE, empcellno);
        values.put(KEY_PASSWORD, emppass);
        db.insert(TABLE_CONTACTS, null, values);
        Log.e("msg", "record inserted succusfully: id:"+" ename:"+empname+" ephone: "+empcellno+" emppassword: "+emppass);

    }
}
