package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.view.inputmethod.InputMethodManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class add_aktActivity extends AppCompatActivity {

    public int counter =0;

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    private AktivitaetDataSource dataSource;

    private static Button add_button;
    private static EditText Name;
    private static EditText Place;
    private static EditText Time;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
        {
            counter = savedInstanceState.getInt("counter");
        }
        setContentView(R.layout.activity_add_akt);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        dataSource = new AktivitaetDataSource(this);
        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
        dataSource.open();


        addbutton();

    }
    @Override
    protected void onSaveInstanceState (Bundle outState) {
        outState.putInt("counter", counter);
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        counter = savedInstanceState.getInt("counter");
    }

    public void addbutton ()
    {

        add_button = (Button)findViewById(R.id.create_button);
        Name = (EditText)findViewById(R.id.editText_add_name);
        Place = (EditText)findViewById(R.id.editText_add_place);
        Time = (EditText)findViewById(R.id.editText_add_time);

        add_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // dataSource = new AktivitaetDataSource(aktivitaetenActivity);
                        // Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
                        //dataSource.open();



                        Aktivitaet aktivitaet = dataSource.createAktivitaet(Name.getText().toString(), Place.getText().toString(), Time.getText().toString());
                        Toast.makeText(add_aktActivity.this, "Aktivität erflogreich erstellt",
                                Toast.LENGTH_SHORT).show();
                        Log.d(LOG_TAG, "Es wurde der folgende Eintrag in die Datenbank geschrieben:");
                        Log.d(LOG_TAG, "ID: " + aktivitaet.getId() + ", Name: " + aktivitaet.getName());

                        Log.d(LOG_TAG, "Folgende Einträge sind in der Datenbank vorhanden:");
                        showAllListEntries();
                        Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
                        dataSource.close();
                        switchToaktivitaeten();


                    }
                }


                );

    }
    private void showAllListEntries ()
    {
        ArrayList<aktParcel> aktivitaetList = dataSource.getAllAktivitaet();


    }
    private void switchToaktivitaeten(){
        Intent a = new Intent(this,aktivitaetenActivity.class);
        startActivity(a);
    }

    protected void onStart(Bundle savedInstanceState) {
        super.onStart();
        Log.d("started",getClass().getName());
    }

    protected void onPause(Bundle savedInstanceState) {
        super.onPause();
        Log.d("paused", getClass().getName());
    }

    protected void onDestroy(Bundle savedInstanceState) {
        super.onDestroy();
        Log.d("destroyed", getClass().getName());
    }

    protected void onRestart(Bundle savedInstanceState) {
        super.onRestart();
        Log.d("restarted", getClass().getName());
    }

    protected void onStop(Bundle savedInstanceState) {
        super.onStop();
        Log.d("stop", getClass().getName());
    }


}
