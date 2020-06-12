package com.wordpress.helpmevishal.mycontacts;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SearchContact extends AppCompatActivity {
    Spinner filter, select;
    TextView showContact;
    String filtered, selected;
    int positionFilter;
    Context con = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_contact);

        filter = (Spinner) findViewById(R.id.search_filter);
        select = (Spinner) findViewById(R.id.search_select);
        showContact = (TextView) findViewById(R.id.searched_content);

        final String attributesValues;
        String[] attributes = new String[]{"SERIAL","NAME","PHONE","EMAIL"};
        DataBaseHelper.searchFilter(this,filter,attributes);

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
                StringBuffer buffer = new StringBuffer();
                cr.moveToFirst();
                    buffer.append("Serial No. "+cr.getString(0)+"\n");
                    buffer.append("Name - "+cr.getString(1)+"\n");
                    buffer.append("Phone - "+cr.getString(2)+"\n");
                    buffer.append("Email Id - "+cr.getString(3));
                    showContact.setText(buffer.toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
    }
}
