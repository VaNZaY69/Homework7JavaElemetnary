package com.vanzay.menu;

public interface MenuAction {
    void doAction();

    String getName();

    boolean closedAfter();
}
