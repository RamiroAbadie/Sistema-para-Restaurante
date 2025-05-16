package main.java.restaurante.app;

import main.java.restaurante.model.*;
import main.java.restaurante.menu.ItemMenu;
import main.java.restaurante.menu.Producto;
import main.java.restaurante.service.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

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

    public int crearPedidoParaCliente(String email) {
        Pedido pedido = gestorPedidos.crearPedido();
        gestorClientes.asignarPedido(email, pedido);
        return pedido.getNumeroOrden();
    }

    public void agregarProductoAlPedido(int numeroOrden, String nombreProducto, int cantidad) {
        Producto producto = gestorMenu.buscarProductoPorNombre(nombreProducto);
        if (producto == null) {
            throw new NoSuchElementException("No se encontró el producto que quiere agregar al pedido " + numeroOrden);
        }
        gestorPedidos.agregarProductoAlPedido(numeroOrden, producto, cantidad);
    }

    public void agregarPersonal(){
        gestorPersonal.agregarEmpleado();
    }

    public void avanzarEstadoPedido(String nombrePersonal, int numeroOrden) {
        gestorPersonal.avanzoPedido(nombrePersonal, numeroOrden);
        gestorPedidos.avanzarEstadoPedido(numeroOrden);
    }

    public BigDecimal devolverTotalPedido(int numeroOrden){
        return gestorPedidos.getTotalPedido(numeroOrden);
    }

    public void registrarCliente() {
        gestorClientes.registrarCliente();
    }

    public void agregarMedioDePago(String email) {gestorClientes.agregarMedioDePago(email);}

    public void clientePagarPedido(int numeroOrden, String email){
        gestorClientes.clientePagarPedido(gestorPedidos.getPedidoById(numeroOrden), email);
    }

    public void asignarCupon(String email) {
        gestorClientes.asignarCupon(email);
    }


    public void agregarItemAlMenu() {
        gestorMenu.agregarItemAlMenu();
    }

    public void mostrarMenu() {
        gestorMenu.mostrarMenu();
    }

    public int generarFactura(int numeroOrden) {
        return gestorFactura.generarFactura(gestorPedidos.getPedidoById(numeroOrden));
    }

    public void mostrarFactura(int numeroFactura){
        gestorFactura.mostrarFacturaPorId(numeroFactura);
    }

    public int generarReporte() {
        // TODO
        /* Aca le pasamos todos los pedidos pero a futuro se podria hacer una seleccion de
        que pedidos queremos generar el reporte */
        return gestorReporte.generarReporte(gestorPedidos.getPedidos());
    }

    public void mostrarReporte(int numeroReporte){
        gestorReporte.mostrarReportePorId(numeroReporte);
    }

    public List<Cliente> getClientes() {
        return gestorClientes.getClientes();
    }

    public List<ItemMenu> getItemMenu() {
        return gestorMenu.getItems();
    }

    public List<Personal> getEmpleados(){
        return gestorPersonal.getEmpleados();
    }

}
