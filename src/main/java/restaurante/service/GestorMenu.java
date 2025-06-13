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
        CategoriaMenu categoriaPlatosPizzas = new CategoriaMenu("🍕 Pizzas 🍕");
        CategoriaMenu categoriaPlatosHamburguesa = new CategoriaMenu("🍔 Hamburguesas 🍔");
        Producto pizzaMuzza = new Producto("Pizza Muzzarella", "Pizza de 8 porciones", BigDecimal.valueOf(3500), Set.of("gluten", "lactosa"));
        Producto pizzaNapolitana = new Producto("Pizza Napolitana", "Pizza con salsa de tomate, mozzarella, tomate en rodajas y albahaca", BigDecimal.valueOf(4000), Set.of("gluten", "lactosa"));
        Producto hamburguesaCompleta = new Producto("Hamburguesa Completa", "Hamburguesa con carne, lechuga, tomate, cebolla y papas fritas", BigDecimal.valueOf(3800), Set.of("gluten", "lactosa"));
        categoriaPlatosPizzas.agregarItem(pizzaMuzza);
        categoriaPlatosPizzas.agregarItem(pizzaNapolitana);
        categoriaPlatosHamburguesa.agregarItem(hamburguesaCompleta);
        menu.agregarItem(categoriaPlatosPizzas);
        menu.agregarItem(categoriaPlatosHamburguesa);
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
