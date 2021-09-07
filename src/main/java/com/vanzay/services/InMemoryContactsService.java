package com.vanzay.services;

import com.vanzay.models.Contact;
import com.vanzay.utils.ListUtils;

import java.util.ArrayList;

public class InMemoryContactsService implements ContactsService {
    private final ArrayList<Contact> contactsList;

    public InMemoryContactsService(ArrayList<Contact> contactsList) {
        this.contactsList = contactsList;
    }

    @Override
    public ArrayList<Contact> getSearchContact(String enteredStr) {
        return (ArrayList<Contact>) ListUtils.filter(contactsList,
                contact -> contact.getName().contains(enteredStr));
    }

    @Override
    public ArrayList<Contact> getAll() {
        return contactsList;
    }

    @Override
    public void remove(int index) {
        contactsList.remove(index);
    }

    @Override
    public void add(Contact contact) {
        contactsList.add(contact);
    }
}
