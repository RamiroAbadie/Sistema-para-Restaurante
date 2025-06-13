package main.java.restaurante.state;

import main.java.restaurante.model.Pedido;

import java.time.LocalDateTime;

public class EnPreparacion extends EstadoPedido {
    @Override
    public void avanzarPedido(Pedido pedido) {
        pedido.setEstado(new ListoParaEntregar());
    }

    @Override
    public boolean puedeCancelar(Pedido pedido) { return true; }

    @Override
    public String getNombreEstado() {
        return "🧑‍🍳 En preparación";
    }

    @Override
    public Float getTiempoEspera(Integer cantidadPedidos) {
        return cantidadPedidos + 10f;
    }
}
