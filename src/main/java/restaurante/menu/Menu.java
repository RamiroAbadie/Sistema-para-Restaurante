package main.java.restaurante.menu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public Producto buscarProductoPorNombre(String nombreBuscado) {
        return buscarRecursivo(items, nombreBuscado.toLowerCase(), new HashSet<>());
    }

    private Producto buscarRecursivo(List<ItemMenu> items, String nombreBuscado, Set<CategoriaMenu> visitadas) {
        for (ItemMenu item : items) {
            if (item instanceof Producto producto) {
                if (producto.getNombre().toLowerCase().equals(nombreBuscado)) {
                    return producto;
                }
            } else if (item instanceof CategoriaMenu categoria) {
                if (visitadas.contains(categoria)) continue; // Previene loops
                visitadas.add(categoria);
                Producto encontrado = buscarRecursivo(categoria.getItems(), nombreBuscado, visitadas);
                if (encontrado != null) return encontrado;
            }
        }
        return null;
    }



    public List<ItemMenu> getItems() {
        return items;
    }
}
