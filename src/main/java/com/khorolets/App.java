package com.khorolets;

import com.khorolets.view.MainMenu;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        MainMenu menu = new MainMenu();
        menu.showMenu();
    }
}
