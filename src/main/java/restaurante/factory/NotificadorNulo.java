package main.java.restaurante.factory;

import main.java.restaurante.model.Pedido;

public class NotificadorNulo implements Notificador {
    @Override
    public void enviarNotificacionAvancePedido(Pedido pedido) {
        // No hace nada
    }

    @Override
    public void enviarNotificacionPagoConfirmado() {
        // No hace nada
    }
}
