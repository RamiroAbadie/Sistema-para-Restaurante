package main.java.restaurante.model;

import java.math.BigDecimal;

public class Cupon {
    private final double porcentajeDescuento;

    public Cupon(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public void aplicarDescuento(Pedido pedido) {
        pedido.setDescuentoAplicado(this.porcentajeDescuento);
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }
}
