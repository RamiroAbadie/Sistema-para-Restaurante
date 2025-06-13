package main.java.restaurante.strategy;

import main.java.restaurante.model.Pedido;
import java.math.BigDecimal;

public interface MedioDePago {
    boolean pagar(Pedido pedido);
}
