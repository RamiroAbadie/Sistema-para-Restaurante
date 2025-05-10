package main.java.restaurante.strategy;

import main.java.restaurante.model.Pedido;
import java.math.BigDecimal;

public abstract class Tarjeta implements MedioDePago {
    protected String numero;
    protected String titular;

    public Tarjeta(String numero, String titular) {
        this.numero = numero;
        this.titular = titular;
    }
}
