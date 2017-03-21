package com.tutorials.hp.datafragment_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView nameTxt,yearTxt;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

		//REFERENCE VIEWS
        nameTxt= (TextView) findViewById(R.id.nameTxt);
        yearTxt= (TextView) findViewById(R.id.yearTxt);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              openFragment();
            }
        });


    }

    
/*
WHEN ACTIVITY RESUMES
*/
    @Override
    protected void onResume() {
        super.onResume();
        
		//DETERMINE WHO STARTED THIS ACTIVITY
		final String sender=this.getIntent().getExtras().getString("SENDER_KEY");
		
		//IF ITS THE FRAGMENT THEN RECEIVE DATA
        if(sender != null)
        {
            this.receiveData();
            Toast.makeText(this, "Received", Toast.LENGTH_SHORT).show();

        }
    }

    /*
        OPEN FRAGMENT
         */
    private void openFragment()
    {
        //PASS OVER THE BUNDLE TO OUR FRAGMENT
        MyFragment myFragment = new MyFragment();
        //THEN NOW SHOW OUR FRAGMENT
        getSupportFragmentManager().beginTransaction().replace(R.id.container,myFragment).commit();
    }

    /*
    RECEIVE DATA FROM FRAGMENT
     */
    private void receiveData()
    {
        //RECEIVE DATA VIA INTENT
        Intent i = getIntent();
        String name = i.getStringExtra("NAME_KEY");
        int year = i.getIntExtra("YEAR_KEY",0);

        //SET DATA TO TEXTVIEWS
        nameTxt.setText(name);
        yearTxt.setText(String.valueOf(year));
    }


}
