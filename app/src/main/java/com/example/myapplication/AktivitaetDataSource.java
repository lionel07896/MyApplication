package com.example.myapplication;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class AktivitaetDataSource {

    private static final String LOG_TAG = AktivitaetDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private AktivitaetDbHelper dbHelper;


    private String[] columns = {
            AktivitaetDbHelper.COLUMN_ID,
            AktivitaetDbHelper.COLUMN_Name,
            AktivitaetDbHelper.COLUMN_Ort,
            AktivitaetDbHelper.COLUMN_Zeit
    };

    public AktivitaetDataSource(Context context) {
        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den dbHelper.");
        dbHelper = new AktivitaetDbHelper(context);
    }
    public void open() {
        Log.d(LOG_TAG, "Eine Referenz auf die Datenbank wird jetzt angefragt.");
        database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + database.getPath());
    }

    public void close() {
        dbHelper.close();
        Log.d(LOG_TAG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
    }
    public Aktivitaet createAktivitaet(String name, String ort, String zeit)
    {

        ContentValues values = new ContentValues();
        values.put(AktivitaetDbHelper.COLUMN_Name, name);
        values.put(AktivitaetDbHelper.COLUMN_Ort, ort);
        values.put(AktivitaetDbHelper.COLUMN_Zeit, zeit);


        long insertId = database.insert(AktivitaetDbHelper.TABLE_SHOPPING_LIST, null, values);

        Cursor cursor = database.query(AktivitaetDbHelper.TABLE_SHOPPING_LIST,
                columns, AktivitaetDbHelper.COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Aktivitaet aktivitaet = cursorToAktivitaetParcel(cursor);
        cursor.close();

        return aktivitaet;
    }
    public void deleteAktivitaet(Aktivitaet aktivitaet) {
        String id = aktivitaet.getId(); //hier "long"

        database.delete(AktivitaetDbHelper.TABLE_SHOPPING_LIST,
                AktivitaetDbHelper.COLUMN_ID + "=" + id,
                null);
        //Toast.makeText(----, "Aktivität erflogreich gelöscht",
          //      Toast.LENGTH_SHORT).show();

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Name: " + aktivitaet.getName().toString());

    }


    private aktParcel cursorToAktivitaetParcel(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(AktivitaetDbHelper.COLUMN_ID);
        int idName = cursor.getColumnIndex(AktivitaetDbHelper.COLUMN_Name);
        int idOrt = cursor.getColumnIndex(AktivitaetDbHelper.COLUMN_Ort);
        int idZeit = cursor.getColumnIndex(AktivitaetDbHelper.COLUMN_Zeit);


        String name = cursor.getString(idName);
        String ort = cursor.getString(idOrt);
        String zeit = cursor.getString(idZeit);
        long id = cursor.getLong(idIndex);

        aktParcel aktivitaet = new aktParcel(Long.toString(id), name, ort, zeit);

        return aktivitaet;
    }


    public ArrayList<aktParcel> getAllAktivitaet() {
        ArrayList<aktParcel> aktivitaetList = new ArrayList<>();

        Cursor cursor = database.query(AktivitaetDbHelper.TABLE_SHOPPING_LIST,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        aktParcel aktivitaet;

        while(!cursor.isAfterLast()) {
            aktivitaet = cursorToAktivitaetParcel(cursor);
            aktivitaetList.add(aktivitaet);
            Log.d(LOG_TAG, "ID: " + aktivitaet.getId() + ", Name: " + aktivitaet.getName() + ", Ort:" + aktivitaet.getOrt() + ", Zeit: " + aktivitaet.getZeit()) ;
            cursor.moveToNext();
        }

        cursor.close();

        return aktivitaetList;
    }

}
