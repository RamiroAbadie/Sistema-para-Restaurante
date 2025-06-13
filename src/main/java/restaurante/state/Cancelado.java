package main.java.restaurante.state;

import main.java.restaurante.model.Pedido;

import java.time.LocalDateTime;

public class Cancelado extends EstadoPedido{
    private LocalDateTime fechaCancelacion;

    public Cancelado(LocalDateTime fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    @Override
    public void avanzarPedido(Pedido pedido) {
        System.out.println("⛔ El pedido ya fue cancelado. No puede volver atras ⛔");
    }

    @Override
    public String getNombreEstado() {
        return "❌ Cancelado";
    }
}
