package main.java.restaurante.factory;

import main.java.restaurante.model.Pedido;

public class NotificadorSMS implements Notificador {
    @Override
    public void enviarNotificacionAvancePedido(Pedido pedido) {
        System.out.println("💬 SMS: Pedido #" + pedido.getNumeroOrden() +
                " actualizado a: " + pedido.getEstadoActual());
    }

    @Override
    public void enviarNotificacionPagoConfirmado() {
        System.out.println("💬 SMS: El pedido se pago con exito");
    }
}
