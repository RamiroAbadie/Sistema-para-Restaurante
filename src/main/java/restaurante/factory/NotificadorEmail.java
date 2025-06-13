package main.java.restaurante.factory;

import main.java.restaurante.model.Pedido;

public class NotificadorEmail implements Notificador {
    @Override
    public void enviarNotificacionAvancePedido(Pedido pedido) {
        System.out.println("📧 EMAIL: Pedido #" + pedido.getNumeroOrden() +
                " ahora está en estado: " + pedido.getEstadoActual());
    }

    @Override
    public void enviarNotificacionPagoConfirmado() {
        System.out.println("📧 EMAIL: El pedido se pago con exito");
    }
}
