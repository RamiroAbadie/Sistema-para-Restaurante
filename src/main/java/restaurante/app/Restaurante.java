package main.java.restaurante.app;

import main.java.restaurante.model.Cliente;
import main.java.restaurante.model.Cupon;
import main.java.restaurante.model.Pedido;
import main.java.restaurante.model.Personal;
import main.java.restaurante.menu.ItemMenu;
import main.java.restaurante.menu.Producto;
import main.java.restaurante.service.*;
import main.java.restaurante.strategy.Notificador;

public class Restaurante {
    private static Restaurante instancia;

    private final GestorPedidos gestorPedidos;
    private final GestorClientes gestorClientes;
    private final GestorPersonal gestorPersonal;
    private final GestorMenu gestorMenu;

    private Restaurante() {
        this.gestorPedidos = GestorPedidos.getInstancia();
        this.gestorClientes = GestorClientes.getInstancia();
        this.gestorPersonal = GestorPersonal.getInstancia();
        this.gestorMenu = GestorMenu.getInstancia();
    }

    public static Restaurante getInstancia() {
        if (instancia == null) {
            instancia = new Restaurante();
        }
        return instancia;
    }

    public Pedido crearPedidoParaCliente(Cliente cliente, int numeroPedido) {
        Pedido pedido = gestorPedidos.crearPedido(numeroPedido);
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

    // Getters
    public GestorPedidos getGestorPedidos() { return gestorPedidos; }
    public GestorClientes getGestorClientes() { return gestorClientes; }
    public GestorPersonal getGestorPersonal() { return gestorPersonal; }
    public GestorMenu getGestorMenu() { return gestorMenu; }
}
