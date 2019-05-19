package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


public class MainActivity extends AppCompatActivity
        {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;


    public int counter =0;

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    private AktivitaetDataSource dataSource;

    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt("counter");
        }
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

       // NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);

        Button login_btn = findViewById(R.id.login_button);
        Button add_akt_btn = findViewById(R.id.add_akt_button);
        Button aktivitaeten_btn = findViewById(R.id.akt_button);
        Button timer_btn = findViewById(R.id. timer_button );

      //  Button menu_timer_btn = findViewById(R.id.nav_timer);
       // Button menu_add_btn = findViewById(R.id.nav_add);
        //Button menu_list_btn = findViewById(R.id.nav_list);
        final ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout, R.string.Open,R.string.Close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        initNavigationDrawer();



        login_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switchToLogin();
            }
        });
        timer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToTimer();
            }
        });
        add_akt_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switchToadd_akt();
            }
        });
        aktivitaeten_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switchToaktivitaeten();
            }
        });


        //Aktivitaet testMemo = new Aktivitaet(15, "FFM", "Treffen" );
        //Log.d(LOG_TAG, "Inhalt der Testmemo: " + testMemo.toString());

        /**
        dataSource = new AktivitaetDataSource(this);

        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
        dataSource.open();

        Aktivitaet aktivitaet = dataSource.createAktivitaet("Test", "FFM", "15Uhr");
        Log.d(LOG_TAG, "Es wurde der folgende Eintrag in die Datenbank geschrieben:");
        Log.d(LOG_TAG, "ID: " + aktivitaet.getId() + ", Name: " + aktivitaet.getName());

        Log.d(LOG_TAG, "Folgende Einträge sind in der Datenbank vorhanden:");
       // showAllListEntries();


        Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
        dataSource.close();


     */} /**
        private void showAllListEntries ()
    {
        List<Aktivitaet> aktivitaetList = dataSource.getAllAktivitaet();

        ArrayAdapter<Aktivitaet> AktivitaetArrayAdapter = new ArrayAdapter<> (
                this,
                android.R.layout.simple_list_item_multiple_choice,
                aktivitaetList);

        ListView aktivitaetListView = (ListView) findViewById(R.id.listview_shopping_memos);
        aktivitaetListView.setAdapter(AktivitaetArrayAdapter);
    }
 **/
    private void switchToLogin(){
        Intent a = new Intent(this,loginActivity.class);
        startActivity(a);
    }
    private void switchToTimer() {
        Intent a = new Intent(this,timerActivity.class);
        startActivity(a);
    }
    private void switchToadd_akt(){
        Intent a = new Intent(this,add_aktActivity.class);
        startActivity(a);
    }
    private void switchToaktivitaeten(){
        Intent a = new Intent(this,aktivitaetenActivity.class);
        startActivity(a);
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

   /** @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;

        }
        return super.onOptionsItemSelected(item);
    }
    **/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, SettingsActivity2.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initNavigationDrawer() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id) {
                    case R.id.nav_timer:
                        switchToTimer();
                        break;
                    case R.id.nav_list:
                        switchToaktivitaeten();
                        break;
                    case R.id.nav_add:
                        switchToadd_akt();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }
}
