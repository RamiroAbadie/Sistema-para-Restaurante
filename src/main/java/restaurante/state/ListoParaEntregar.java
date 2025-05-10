package main.java.restaurante.state;

import main.java.restaurante.model.Pedido;

public class ListoParaEntregar extends EstadoPedido {
    @Override
    public void avanzarPedido(Pedido pedido) {
        pedido.setEstado(new Entregado());
    }

    @Override
    public String getNombreEstado() {
        return "Listo para entregar";
    }
}
