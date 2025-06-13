package main.java.restaurante.model;

import main.java.restaurante.menu.Producto;
import main.java.restaurante.state.EstadoPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contadorPedidos = 1;
    private final int numeroOrden;
    private EstadoPedido estado;
    private double descuentoAplicado;
    private List<ProductoPedido> productos;

    public Pedido(EstadoPedido estadoInicial) {
        this.numeroOrden = contadorPedidos++;
        this.estado = estadoInicial;
        this.productos = new ArrayList<>();
        this.descuentoAplicado = 0;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        // Solo si EstadoPedido es EnEspera o Programado
        if (this.estado.puedeAgregarProducto()){
            // Buscamos si el producto ya esta en el pedido
            ProductoPedido existente = buscarProducto(producto);
            if (existente != null) {
                existente.setCantidad(existente.getCantidad() + cantidad);
                //TODO: Este mensaje lo debe dar un Notificador
                System.out.println("➕ Producto: " + producto.getNombre() + " agregado con exito! 🫡");
            } else {
                productos.add(new ProductoPedido(producto, cantidad));
            }
        }
        else {
            //TODO: Este mensaje lo debe dar un Notificador
            System.out.println("Ya no es posible agregar mas productos 😞✊");
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
        BigDecimal descuento = total.multiply(BigDecimal.valueOf(descuentoAplicado));
        return total.subtract(descuento);
    }

    public void avanzarEstado() {
        estado.avanzarPedido(this);
    }

    public Pedido cancelar(){
        this.estado.puedeCancelar(this);
        return this;
    }

    public void tick(LocalDateTime ahora){
        this.estado.tick(this, ahora);
    }

    public void setEstado(EstadoPedido nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void setDescuentoAplicado(double descuentoAplicado) {
        if (descuentoAplicado>0){
            this.descuentoAplicado += descuentoAplicado;
        }
        else {
            this.descuentoAplicado = descuentoAplicado;
        }
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

    public double getDescuentoAplicado() {
        return descuentoAplicado;
    }
}
