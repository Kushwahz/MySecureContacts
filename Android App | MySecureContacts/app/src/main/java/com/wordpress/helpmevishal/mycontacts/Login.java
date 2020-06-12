package com.wordpress.helpmevishal.mycontacts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText username, password;
    Button login;
    String Username, Password;
    CheckBox logged;
    Boolean loggedOut = false;
    SharedPreferences loginCredentials;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);
        logged = (CheckBox) findViewById(R.id.cb_loggedIn);

        loginCredentials = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
        login = (Button) findViewById(R.id.btn_Login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Username = loginCredentials.getString("username",null);
                Password = loginCredentials.getString("password",null);

                if(Username.equals(username.getText().toString())&& Password.equals(password.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Login Successful!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),ViewContacts.class));
                    finishAffinity();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Invalid Username or Password!",Toast.LENGTH_SHORT).show();
                    username.setText("");
                    password.setText("");
                }

                SharedPreferences.Editor editor = loginCredentials.edit();
                if (logged.isChecked()){
                    editor.putBoolean("keepMeLoggedIn", true);
                    editor.commit();
                }
                else {
                    editor.putBoolean("keepMeLoggedIn", false);
                    editor.commit();
                }
            }
        });
    }
}
