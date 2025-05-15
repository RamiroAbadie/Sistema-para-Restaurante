package main.java.restaurante.model;

import main.java.restaurante.menu.Producto;
import main.java.restaurante.state.EstadoPedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contadorPedidos = 1;
    private final int numeroOrden;
    private EstadoPedido estado;
    private List<ProductoPedido> productos;

    public Pedido(EstadoPedido estadoInicial) {
        this.numeroOrden = contadorPedidos++;
        this.estado = estadoInicial;
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto, int cantidad) {
        ProductoPedido existente = buscarProducto(producto);
        if (existente != null) {
            existente.setCantidad(existente.getCantidad() + cantidad);
        } else {
            productos.add(new ProductoPedido(producto, cantidad));
        }
    }

    public ProductoPedido buscarProducto(Producto producto) {
        for (ProductoPedido pp : productos) {
            if (pp.getProducto().equals(producto)) {
                return pp;
            }
        }
        return null;
    }

    public BigDecimal calcularTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (ProductoPedido pp : productos) {
            total = total.add(pp.calcularSubtotal());
        }
        return total;
    }

    public void avanzarEstado() {
        estado.avanzarPedido(this);
    }

    public void setEstado(EstadoPedido nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public String getEstadoActual() {
        return estado.getNombreEstado();
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public List<ProductoPedido> getProductos() {
        return productos;
    }

    public EstadoPedido getEstado() {
        return estado;
    }
}
