package main.java.restaurante.factory;

import main.java.restaurante.model.Pedido;

public class NotificadorApp implements Notificador {
    @Override
    public void enviarNotificacionAvancePedido(Pedido pedido) {
        System.out.println("📳 APP: Pedido #" + pedido.getNumeroOrden() +
                " ahora está en estado: " + pedido.getEstadoActual());
    }

    @Override
    public void enviarNotificacionPagoConfirmado() {
        System.out.println("📳 APP: El pedido se pago con exito");
    }
}
