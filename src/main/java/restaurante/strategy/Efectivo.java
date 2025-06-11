package main.java.restaurante.strategy;

import main.java.restaurante.model.Pedido;

public class Efectivo implements MedioDePago{
    Moneda moneda;

    public Efectivo(Moneda moneda) {
        this.moneda = moneda;
    }

    @Override
    public void pagar(Pedido pedido) {
        pedido.setDescuentoAplicado(0.1);
        System.out.println("Pagando con efectivo: " + moneda + pedido.calcularTotal());
    }
}
