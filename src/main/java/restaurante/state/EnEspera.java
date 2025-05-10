package main.java.restaurante.state;

import main.java.restaurante.model.Pedido;

public class EnEspera extends EstadoPedido {
    @Override
    public void avanzarPedido(Pedido pedido) {
        pedido.setEstado(new EnPreparacion());
    }

    @Override
    public String getNombreEstado() {
        return "En espera";
    }
}
