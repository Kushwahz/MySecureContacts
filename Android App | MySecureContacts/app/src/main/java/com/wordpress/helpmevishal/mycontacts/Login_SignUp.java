package com.wordpress.helpmevishal.mycontacts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login_SignUp extends Activity {
    Button signUp;
    Boolean loggedIn, signedIn = false, loggedOut;
    SharedPreferences checking;
    TextView titleText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__sign_up);
        titleText = (TextView) findViewById(R.id.textView);
        final Typeface face = Typeface.createFromAsset(getAssets(), "SCRIPTBL.TTF");
        titleText.setTypeface(face);

        checking = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
        loggedOut = checking.getBoolean("logOut", false);
        if (loggedOut){
            SharedPreferences.Editor editor = checking.edit();
            editor.putBoolean("logOut",false);
            editor.commit();
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();
        }else {
            loggedIn = checking.getBoolean("keepMeLoggedIn",false);
            signedIn = checking.getBoolean("signedIn",false);

            if(signedIn){
                if(loggedIn){
                    startActivity(new Intent(getApplicationContext(),ViewContacts.class));
                    finish();
                }
                else{
                    startActivity(new Intent(getApplicationContext(),Login.class));
                    finish();
                }
            }
            signUp = (Button) findViewById(R.id.btn_signUp);
            signUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),SignUp.class));
                    finish();
                }
            });
        }
    }
}
