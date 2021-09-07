package com.vanzay.menu;

import java.util.List;
import java.util.Scanner;

public class Menu {
    public List<MenuAction> actions;
    private final Scanner scanner;

    public Menu(Scanner scanner, List<MenuAction> actions) {
        this.actions = actions;
        this.scanner = scanner;
    }

    private void printAction() {
        for (int i = 0; i < actions.size(); i++) {
            System.out.printf("%d -%s\n", i + 1, actions.get(i).getName());
        }
        System.out.printf("%d - Exit\n", actions.size() + 1);
    }

    private int getChoice() {
        System.out.print("Enter your choice:");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice - 1;
    }

    public void addAction(MenuAction action) {
        actions.add(action);
    }

    public void run() {
        while (true) {
            printAction();
            int choice = getChoice();
            if (choice < 0 || choice > actions.size()) {
                System.out.println("Incorrect input");
                continue;
            }

            if (choice == actions.size()) break;

            actions.get(choice).doAction();
            if (actions.get(choice).closedAfter()) break;
        }
    }
}
