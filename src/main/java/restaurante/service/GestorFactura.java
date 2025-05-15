package main.java.restaurante.service;

import main.java.restaurante.model.Factura;
import main.java.restaurante.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class GestorFactura {
    private static GestorFactura instancia;

    private List<Factura> facturas;

    private GestorFactura() {
        this.facturas = new ArrayList<>();
    }

    public static GestorFactura getInstancia() {
        if (instancia == null) {
            instancia = new GestorFactura();
        }
        return instancia;
    }

    public Factura generarFactura(Pedido pedido) {
        Factura factura = new Factura(pedido);
        facturas.add(factura);
        return factura;
    }

    public List<Factura> getFacturasEmitidas() {
        return facturas;
    }
}
