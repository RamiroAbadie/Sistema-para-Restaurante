package main.java.restaurante.model;

import java.util.List;

public class Reporte {
    private List<Pedido> pedidos;

    public Reporte(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void mostrar() {
        System.out.println("\n=== REPORTE DE PEDIDOS ===");
        for (Pedido p : pedidos) {
            System.out.println("Pedido #" + p.getNumeroOrden()
                    + " - Estado: " + p.getEstadoActual()
                    + " - Total: $" + p.calcularTotal());
        }
        System.out.println("==========================\n");
    }
}
