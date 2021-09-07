package com.vanzay.menu.action;


import com.vanzay.menu.MenuAction;
import com.vanzay.services.ContactsService;

import java.util.Scanner;

public class RemoveContactMenuAction implements MenuAction {
    private final Scanner scanner;
    private final ContactsService service;

    public RemoveContactMenuAction(Scanner scanner, ContactsService service) {
        this.scanner = scanner;
        this.service = service;
    }

    @Override
    public void doAction() {
        System.out.println("Enter the number of the contact you want to delete: ");
        int number = scanner.nextInt();
        service.remove(number - 1);
        System.out.println("Contact deleted.");
    }

    @Override
    public String getName() {
        return "Remove contact";
    }

    @Override
    public boolean closedAfter() {
        System.out.print("To return to the menu, enter - 1, exit - 2: ");
        int choice = scanner.nextInt();
        if (choice < 1 || choice > 2) {
            System.out.println("Incorrect input");
        }
        return choice == 2;
    }
}
