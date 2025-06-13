package main.java.restaurante.observer;

import java.time.LocalDateTime;

public interface PedidoObserver {

    public void notificar(LocalDateTime ahora);// Notificamos, que? a quien? eso lo define cada impl.
}
