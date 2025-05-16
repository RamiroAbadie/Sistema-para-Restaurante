package main.java.restaurante.model;

import main.java.restaurante.strategy.MedioDePago;
import main.java.restaurante.strategy.Notificador;
import main.java.restaurante.strategy.TarjetaCredito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nombre;
    private String email;
    private Cupon cupon;
    private List<MedioDePago> mediosDePago;
    private List<Pedido> pedidos;

    public Cliente(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
        this.mediosDePago = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    public void asignarCupon() {
        // TODO: Aca estaria la logica de creacion de un cupon
        Cupon cupon = new Cupon(0.15);
        this.cupon = cupon;
    }

    public void agregarMedioDePago() {
        // TODO: Aca se pide que se agregue un medio de pago
        MedioDePago tarjeta = new TarjetaCredito("1234-5678-0000", "Lionel M.");
        mediosDePago.add(tarjeta);
    }

    public void asignarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void pagarPedido(Pedido pedido) {
        if (mediosDePago.isEmpty()) {
            throw new IllegalArgumentException("El cliente no tiene medios de pago disponible");
        }

        BigDecimal total = pedido.calcularTotal();
        if (cupon != null) {
            total = cupon.aplicarDescuento(total);
        }

        //TODO: Que seleccione un medio de pago
        mediosDePago.getFirst().pagar(pedido, total);
    }

    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public List<Pedido> getPedidos() { return pedidos; }
    public List<MedioDePago> getMediosDePago() { return mediosDePago; }
}
