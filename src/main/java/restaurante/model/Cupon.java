package main.java.restaurante.model;

import java.math.BigDecimal;

public class Cupon {
    private final Integer codigo;
    private final double porcentajeDescuento;

    public Cupon(Integer codigo, double porcentajeDescuento) {
        this.codigo = codigo;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public void aplicarDescuento(Pedido pedido) {
        pedido.setDescuentoAplicado(this.porcentajeDescuento);
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public Integer getCodigo() {
        return codigo;
    }
}
