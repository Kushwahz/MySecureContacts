package com.wordpress.helpmevishal.mycontacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContacts extends AppCompatActivity {
    EditText name, phone, email;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);

        name = (EditText) findViewById(R.id.et_name);
        phone = (EditText) findViewById(R.id.et_phone);
        email = (EditText) findViewById(R.id.et_email);
        add = (Button) findViewById(R.id.btn_add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals("")||phone.getText().toString().equals("")||email.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Empty Field!",Toast.LENGTH_SHORT).show();
                }else
                {
                    ViewContacts.Contact.insertContact(name.getText().toString(),phone.getText().toString(),email.getText().toString());
                    name.setText("");
                    phone.setText("");
                    email.setText("");
                    Toast.makeText(getApplicationContext(),"New Contact!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), ViewContacts.class));
                    finishAffinity();
                }
            }
        });
    }
}
