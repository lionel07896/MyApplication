package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AktivitaetDbHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = AktivitaetDbHelper.class.getSimpleName();

    public static final String DB_NAME = "aktivitaet_list.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_SHOPPING_LIST = "aktivitaet_list";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_Name = "name";
    public static final String COLUMN_Ort = "ort";
    public static final String COLUMN_Zeit = "zeit";



    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_SHOPPING_LIST +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_Name + " TEXT NOT NULL, " +
                    COLUMN_Ort + " TEXT NOT NULL," +
                    COLUMN_Zeit + " TEXT NOT NULL);";


    public AktivitaetDbHelper(Context context) {
         // super(context, "AktivitaetDB", null, 1);
        super(context, "/sdcard/"+DB_NAME, null, DB_VERSION);
        SQLiteDatabase.openOrCreateDatabase("/sdcard/" + DB_NAME, null);
        Log.d(LOG_TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE + " angelegt.");
            db.execSQL(SQL_CREATE);
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
