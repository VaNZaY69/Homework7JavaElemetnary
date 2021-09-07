package com.vanzay.menu.action;

import com.vanzay.models.Contact;
import com.vanzay.services.ContactsService;
import com.vanzay.menu.MenuAction;

import java.util.Scanner;

public class AddContactMenuAction implements MenuAction {
    private final Scanner scanner;
    private final ContactsService service;

    public AddContactMenuAction(Scanner scanner, ContactsService service) {
        this.scanner = scanner;
        this.service = service;
    }

    @Override
    public void doAction() {
        System.out.print("Enter contact name: ");
        String nameContact = scanner.nextLine();
        System.out.print("Enter the contact's phone number: ");
        String phoneContact = scanner.nextLine();
        service.add(new Contact(nameContact, phoneContact));
        System.out.println("The contact has been added.");
    }

    @Override
    public String getName() {
        return "Add contact";
    }

    @Override
    public boolean closedAfter() {
        System.out.print("To return to the menu, enter - 1, exit - 2: ");
        int choice = scanner.nextInt();
        if (choice < 1 || choice > 2) {
            System.out.println("Incorrect input");
        } else return choice == 2;
        return false;
    }
}