package main.java.restaurante.state;

import main.java.restaurante.model.Pedido;

import java.time.LocalDateTime;

public class Programado extends EstadoPedido {
    @Override
    public void avanzarPedido(Pedido pedido) {
        /* Vacio, no nos sirve en esta clase
        solo vamos a avanzar el pedido cuando sea el momento de tick
         */
    }

    @Override
    public void tick(Pedido pedido) {
        //TODO: Esto lo tiene que hacer un notificador:
        System.out.println("Pedido " + pedido.getNumeroOrden() + " activado a EnEspera");
        pedido.setEstado(new EnEspera());
    }

    @Override
    public boolean puedeCancelar(Pedido pedido) {
        pedido.setEstado(new Cancelado(LocalDateTime.now()));
        return true;
    }

    @Override
    public boolean puedeAgregarProducto() {
        return true;
    }

    @Override
    public String getNombreEstado() {
        return "⏲️ Programado";
    }
}
