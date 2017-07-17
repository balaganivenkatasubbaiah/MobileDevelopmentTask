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
    public static String ename;
    public static String ephone;
    public static String epassword;
    public static String tablename;

    static
    {
        tablename = "employeedetails";
        ename = "ename";
        ephone = "ephone";
        epassword="epassword";
    }

    public MyDataBase(MainActivity mainActivity, String mydb)
    {
        super(mainActivity, mydb, null, 1);
        context=mainActivity;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + tablename + "(" + ename + " TEXT," + ephone + " TEXT," +  epassword + " TEXT"+")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertRecord(String empname, String empcellno, String emppass)
    {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ename, empname);
        values.put(ephone, empcellno);
        values.put(epassword, emppass);
        db.insert(tablename, null, values);
        Log.e("msg", "record inserted succusfully: id:"+" ename:"+empname+" ephone: "+empcellno+" emppassword: "+emppass);

    }
}
