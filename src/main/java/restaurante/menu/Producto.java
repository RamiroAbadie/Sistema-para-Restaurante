package main.java.restaurante.menu;

import java.math.BigDecimal;
import java.util.Set;

public class Producto extends ItemMenu {
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Set<String> alergenos;

    private int tiempoPreparacionMin;

    public Producto(String nombre, String descripcion, BigDecimal precio, Set<String> alergenos, int tiempoPreparacionMin) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.alergenos = alergenos;
        this.tiempoPreparacionMin = tiempoPreparacionMin;
    }

    @Override
    public void mostrar() {
        System.out.println("- " + nombre + " (" + descripcion + ") - $" + precio);
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public Set<String> getAlergenos() {
        return alergenos;
    }

    public int getTiempoPreparacionMin() {
        return tiempoPreparacionMin;
    }
}
