package com.example.ishupreetsingh.smartenergy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText ip_addr,key_store,salt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        ip_addr =(EditText)findViewById(R.id.ip_addr);
        key_store = (EditText)findViewById(R.id.key_store);
        salt = (EditText)findViewById(R.id.salt);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void sendOn(View view) {
        String ip = ip_addr.getText().toString();
        int key = Integer.parseInt(key_store.getText().toString());
        int salt_val = Integer.parseInt(salt.getText().toString());
        int action = key+salt_val+1;
        Utils.threadGET ("http://"+ip+"/socket1On?action="+action+"&salt="+salt_val);

        System.out.println("On Message Sending");
        // Do something in response to button click
    }
    public void sendOff(View view) {
        String ip = ip_addr.getText().toString();
        int key = Integer.parseInt(key_store.getText().toString());
        int salt_val = Integer.parseInt(salt.getText().toString());
        int action = key+salt_val;
        Utils.threadGET("http://"+ip+"/socket1On?action="+action+"&salt="+salt_val);
        System.out.println("OFF Message Sending");
        // Do something in response to button click
    }
    public void setKey(View view) {
        String ip = ip_addr.getText().toString();
        int key = Integer.parseInt(key_store.getText().toString());
        Utils.threadGET("http://"+ip+"/setKey?key="+key);
        System.out.println("Key set Message Sending");
        // Do something in response to button click
    }
    public void getKey(View view) {
        System.out.println("Message Sending");
        String ip = ip_addr.getText().toString();
        Utils.threadGET("http://"+ip+"/getKey");
        key_store.setText(Utils.global_key);
        // Do something in response to button click
        System.out.println("Key get Message Received "+Utils.global_key);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
