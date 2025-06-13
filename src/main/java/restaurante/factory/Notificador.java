package main.java.restaurante.factory;

import main.java.restaurante.model.Pedido;

public interface Notificador {
    void enviarNotificacionAvancePedido(Pedido pedido);
    void enviarNotificacionPagoConfirmado();
}
