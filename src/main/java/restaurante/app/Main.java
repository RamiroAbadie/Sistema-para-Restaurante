package main.java.restaurante.app;

import main.java.restaurante.model.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Restaurante restaurante = Restaurante.getInstancia();

        // === Crear y registrar cliente ===
        restaurante.registrarCliente();

        //Aca podemos usar esto para ver a los clientes (lista read only)
        List<Cliente> clientes = restaurante.getClientes();
        System.out.println("=== Email cliente creado: ===");
        System.out.println(clientes.getFirst().getEmail());
        System.out.println("======");

        // === Crear productos y agregarlos al menú ===
        restaurante.agregarItemAlMenu();
        restaurante.mostrarMenu();

        // === Crear medio de pago y cupón ===
        restaurante.agregarMedioDePago(clientes.getFirst().getEmail());
        restaurante.asignarCupon(clientes.getFirst().getEmail());

        // === Crear pedido y agregar productos ===
        int nroDeOrden = restaurante.crearPedidoParaCliente(clientes.getFirst().getEmail());
        restaurante.agregarProductoAlPedido(nroDeOrden, "pizza muzzarella", 2);

        System.out.println("\nTotal del pedido (sin descuento): $" + restaurante.devolverTotalPedido(nroDeOrden));

        // === Pagar y notificar ===
        restaurante.clientePagarPedido(nroDeOrden, clientes.getFirst().getEmail());

        // === Crear y agregar personal ===
        restaurante.agregarPersonal();
        List<Personal> empleados = restaurante.getEmpleados();
        System.out.println("\n=== Nombre Personal (Mesero) creado: ===");
        System.out.println(empleados.getFirst().getNombre());
        System.out.println("======\n");

        // === Avanzar estado y notificar ===
        restaurante.avanzarEstadoPedido("Juan Mesero", nroDeOrden); // En preparación
        restaurante.avanzarEstadoPedido("Juan Mesero", nroDeOrden); // Listo para entregar
        restaurante.avanzarEstadoPedido("Juan Mesero", nroDeOrden); // Entregado
        // Descomentar si se quiere probar avanzar pedido entregado:
        //restaurante.avanzarEstadoPedido("Juan Mesero", nroDeOrden);

        // === Emitir factura ===
        int nroFactura = restaurante.generarFactura(nroDeOrden);
        restaurante.mostrarFactura(nroFactura);

        // === Generar y mostrar reporte de ventas ===
        int nroReporte = restaurante.generarReporte();
        restaurante.mostrarReporte(nroReporte);
    }
}
