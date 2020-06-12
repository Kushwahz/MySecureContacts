package com.wordpress.helpmevishal.mycontacts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Kushwahz on 05-11-2016.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    private List<Contacts> contactList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, phone, email;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            phone = (TextView) view.findViewById(R.id.phone);
            email = (TextView) view.findViewById(R.id.email_id);
        }
    }


    public ContactAdapter(List<Contacts> contactList) {
        this.contactList = contactList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contacts_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Contacts contacts = contactList.get(position);
        holder.name.setText(contacts.getName());
        holder.phone.setText(contacts.getPhone());
        holder.email.setText(contacts.getEmail());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}

