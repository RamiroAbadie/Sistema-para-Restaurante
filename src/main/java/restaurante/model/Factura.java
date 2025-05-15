package main.java.restaurante.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Factura {
    private static int contadorFacturas = 1;
    private final int numeroFactura;
    private Pedido pedido;
    private LocalDateTime fechaEmision;

    public Factura(Pedido pedido) {
        this.numeroFactura = contadorFacturas++;
        this.pedido = pedido;
        this.fechaEmision = LocalDateTime.now();
    }

    public void mostrar() {
        System.out.println("\n===== FACTURA =====");
        System.out.println("Fecha: " + fechaEmision.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        System.out.println("Pedido N°: " + pedido.getNumeroOrden());
        System.out.println("Estado actual: " + pedido.getEstadoActual());
        System.out.println("Productos:");

        BigDecimal total = BigDecimal.ZERO;
        for (var pp : pedido.getProductos()) {
            BigDecimal subtotal = pp.calcularSubtotal();
            System.out.println("- " + pp.getProducto().getNombre() + " x" + pp.getCantidad() + ": $" + subtotal);
            total = total.add(subtotal);
        }

        System.out.println("Total: $" + total);
        System.out.println("====================\n");
    }
}
