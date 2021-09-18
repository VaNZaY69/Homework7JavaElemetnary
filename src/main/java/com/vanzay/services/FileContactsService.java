package com.vanzay.services;

import com.vanzay.models.Contact;
import com.vanzay.utils.ListUtils;

import java.io.*;
import java.util.ArrayList;

public class FileContactsService implements ContactsService {
    private final File file = new File("Contacts.txt");
    private ArrayList<Contact> contactsList;

    public FileContactsService(ArrayList<Contact> contactsList) {
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
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                contactsList = (ArrayList<Contact>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeFile() {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(file, true))) {
            for (Contact contact : contactsList) {
                out.writeObject(contact);
            }
            out.flush();
        } catch (FileNotFoundException ex) {
            System.out.println("Error with specified file");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error with I/O processes");
            ex.printStackTrace();
        }
    }

}
