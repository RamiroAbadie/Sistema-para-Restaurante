package main.java.restaurante.service;

import main.java.restaurante.menu.ItemMenu;
import main.java.restaurante.menu.Menu;

import java.util.List;

public class GestorMenu {
    private static GestorMenu instancia;
    private final Menu menu;

    private GestorMenu() {
        this.menu = new Menu();
    }

    public static GestorMenu getInstancia() {
        if (instancia == null) {
            instancia = new GestorMenu();
        }
        return instancia;
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
