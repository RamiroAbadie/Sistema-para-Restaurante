package main.java.restaurante.observer;

import main.java.restaurante.model.Pedido;
import main.java.restaurante.state.EstadoPedido;

import java.time.LocalDateTime;

public class PedidoProgramadoObserver implements PedidoObserver{
    // Como o un wrapper cuya funcion es notificar al pedido del que es responsable.
    private Pedido pedido; // Pedido del que es responsable

    public PedidoProgramadoObserver(Pedido pedido) {
        this.pedido = pedido;
    }
    @Override
    public void notificar(LocalDateTime ahora) {
        // Aca chequea que sea el horario para dar marcha al pedido
        if (!ahora.isBefore(pedido.getHorarioProgramado())) {
            pedido.tick();
        }
    }
}
