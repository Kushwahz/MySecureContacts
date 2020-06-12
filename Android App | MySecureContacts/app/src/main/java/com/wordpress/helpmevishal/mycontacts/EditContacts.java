package com.wordpress.helpmevishal.mycontacts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditContacts extends AppCompatActivity {
    EditText name, phone, email;
    Button save;
    Spinner filter, select;
    String filtered, selected;
    int positionFilter;
    Context con = this;
    String Serial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contacts);

        filter = (Spinner) findViewById(R.id.edit_filter);
        String[] attributes = new String[]{"SERIAL","NAME","PHONE","EMAIL"};
        DataBaseHelper.searchFilter(this,filter,attributes);
        select = (Spinner) findViewById(R.id.edit_select);
        name = (EditText) findViewById(R.id.edit_name);
        phone = (EditText) findViewById(R.id.edit_phone);
        email = (EditText) findViewById(R.id.edit_email);
        save = (Button) findViewById(R.id.btn_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DataBaseHelper(con).editContact(Serial, name.getText().toString(),
                        phone.getText().toString(),email.getText().toString());
                Toast.makeText(con, "Contact Updated!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),ViewContacts.class));
                finishAffinity();
            }
        });

        filter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionFilter = parent.getSelectedItemPosition();
                filtered = parent.getSelectedItem().toString();
                new DataBaseHelper(getApplication()).searchFilter(con,
                        select,ViewContacts.Contact.getFilterContact(getApplicationContext(),
                                positionFilter,select));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected = parent.getSelectedItem().toString();
                Cursor cr = new DataBaseHelper(getApplicationContext()).getContact(filtered,selected);
                cr.moveToFirst();
                Serial = cr.getString(0);
                name.setText(cr.getString(1));
                phone.setText(cr.getString(2));
                email.setText(cr.getString(3));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
    }
}
