package main.java.restaurante.strategy;

import main.java.restaurante.model.Pedido;

public class NotificadorApp implements Notificador {
    @Override
    public void enviarNotificacion(Pedido pedido) {
        System.out.println("APP: Pedido #" + pedido.getNumeroOrden() + " ahora está en estado: " + pedido.getEstadoActual());
    }
}
