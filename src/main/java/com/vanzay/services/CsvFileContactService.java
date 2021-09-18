package com.vanzay.services;

import com.vanzay.models.Contact;
import com.vanzay.utils.ListUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CsvFileContactService implements ContactsService {
    private final File file = new File("Contacts.csv");
    private ArrayList<Contact> contactsList;

    public CsvFileContactService(ArrayList<Contact> contactsList) {
        this.contactsList = contactsList;
    }

    @Override
    public ArrayList<Contact> getSearchContact(String enteredStr) {
        openFile();
        return (ArrayList<Contact>) ListUtils.filter(contactsList,
                contact -> contact.getName().contains(enteredStr));
    }

    @Override
    public void remove(int index) {
        openFile();
        contactsList.remove(index);
        writeFile();
    }

    @Override
    public ArrayList<Contact> getAll() {
        openFile();
        return contactsList;
    }

    @Override
    public void add(Contact contact) {
        openFile();
        contactsList.add(contact);
        writeFile();
    }

    private void openFile() {
        if(file.exists()) {
            contactsList = null;
            try (FileReader is = new FileReader(file)) {
                BufferedReader bufferedReader = new BufferedReader(is);
                contactsList = (ArrayList<Contact>) bufferedReader.lines()
                        .map(l -> l.split(","))
                        .map(a -> new Contact(a[0], a[1]))
                        .collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeFile() {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter("Contacts.csv"))) {
            System.out.println(contactsList);
            for (Contact contact : contactsList) {
                bufferedWriter.write(contact.toString()+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


