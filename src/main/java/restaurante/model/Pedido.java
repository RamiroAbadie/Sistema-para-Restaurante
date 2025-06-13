package main.java.restaurante.model;

import main.java.restaurante.app.Plataforma;
import main.java.restaurante.menu.Producto;
import main.java.restaurante.state.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contadorPedidos = 1;
    private final int numeroOrden;
    private EstadoPedido estado;
    private LocalDateTime horarioProgramado;
    private double descuentoAplicado;
    private List<ProductoPedido> productos;

    public Pedido(EstadoPedido estadoInicial) {
        this.numeroOrden = contadorPedidos++;
        this.estado = estadoInicial;
        this.horarioProgramado = LocalDateTime.now();
        this.productos = new ArrayList<>();
        this.descuentoAplicado = 0;
    }

    // Constructor SOLO para Pedidos programados
    public Pedido(LocalDateTime horarioProgramado) {
        this.numeroOrden = contadorPedidos++;
        this.estado = new Programado();
        this.horarioProgramado = horarioProgramado;
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

    public Pedido cancelar() {
        this.estado.puedeCancelar(this);
        return this;
    }

    public void tick(){
        this.estado.tick(this);
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

    public LocalDateTime getHorarioProgramado() {
        return horarioProgramado;
    }

    public Float getTiempoEspera(Integer cantidadPedidos, Plataforma plataforma) {
        // Estado: En espera
        if (estado instanceof EnEspera) {
            return estado.getTiempoEspera(cantidadPedidos);
        }

        // Estado: En preparación
        if (estado instanceof EnPreparacion) {
            int tiempo = productos.stream()
                    .mapToInt(pp -> pp.getProducto().getTiempoPreparacionMin() * pp.getCantidad())
                    .sum();
            return (float) tiempo;
        }

        // Estado: Listo para entregar
        if (estado instanceof ListoParaEntregar) {
            return plataforma == Plataforma.APP ? 15.0F : 0.0F;
        }

        // Cualquier otro estado (Entregado, Cancelado, etc.)
        return 0.0F;
    }

}
