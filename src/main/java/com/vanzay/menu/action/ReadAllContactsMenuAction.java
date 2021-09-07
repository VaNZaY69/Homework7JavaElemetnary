package com.vanzay.menu.action;

import com.vanzay.menu.MenuAction;
import com.vanzay.services.ContactsService;

import java.util.Scanner;

public class ReadAllContactsMenuAction implements MenuAction {
    private final ContactsService service;
    Scanner scanner;

    public ReadAllContactsMenuAction(Scanner scanner, ContactsService service) {
        this.scanner = scanner;
        this.service = service;
    }

    @Override
    public void doAction() {
        for (int i = 0; i < service.getAll().size(); i++) {
            System.out.println((i + 1) + ") " + service.getAll().get(i));
        }
    }

    @Override
    public String getName() {
        return "Read all contacts";
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
