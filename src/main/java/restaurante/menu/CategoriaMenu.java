package main.java.restaurante.menu;

import java.util.ArrayList;
import java.util.List;

public class CategoriaMenu extends ItemMenu {
    private String nombre;
    private List<ItemMenu> items;

    public CategoriaMenu(String nombre) {
        this.nombre = nombre;
        this.items = new ArrayList<>();
    }

    public void agregarItem(ItemMenu item) {
        items.add(item);
    }

    @Override
    public void mostrar() {
        System.out.println("» " + nombre);
        for (ItemMenu item : items) {
            item.mostrar();
        }
    }

    public String getNombre() {
        return nombre;
    }

    public List<ItemMenu> getItems() {
        return items;
    }
}
