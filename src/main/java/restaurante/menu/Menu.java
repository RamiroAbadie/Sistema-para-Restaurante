package main.java.restaurante.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<ItemMenu> items;

    public Menu() {
        this.items = new ArrayList<>();
    }

    public void agregarItem(ItemMenu item) {
        items.add(item);
    }

    public void mostrarMenu() {
        System.out.println("\n=== MENÚ ===");
        for (ItemMenu item : items) {
            item.mostrar();
        }
    }

    public List<ItemMenu> getItems() {
        return items;
    }
}
