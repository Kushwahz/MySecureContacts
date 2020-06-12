package com.wordpress.helpmevishal.mycontacts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewContacts extends AppCompatActivity {
    TextView showContact;
    private List<Contacts> contactList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ContactAdapter mAdapter;
    public static DataBaseHelper Contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts);
        Contact = new DataBaseHelper(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new ContactAdapter(contactList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareContacts();
    }

    private void prepareContacts() {
        Cursor cr = Contact.getContact();

        while (cr.moveToNext()) {
            Contacts contact = new Contacts(cr.getString(1), cr.getString(2), cr.getString(3));
            contactList.add(contact);
        }
            mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_new_contact) {
            startActivity(new Intent(getApplicationContext(), AddContacts.class));
        }

        if(id==R.id.action_search_contact){
            startActivity(new Intent(getApplicationContext(), SearchContact.class));
        }

        if(id==R.id.action_edit_contact){
            startActivity(new Intent(getApplicationContext(), EditContacts.class));
        }

        if(id==R.id.action_delete_contact){
            startActivity(new Intent(getApplicationContext(), DeleteContacts.class));
        }

        if(id==R.id.action_logout){
            SharedPreferences logout = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = logout.edit();
            editor.putBoolean("logOut",true);
            editor.commit();
            finishAffinity();
            Toast.makeText(getApplicationContext(),"Secured Exit!",Toast.LENGTH_LONG).show();
        }

        if(id==R.id.action_close){
            finishAffinity();
            Toast.makeText(this, "Unsecured Exit!", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

}
