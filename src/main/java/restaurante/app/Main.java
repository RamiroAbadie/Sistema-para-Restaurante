package main.java.restaurante.app;

import main.java.restaurante.model.*;
import main.java.restaurante.observer.RelojSistema;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //TODO: Seleccionar plataforma (para test descomentar uno por vez)
        Plataforma plataforma = Plataforma.APP;
        //Plataforma plataforma = Plataforma.TOTEM;
        Restaurante restaurante = Restaurante.getInstancia(plataforma);

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

        // === Crear pedidos y agregar productos ===
        // === A ===
        int nroDeOrdenA = restaurante.crearPedidoParaCliente(clientes.getFirst().getEmail());
        restaurante.agregarProductoAlPedido(nroDeOrdenA, "pizza muzzarella", 2);
        restaurante.agregarProductoAlPedido(nroDeOrdenA, "Pizza Napolitana", 1);

        // === B (Pedido para cancelar)===
        int nroDeOrdenB = restaurante.crearPedidoParaCliente(clientes.getFirst().getEmail());
        restaurante.agregarProductoAlPedido(nroDeOrdenB, "Hamburguesa Completa", 2);

        // === C (Pedido programado para dentro de 1 min)===
        LocalDateTime horarioProgramado = LocalDateTime.now().plusSeconds(5);
        int nroDeOrdenC = restaurante.crearPedidoProgramadoParaCliente(clientes.getFirst().getEmail(), horarioProgramado);
        restaurante.agregarProductoAlPedido(nroDeOrdenB, "Pizza Muzzarella", 1);
        restaurante.agregarProductoAlPedido(nroDeOrdenB, "Hamburguesa Completa", 1);

        System.out.println("\n=== Comprobamos creacion pedidoA: ===");
        System.out.println("Total del pedidoA (recien creado) (sin descuento): $" + restaurante.devolverTotalPedido(nroDeOrdenA));
        System.out.println("\n=== Comprobamos creacion pedidoB: ===");
        System.out.println("Total del pedidoB (recien creado) (sin descuento): $" + restaurante.devolverTotalPedido(nroDeOrdenB));
        System.out.println("\n=== Comprobamos creacion pedidoC: ===");
        System.out.println("Total del pedidoC (recien PROGRAMADO) (sin descuento): $" + restaurante.devolverTotalPedido(nroDeOrdenC));

        // === Cancelar pedido B ===
        /* ⚠️ Esta devolviendo Pedido solo para hacer esta prueba, si no deberia ser void ⚠️
            (ROMPE ENCAPSULAMIENTO)
        */
        System.out.println("\n=== Cancelamos pedidoB: ===");
        Pedido pedidoBTest = restaurante.cancelarPedido(nroDeOrdenB);
        System.out.println(pedidoBTest.getEstado().getNombreEstado());
        System.out.println("===============\n");

        // === Crear y agregar personal ===
        restaurante.agregarPersonal();
        List<Personal> empleados = restaurante.getEmpleados();
        System.out.println("\n=== Personal (Mesero) creado: ===");
        System.out.println("Nombre: " + empleados.getFirst().getNombre());
        System.out.println("Legajo/Id: " + empleados.getFirst().getId());
        System.out.println("======\n");

        // === Avanzar estado y notificar ; Agregar productos ===
        System.out.println("=== Intentamos agregar producto a pedidoA (1 Pizza Napo): ===");
        restaurante.agregarProductoAlPedido(nroDeOrdenA, "Pizza Napolitana", 1);
        System.out.println("=============================");
        restaurante.avanzarEstadoPedido("ABC123", nroDeOrdenA); // En preparación
        System.out.println("=== Intentamos agregar producto a pedidoA (1 Pizza Napo): ===");
        restaurante.agregarProductoAlPedido(nroDeOrdenA, "Pizza Napolitana", 1);
        System.out.println("=============================");
        restaurante.avanzarEstadoPedido("ABC123", nroDeOrdenA); // Listo para entregar
        restaurante.avanzarEstadoPedido("ABC123", nroDeOrdenA); // Entregado
        // Descomentar si se quiere probar avanzar pedido entregado:
        //restaurante.avanzarEstadoPedido("ABC123", nroDeOrden);

        // === Pagar y notificar ===
        System.out.println("\n=== Cliente paga pedido: ===");
        restaurante.clientePagarPedido(nroDeOrdenA, clientes.getFirst().getEmail());

        System.out.println("\n=== Logs (Quien avanzo que pedido y de que estado): ===");
        List<Log> logs = restaurante.getLogs();
        for(Log l :logs){
            System.out.println("==========");
            System.out.println("Empleado: " + l.getEmpleado());
            System.out.println("nroOrden: " + l.getNroOrden());
            System.out.println("Avanzo de estado: " + l.getEstadoPedido());
            System.out.println("==========");
        }

        // === Emitir factura ===
        int nroFactura = restaurante.generarFactura(nroDeOrdenA);
        restaurante.mostrarFactura(nroFactura);

        // === Generar y mostrar reporte de ventas ===
        int nroReporte = restaurante.generarReporte();
        restaurante.mostrarReporte(nroReporte);

        try {
            System.out.println("\n=== Estado pedidoC (Pedido que programamos para dentro de 5 segundos): ===");
            System.out.println(restaurante.getPedidos().get(2).getEstado().getNombreEstado());
            System.out.println("=== Estamos esperando 10 segundos antes de terminar el main thread: ===");
            Thread.sleep(10000);
            System.out.println("=== FIN de los 10 secs ===");
            System.out.println("\n=== Comprobamos estado pedidoC (Pedido que programamos para dentro de 5 segundos): ===");
            System.out.println(restaurante.getPedidos().get(2).getEstado().getNombreEstado());
            // Aca se podria mover al pedidoC de estados hasta entregarlo y bla bla bla
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        RelojSistema.getInstance().getScheduler().shutdown();
    }
}
