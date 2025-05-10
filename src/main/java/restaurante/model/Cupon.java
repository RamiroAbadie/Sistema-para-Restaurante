package main.java.restaurante.model;

import java.math.BigDecimal;

public class Cupon {
    private final double porcentajeDescuento;

    public Cupon(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public BigDecimal aplicarDescuento(BigDecimal total) {
        BigDecimal descuento = total.multiply(BigDecimal.valueOf(porcentajeDescuento));
        return total.subtract(descuento);
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }
}
