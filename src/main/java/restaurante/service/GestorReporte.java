package main.java.restaurante.service;

import main.java.restaurante.model.Pedido;
import main.java.restaurante.model.Reporte;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestorReporte {
    private List<Reporte> reportes;
    private static GestorReporte instancia;

    private GestorReporte() {
        this.reportes = new ArrayList<>();
    }

    public static GestorReporte getInstancia() {
        if (instancia == null) {
            instancia = new GestorReporte();
        }
        return instancia;
    }

    public Reporte generarReporte(List<Pedido> pedidos) {
        List<Pedido> entregados = pedidos.stream()
                .filter(p -> p.getEstadoActual().equals("Entregado"))
                .collect(Collectors.toList());

        Reporte nuevo = new Reporte(entregados);
        reportes.add(nuevo);
        return nuevo;
    }

    public List<Reporte> getReportes() {
        return reportes;
    }
}
