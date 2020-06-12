package com.wordpress.helpmevishal.mycontacts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    EditText sUsername, sPassword;
    Button sSignUp;
    Boolean signedIn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);
        sUsername = (EditText) findViewById(R.id.et_s_username);
        sPassword = (EditText) findViewById(R.id.et_s_password);
        sSignUp = (Button) findViewById(R.id.btn_s_SignUp);
        sSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences saveUser = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = saveUser.edit();
                editor.putString("username",sUsername.getText().toString());
                editor.putString("password",sPassword.getText().toString());
                editor.putBoolean("signedIn",true);
                editor.commit();
                Toast.makeText(getApplicationContext(),"Welcome "+sUsername.getText().toString(),Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),ViewContacts.class));
                finish();
            }
        });
    }
}
