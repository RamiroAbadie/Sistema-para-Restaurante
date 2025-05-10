package main.java.restaurante.service;

import main.java.restaurante.menu.ItemMenu;
import main.java.restaurante.menu.Menu;

import java.util.List;

public class GestorMenu {
    private final Menu menu;

    public GestorMenu() {
        this.menu = new Menu();
    }

    public void agregarItemAlMenu(ItemMenu item) {
        menu.agregarItem(item);
    }

    public void mostrarMenu() {
        menu.mostrarMenu();
    }

    public List<ItemMenu> getItems() {
        return menu.getItems();
    }
}
