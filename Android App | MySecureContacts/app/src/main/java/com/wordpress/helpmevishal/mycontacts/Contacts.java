package com.wordpress.helpmevishal.mycontacts;

/**
 * Created by Kushwahz on 05-11-2016.
 */

public class Contacts {
    String Name;
    String Phone;
    String Email;

    public Contacts() {
    }

    public Contacts(String name, String phone, String email) {
        Name = name;
        Phone = phone;
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
