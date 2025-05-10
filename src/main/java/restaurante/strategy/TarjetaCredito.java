package main.java.restaurante.strategy;

import main.java.restaurante.model.Pedido;
import java.math.BigDecimal;

public class TarjetaCredito extends Tarjeta {
    public TarjetaCredito(String numero, String titular) {
        super(numero, titular);
    }

    @Override
    public void pagar(Pedido pedido, BigDecimal total) {
        System.out.println("Pagando con tarjeta de crédito de " + titular + ": $" + total);
    }
}
