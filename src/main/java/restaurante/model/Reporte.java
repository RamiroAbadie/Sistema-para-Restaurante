package main.java.restaurante.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Reporte {
    private final LocalDate fechaGeneracion;
    private final List<Pedido> pedidos;
    private final int cantidadPedidos;
    private final BigDecimal montoTotal;

    public Reporte(List<Pedido> pedidosEntregados) {
        this.fechaGeneracion = LocalDate.now();
        this.pedidos = pedidosEntregados;
        this.cantidadPedidos = pedidosEntregados.size();
        this.montoTotal = calcularMontoTotal(pedidosEntregados);
    }

    private BigDecimal calcularMontoTotal(List<Pedido> pedidos) {
        BigDecimal total = BigDecimal.ZERO;
        for (Pedido p : pedidos) {
            total = total.add(p.calcularTotal());
        }
        return total;
    }

    public void mostrar() {
        System.out.println("\n=== REPORTE DE VENTAS ===");
        System.out.println("Fecha: " + fechaGeneracion);
        System.out.println("Cantidad de pedidos entregados: " + cantidadPedidos);
        System.out.println("Monto total recaudado: $" + montoTotal);
        System.out.println("==========================\n");
    }
}
