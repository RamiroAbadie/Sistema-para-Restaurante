package main.java.restaurante.app;

import main.java.restaurante.model.*;
import main.java.restaurante.menu.ItemMenu;
import main.java.restaurante.menu.Producto;
import main.java.restaurante.service.*;
import main.java.restaurante.strategy.Notificador;

public class Restaurante {
    private static Restaurante instancia;

    private final GestorPedido gestorPedidos;
    private final GestorClientes gestorClientes;
    private final GestorPersonal gestorPersonal;
    private final GestorMenu gestorMenu;
    private final GestorFactura gestorFactura;
    private final GestorReporte gestorReporte;


    private Restaurante() {
        this.gestorPedidos = GestorPedido.getInstancia();
        this.gestorClientes = GestorClientes.getInstancia();
        this.gestorPersonal = GestorPersonal.getInstancia();
        this.gestorMenu = GestorMenu.getInstancia();
        this.gestorFactura = GestorFactura.getInstancia();
        this.gestorReporte = GestorReporte.getInstancia();
    }

    public static Restaurante getInstancia() {
        if (instancia == null) {
            instancia = new Restaurante();
        }
        return instancia;
    }

    public Pedido crearPedidoParaCliente(Cliente cliente) {
        Pedido pedido = gestorPedidos.crearPedido();
        gestorClientes.asignarPedido(cliente, pedido);
        return pedido;
    }

    public void agregarProductoAlPedido(Pedido pedido, Producto producto, int cantidad) {
        gestorPedidos.agregarProductoAlPedido(pedido, producto, cantidad);
    }

    public void avanzarEstadoPedido(Pedido pedido, Notificador notificador) {
        gestorPedidos.avanzarEstadoPedido(pedido, notificador);
    }

    public void registrarCliente(Cliente cliente) {
        gestorClientes.registrarCliente(cliente);
    }

    public void asignarCupon(Cliente cliente, Cupon cupon) {
        gestorClientes.asignarCupon(cliente, cupon);
    }

    public void agregarEmpleado(Personal empleado) {
        gestorPersonal.agregarEmpleado(empleado);
    }

    public void agregarItemAlMenu(ItemMenu item) {
        gestorMenu.agregarItemAlMenu(item);
    }

    public void mostrarMenu() {
        gestorMenu.mostrarMenu();
    }

    public Factura generarFactura(Pedido pedido) {
        return getGestorFactura().generarFactura(pedido);
    }

    public Reporte generarReporte() {
        /* Aca le pasamos todos los pedidos pero a futuro se podria hacer una seleccion de
        que pedidos queremos generar el reporte */
        return getGestorReporte().generarReporte(gestorPedidos.getPedidos());
    }


    public GestorPedido getGestorPedidos() { return gestorPedidos; }
    public GestorClientes getGestorClientes() { return gestorClientes; }
    public GestorPersonal getGestorPersonal() { return gestorPersonal; }
    public GestorMenu getGestorMenu() { return gestorMenu; }
    public GestorFactura getGestorFactura() {
        return GestorFactura.getInstancia();
    }

    public GestorReporte getGestorReporte() {
        return GestorReporte.getInstancia();
    }

}
