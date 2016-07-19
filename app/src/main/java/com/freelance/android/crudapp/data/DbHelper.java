package com.freelance.android.crudapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 07/18/2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final int Database_Version = 1;
    public static final String Database_Name = "crud.db";

    public static final String Database_Table = "contacts";
    public static final String c_id = "contactId";
    public static final String c_firstName = "FirstName";
    public static final String c_lastName = "LastName";
    public static final String c_phoneNumber = "PhoneNumber";
    public static final String c_emailAddress = "EmailAddress";
    public static final String c_homeAddress = "HomeAddress";

    public DbHelper(Context context) {
        super(context, Database_Name, null, Database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String query = "CREATE TABLE " + Database_Table + " ( " +
                c_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                c_firstName + " TEXT UNIQUE NOT NULL, " +
                c_lastName + " TEXT NOT NULL, " +
                c_phoneNumber + " TEXT NOT NULL, " +
                c_emailAddress + " TEXT NOT NULL, " +
                c_homeAddress + " TEXT NOT NULL);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table If Exists " + Database_Table);
        onCreate(db);
    }

    public void insertContactInfo(HashMap<String, String> queryValues) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cV = new ContentValues();
        cV.put("FirstName", queryValues.get(c_firstName));
        cV.put("LastName", queryValues.get(c_lastName));
        cV.put("PhoneNumber", queryValues.get(c_phoneNumber));
        cV.put("EmailAddress", queryValues.get(c_emailAddress));
        cV.put("HomeAddress", queryValues.get(c_homeAddress));

        db.insert(Database_Table, null, cV);
        db.close();
    }

    public int updateContactInfo(HashMap<String, String> queryValues) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cV = new ContentValues();
        cV.put("FirstName", queryValues.get(c_firstName));
        cV.put("LastName", queryValues.get(c_lastName));
        cV.put("PhoneNumber", queryValues.get(c_phoneNumber));
        cV.put("EmailAddress", queryValues.get(c_emailAddress));
        cV.put("HomeAddress", queryValues.get(c_homeAddress));

        return db.update(Database_Table, cV, c_id + " =?", new String[]{queryValues.get(c_id)});
    }

    public void deleteContactInfo(String id) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Delete From " + Database_Table + " Where " + c_id + " = '" + id + "';");
    }

    public ArrayList<HashMap<String, String>> getAllContactInfo() {
        ArrayList<HashMap<String, String>> cArrayList = new ArrayList<HashMap<String, String>>();

        String selectQuery = "Select * From " + Database_Table + " Order By " + c_lastName + ";";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {

            do {

                HashMap<String, String> contactMap = new HashMap<String, String>();

                contactMap.put("contactId", c.getString(0));
                contactMap.put("FirstName", c.getString(1));
                contactMap.put("LastName", c.getString(2));
                contactMap.put("PhoneNumber", c.getString(3));
                contactMap.put("EmailAddress", c.getString(4));
                contactMap.put("HomeAddress", c.getString(5));

                cArrayList.add(contactMap);
            } while (c.moveToNext());
        }

        return cArrayList;
    }

    public HashMap<String, String> getEachContactInfo(String Id) {
        HashMap<String, String> contactMap = new HashMap<String, String>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "Select * from " + Database_Table + " Where " + c_id + " = '" + Id + "';";

        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {

            do {

                contactMap.put("contactId", c.getString(0));
                contactMap.put("FirstName", c.getString(1));
                contactMap.put("LastName", c.getString(2));
                contactMap.put("PhoneNumber", c.getString(3));
                contactMap.put("EmailAddress", c.getString(4));
                contactMap.put("HomeAddress", c.getString(5));

            } while (c.moveToNext());
        }

        return contactMap;
    }
}

