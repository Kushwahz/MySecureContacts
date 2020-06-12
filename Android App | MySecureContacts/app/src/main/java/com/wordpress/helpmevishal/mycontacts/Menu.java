package com.wordpress.helpmevishal.mycontacts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Menu extends Activity {
    Button view, add, search, edit, delete, logOut;
    public static Boolean clicked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);



        clicked = true;
        view = (Button) findViewById(R.id.btn_view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ViewContacts.class));
            }
        });

        add = (Button) findViewById(R.id.btn_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddContacts.class));
            }
        });

        search = (Button) findViewById(R.id.btn_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SearchContact.class));
            }
        });
        edit = (Button) findViewById(R.id.btn_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),EditContacts.class));
            }
        });

        delete = (Button) findViewById(R.id.delete_btn);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DeleteContacts.class));
            }
        });

        logOut = (Button) findViewById(R.id.btn_logOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences logout = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = logout.edit();
                editor.putBoolean("logOut",true);
                editor.commit();
                finishAffinity();
                Toast.makeText(getApplicationContext(),"Logged Out",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_logout){
            SharedPreferences logout = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = logout.edit();
            editor.putBoolean("logOut",true);
            editor.commit();
            finishAffinity();
            Toast.makeText(getApplicationContext(),"Logged Out",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
