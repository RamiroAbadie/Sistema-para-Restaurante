package main.java.restaurante.state;

import main.java.restaurante.model.Pedido;

import java.time.LocalDateTime;

public abstract class EstadoPedido {
    public abstract void avanzarPedido(Pedido pedido);
    public void tick(Pedido pedido) {} // default: no hacemos nada
    public boolean puedeCancelar(Pedido pedido) { return false; } // default: no
    public boolean puedeAgregarProducto() { return false; } // default: no
    public abstract String getNombreEstado();
}
