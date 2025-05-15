package main.java.restaurante.service;

import main.java.restaurante.model.Pedido;
import main.java.restaurante.model.Reporte;

import java.util.List;

public class GestorReporte {
    private static GestorReporte instancia;

    private GestorReporte() {}

    public static GestorReporte getInstancia() {
        if (instancia == null) {
            instancia = new GestorReporte();
        }
        return instancia;
    }

    public Reporte generarReporte(List<Pedido> pedidos) {
        return new Reporte(pedidos);
    }
}
