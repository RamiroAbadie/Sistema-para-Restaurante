package main.java.restaurante.service;

import main.java.restaurante.model.Pedido;
import main.java.restaurante.menu.Producto;
import main.java.restaurante.model.Personal;
import main.java.restaurante.state.EnEspera;
import main.java.restaurante.state.Entregado;
import main.java.restaurante.strategy.Notificador;
import main.java.restaurante.strategy.NotificadorApp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class GestorPedido {
    private static GestorPedido instancia;
    private List<Pedido> pedidos;
    private Notificador notificador;

    private GestorPedido() {
        this.pedidos = new ArrayList<>();
        // TODO
        /* Aca hay que ver si hacer una lista de notificadores en los que
        se puedan guardar diferentes tipos de notificadores, por el momento
        solo se puede uno que esta harcodeado aca */
        this.notificador = new NotificadorApp();
    }

    public static GestorPedido getInstancia() {
        if (instancia == null) {
            instancia = new GestorPedido();
        }
        return instancia;
    }

    public Pedido crearPedido() {
        Pedido pedido = new Pedido(new EnEspera());
        pedidos.add(pedido);
        return pedido;
    }

    public void avanzarEstadoPedido(int numeroOrden) {
        Pedido pedido = buscarPedidoPorId(numeroOrden);
        if (pedido == null) {
            throw new NoSuchElementException("No se encontró el pedido solicitado.");
        }
        if (pedido.getEstado() instanceof Entregado) {
            throw new IllegalStateException("No se puede avanzar un pedido ya entregado.");
        }
        pedido.avanzarEstado();
        notificador.enviarNotificacion(pedido);
    }

    public void agregarProductoAlPedido(int numeroOrden, Producto producto, int cantidad) {
        Pedido pedido = buscarPedidoPorId(numeroOrden);
        if (pedido == null) {
            throw new NoSuchElementException("No se encontró el pedido solicitado.");
        }
        if (pedido.getEstado() instanceof Entregado) {
            throw new IllegalStateException("No se puede modificar un pedido ya entregado.");
        }
        pedido.agregarProducto(producto, cantidad);
    }

    public BigDecimal getTotalPedido(int numeroOrden){
        Pedido pedido = buscarPedidoPorId(numeroOrden);
        if (pedido == null) {
            throw new NoSuchElementException("No se encontró el pedido solicitado.");
        }
        if (pedido.getEstado() instanceof Entregado) {
            throw new IllegalStateException("No se puede modificar un pedido ya entregado.");
        }
        return pedido.calcularTotal();
    }

    public Pedido getPedidoById(int numeroOrden){
        Pedido pedido = buscarPedidoPorId(numeroOrden);
        if (pedido == null) {
            throw new NoSuchElementException("No se encontró el pedido solicitado.");
        }
        return pedido;
    }

    private Pedido buscarPedidoPorId(int numeroOrden) {
        for (Pedido p : pedidos) {
            if (p.getNumeroOrden() == numeroOrden) {
                return p;
            }
        }
        return null;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}
