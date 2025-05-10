package main.java.restaurante.app;

import main.java.restaurante.menu.*;
import main.java.restaurante.model.*;
import main.java.restaurante.strategy.*;

import java.math.BigDecimal;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Restaurante restaurante = Restaurante.getInstancia();

        // === Crear y registrar cliente ===
        Cliente cliente = new Cliente("Lionel Messi", "LeoMessi@InterMiami.com");
        restaurante.registrarCliente(cliente);

        // === Crear productos y agregarlos al menú ===
        Producto pizza = new Producto("Pizza", "Muzzarella", BigDecimal.valueOf(3500), Set.of("gluten", "lactosa"));
        Producto ensalada = new Producto("Ensalada César", "Con pollo", BigDecimal.valueOf(2800), Set.of("huevo"));

        CategoriaMenu categoriaPlatos = new CategoriaMenu("Platos principales");
        categoriaPlatos.agregarItem(pizza);
        categoriaPlatos.agregarItem(ensalada);

        restaurante.agregarItemAlMenu(categoriaPlatos);
        restaurante.mostrarMenu();

        // === Crear medio de pago y cupón ===
        MedioDePago tarjeta = new TarjetaCredito("1234-5678-0000", "Lionel M.");
        cliente.agregarMedioDePago(tarjeta);

        Cupon cupon = new Cupon(0.15); // 15% de descuento
        restaurante.asignarCupon(cliente, cupon);

        // === Crear pedido y agregar productos ===
        Pedido pedido = restaurante.crearPedidoParaCliente(cliente, 1);
        restaurante.agregarProductoAlPedido(pedido, pizza, 2);
        restaurante.agregarProductoAlPedido(pedido, ensalada, 1);

        System.out.println("\nTotal del pedido (sin descuento): $" + pedido.calcularTotal());

        // === Pagar y notificar ===
        Notificador notificador = new NotificadorApp();
        cliente.pagarPedido(pedido, tarjeta, notificador);

        // === Avanzar estado y notificar ===
        restaurante.avanzarEstadoPedido(pedido, notificador); // En preparación
        restaurante.avanzarEstadoPedido(pedido, notificador); // Listo para entregar
        restaurante.avanzarEstadoPedido(pedido, notificador); // Entregado
        restaurante.avanzarEstadoPedido(pedido, notificador); // No puede avanzar más
        //TODO
        // Vuelve a mandar una notificacion de entregado, no se porque, revisar
    }
}
