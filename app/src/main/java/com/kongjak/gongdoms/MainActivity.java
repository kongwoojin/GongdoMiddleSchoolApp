package com.kongjak.gongdoms;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://gongdo.ms.kr"));
                startActivity(myIntent);
            }
        });

    }
    public void notice(View v) {

        Intent myintent = new Intent(this, NoticeActivity.class);
        startActivity(myintent);
    }

    public void meal(View v) {

        Intent myintent = new Intent(this, CafeActivity.class);
        startActivity(myintent);
    }

    public void intro(View v) {

        Intent myintent = new Intent(this, SchoolActivity.class);
        startActivity(myintent);
    }

    public void rule(View v) {

        Snackbar.make(v, "추가 준비중...", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }
    public void map(View v) {

        Snackbar.make(v, "추가 준비중...", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_info) {
            Intent myintent = new Intent(this, info.class);
            startActivity(myintent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}