package main.java.restaurante.service;

import main.java.restaurante.menu.CategoriaMenu;
import main.java.restaurante.menu.ItemMenu;
import main.java.restaurante.menu.Menu;
import main.java.restaurante.menu.Producto;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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

    public void agregarItemAlMenu() {
        //TODO: Aca se deberia pedir al usuario ingresar los datos para crear producto con categoria
        CategoriaMenu categoriaPlatos = new CategoriaMenu("Platos principales");
        Producto pizza = new Producto("Pizza Muzzarella", "Pizza de 8 porciones", BigDecimal.valueOf(3500), Set.of("gluten", "lactosa"));
        categoriaPlatos.agregarItem(pizza);
        menu.agregarItem(categoriaPlatos);
    }

    public Producto buscarProductoPorNombre(String nombreBuscado){
        return menu.buscarProductoPorNombre(nombreBuscado);
    }

    public void mostrarMenu() {
        menu.mostrarMenu();
    }

    public List<ItemMenu> getItems() {
        return Collections.unmodifiableList(menu.getItems());
    }
}
