package main.java.restaurante.state;

import main.java.restaurante.model.Pedido;

import java.time.LocalDateTime;

public class EnEspera extends EstadoPedido {
    @Override
    public void avanzarPedido(Pedido pedido) {
        pedido.setEstado(new EnPreparacion());
    }

    @Override
    public boolean puedeCancelar(Pedido pedido) {
        pedido.setEstado(new Cancelado(LocalDateTime.now()));
        return true;
    }

    @Override
    public boolean puedeAgregarProducto() { return true; }
    @Override
    public String getNombreEstado() {
        return "👌 En espera";
    }
}
