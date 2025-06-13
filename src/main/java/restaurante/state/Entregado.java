package main.java.restaurante.state;

import main.java.restaurante.model.Pedido;

import java.time.LocalDateTime;

public class Entregado extends EstadoPedido {
    @Override
    public void avanzarPedido(Pedido pedido) {
        System.out.println("⛔ El pedido ya fue entregado. No puede avanzar más. ⛔");
    }

    @Override
    public String getNombreEstado() {
        return "✅ Entregado";
    }
}
