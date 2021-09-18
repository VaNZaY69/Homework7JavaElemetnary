package com.vanzay.services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.vanzay.models.Contact;
import com.vanzay.utils.ListUtils;

import java.io.*;
import java.util.ArrayList;

public class XmlContactService implements ContactsService {
    private final File file = new File("Contacts.xml");
    private final ArrayList<Contact> contactsList;

    public XmlContactService(ArrayList<Contact> contactsList) {
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

    private void openFile(){

            ObjectMapper objectMapper = new XmlMapper();
            try {
                    contactsList.add(objectMapper.readValue(new FileReader(file), Contact.class));
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    private void writeFile() {
        ObjectMapper objectMapper = new XmlMapper();
            try {
                for (Contact contact : contactsList) {
                    objectMapper
                            .writerWithDefaultPrettyPrinter()
                            .writeValue(new FileWriter(file), contact);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
