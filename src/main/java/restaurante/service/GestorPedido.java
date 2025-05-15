package main.java.restaurante.service;

import main.java.restaurante.model.Pedido;
import main.java.restaurante.menu.Producto;
import main.java.restaurante.state.EnEspera;
import main.java.restaurante.state.Entregado;
import main.java.restaurante.strategy.Notificador;

import java.util.ArrayList;
import java.util.List;

public class GestorPedido {
    private static GestorPedido instancia;
    private List<Pedido> pedidos;

    private GestorPedido() {
        this.pedidos = new ArrayList<>();
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

    public void avanzarEstadoPedido(Pedido pedido, Notificador notificador) {
        pedido.avanzarEstado();
        notificador.enviarNotificacion(pedido);
    }

    public void agregarProductoAlPedido(Pedido pedido, Producto producto, int cantidad) {
        if (pedido.getEstado() instanceof Entregado) {
            throw new IllegalStateException("No se puede modificar un pedido ya entregado.");
        }
        pedido.agregarProducto(producto, cantidad);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}
