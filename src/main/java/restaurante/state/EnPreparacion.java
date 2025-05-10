package main.java.restaurante.state;

import main.java.restaurante.model.Pedido;

public class EnPreparacion extends EstadoPedido {
    @Override
    public void avanzarPedido(Pedido pedido) {
        pedido.setEstado(new ListoParaEntregar());
    }

    @Override
    public String getNombreEstado() {
        return "En preparación";
    }
}
