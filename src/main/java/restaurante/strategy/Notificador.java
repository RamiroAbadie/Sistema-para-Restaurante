package main.java.restaurante.strategy;

import main.java.restaurante.model.Pedido;

public interface Notificador {
    void enviarNotificacion(Pedido pedido);
}
