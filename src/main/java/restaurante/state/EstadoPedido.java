package main.java.restaurante.state;

import main.java.restaurante.model.Pedido;

public abstract class EstadoPedido {
    public abstract void avanzarPedido(Pedido pedido);
    public abstract String getNombreEstado();
}
