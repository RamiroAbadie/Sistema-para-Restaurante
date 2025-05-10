package main.java.restaurante.model;

import main.java.restaurante.menu.Producto;
import java.math.BigDecimal;

public class ProductoPedido {
    private Producto producto;
    private int cantidad;

    public ProductoPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public BigDecimal calcularSubtotal() {
        return producto.getPrecio().multiply(BigDecimal.valueOf(cantidad));
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
