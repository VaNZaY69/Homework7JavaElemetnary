package com.vanzay;

import com.vanzay.menu.Menu;
import com.vanzay.menu.MenuAction;
import com.vanzay.menu.action.AddContactMenuAction;
import com.vanzay.menu.action.ReadAllContactsMenuAction;
import com.vanzay.menu.action.RemoveContactMenuAction;
import com.vanzay.menu.action.SearchContactMenuAction;
import com.vanzay.models.Contact;
import com.vanzay.services.ContactsService;
import com.vanzay.services.InMemoryContactsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<MenuAction> actions = new ArrayList<>();

        ArrayList<Contact> contactsList = new ArrayList<>();

        ContactsService service = new InMemoryContactsService(contactsList);

        Menu menu = new Menu(scanner, actions);
        menu.addAction(new AddContactMenuAction(scanner, service));
        menu.addAction(new ReadAllContactsMenuAction(scanner, service));
        menu.addAction(new RemoveContactMenuAction(scanner, service));
        menu.addAction(new SearchContactMenuAction(scanner, service));

        menu.run();
    }
}
