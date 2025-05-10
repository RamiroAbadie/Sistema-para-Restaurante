package main.java.restaurante.model;

import main.java.restaurante.strategy.MedioDePago;
import main.java.restaurante.strategy.Notificador;

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

    public void agregarMedioDePago(MedioDePago medio) {
        this.mediosDePago.add(medio);
    }

    public void asignarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void pagarPedido(Pedido pedido, MedioDePago medio, Notificador notificador) {
        if (!mediosDePago.contains(medio)) {
            throw new IllegalArgumentException("El medio de pago no está registrado en el cliente.");
        }

        BigDecimal total = pedido.calcularTotal();
        if (cupon != null) {
            total = cupon.aplicarDescuento(total);
        }

        medio.pagar(pedido, total);
        notificador.enviarNotificacion(pedido);
    }

    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public List<Pedido> getPedidos() { return pedidos; }
    public List<MedioDePago> getMediosDePago() { return mediosDePago; }
}
