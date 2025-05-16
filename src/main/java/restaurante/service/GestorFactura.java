package main.java.restaurante.service;

import main.java.restaurante.model.Factura;
import main.java.restaurante.model.Pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

    public int generarFactura(Pedido pedido) {
        Factura factura = new Factura(pedido);
        facturas.add(factura);
        return factura.getNumeroFactura();
    }

    public void mostrarFacturaPorId(int numeroFactura){
        Factura factura = buscarFacturaPorId(numeroFactura);
        if (factura == null) {
            throw new NoSuchElementException("No se encontró la factura solicitada.");
        }
        factura.mostrar();
    }

    private Factura buscarFacturaPorId(int numeroFactura) {
        for (Factura f : facturas) {
            if (f.getNumeroFactura() == numeroFactura) {
                return f;
            }
        }
        return null;
    }
    public List<Factura> getFacturasEmitidas() {
        return facturas;
    }
}
