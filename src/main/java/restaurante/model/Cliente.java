package main.java.restaurante.model;

import main.java.restaurante.factory.ValidadorCupon;
import main.java.restaurante.strategy.*;

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

    public void asignarCupon(Cupon cupon) {
        this.cupon = cupon;
    }

    public void agregarMedioDePago() {
        // TODO: Aca se pide que se agregue un medio de pago
        MedioDePago efectivo = new Efectivo(Moneda.ARS$);
        MedioDePago tarjeta = new TarjetaCredito("1234-5678-0000", "Lionel M.");
        mediosDePago.add(efectivo);
        mediosDePago.add(tarjeta);
    }

    public void asignarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public boolean pagarPedido(Pedido pedido, ValidadorCupon validadorCupon) {
        if (mediosDePago.isEmpty()) {
            throw new IllegalArgumentException("El cliente no tiene medios de pago disponible");
        }

        BigDecimal total = pedido.calcularTotal();
        if (cupon != null && validadorCupon.validar(cupon.getCodigo())) {
            cupon.aplicarDescuento(pedido);
        }

        //TODO: Que seleccione un medio de pago
        return mediosDePago.getFirst().pagar(pedido);
    }

    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public List<Pedido> getPedidos() { return pedidos; }
    public List<MedioDePago> getMediosDePago() { return mediosDePago; }
}
