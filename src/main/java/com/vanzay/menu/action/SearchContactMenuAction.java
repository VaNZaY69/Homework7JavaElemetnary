package com.vanzay.menu.action;

import com.vanzay.menu.MenuAction;
import com.vanzay.services.ContactsService;

import java.util.Scanner;

public class SearchContactMenuAction implements MenuAction {
    private final ContactsService service;
    Scanner scanner;

    public SearchContactMenuAction(Scanner scanner, ContactsService service) {
        this.scanner = scanner;
        this.service = service;
    }

    @Override
    public void doAction() {
        System.out.println("Enter the name or part of the contact you are looking for:");
        String enteredStr = scanner.nextLine();
        for (int i = 0; i < service.getSearchContact(enteredStr).size(); i++) {
            System.out.println((i + 1) + ") " + service.getSearchContact(enteredStr).get(i));
        }
    }

    @Override
    public String getName() {
        return "Search for contacts";
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
