package main.java.restaurante.app;

import main.java.restaurante.factory.AppFactory;
import main.java.restaurante.factory.PlataformaFactory;
import main.java.restaurante.factory.TotemFactory;
import main.java.restaurante.model.*;
import main.java.restaurante.menu.Producto;
import main.java.restaurante.service.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

public class Restaurante {
    private static Restaurante instancia;
    private final Plataforma plataforma;
    private final PlataformaFactory factory;
    private final GestorPedido gestorPedidos;
    private final GestorClientes gestorClientes;
    private final GestorPersonal gestorPersonal;
    private final GestorMenu gestorMenu;
    private final GestorFactura gestorFactura;
    private final GestorReporte gestorReporte;


    private Restaurante(Plataforma plataforma) {
        if (plataforma == Plataforma.TOTEM) {
            this.factory = new TotemFactory();
        } else {
            this.factory = new AppFactory();
        }
        this.plataforma = plataforma;
        this.gestorPedidos = GestorPedido.getInstancia(factory);
        this.gestorClientes = GestorClientes.getInstancia(factory);
        this.gestorPersonal = GestorPersonal.getInstancia();
        this.gestorMenu = GestorMenu.getInstancia();
        this.gestorFactura = GestorFactura.getInstancia();
        this.gestorReporte = GestorReporte.getInstancia();
    }

    public static Restaurante getInstancia(Plataforma plataforma) {
        if (instancia == null) {
            instancia = new Restaurante(plataforma);
        }
        return instancia;
    }

    public int crearPedidoParaCliente(String email) {
        Pedido pedido = gestorPedidos.crearPedido();
        gestorClientes.asignarPedido(email, pedido);
        return pedido.getNumeroOrden();
    }

    //Devuelve Pedido solo para prueba, si no deberia ser int (nroOrden)
    public Pedido crearPedidoProgramadoParaCliente(String email, LocalDateTime horarioProgramado) {
        Pedido pedido = gestorPedidos.crearPedidoProgramado(horarioProgramado);
        gestorClientes.asignarPedido(email, pedido);
        return pedido;
    }

    //Devuelve Pedido solo para prueba, si no void
    public Pedido cancelarPedido(int nroOrden){
        return gestorPedidos.cancelarPedido(nroOrden);
    }

    public void agregarProductoAlPedido(int numeroOrden, String nombreProducto, int cantidad) {
        Producto producto = gestorMenu.buscarProductoPorNombre(nombreProducto);
        if (producto == null) {
            throw new NoSuchElementException("No se encontró el producto que quiere agregar al pedido " + numeroOrden);
        }
        gestorPedidos.agregarProductoAlPedido(numeroOrden, producto, cantidad);
    }

    public void agregarPersonal() {
        gestorPersonal.agregarEmpleado();
    }

    public void avanzarEstadoPedido(String idEmpleado, int numeroOrden) {
        gestorPersonal.avanzoPedido(idEmpleado, numeroOrden, gestorPedidos.getPedidoById(numeroOrden).getEstado().getNombreEstado());
        gestorPedidos.avanzarEstadoPedido(numeroOrden);
    }

    public Float getTiempoDeEspera(int numeroOrden) {
        return gestorPedidos.getTiempoEsperaPedido(numeroOrden, this.plataforma);
    }

    public BigDecimal devolverTotalPedido(int numeroOrden) {
        return gestorPedidos.getTotalPedido(numeroOrden);
    }

    public void registrarCliente() {
        gestorClientes.registrarCliente();
    }

    public void agregarMedioDePago(String email) {
        gestorClientes.agregarMedioDePago(email);
    }

    public void clientePagarPedido(int numeroOrden, String email) {
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

    public void mostrarFactura(int numeroFactura) {
        gestorFactura.mostrarFacturaPorId(numeroFactura);
    }

    public int generarReporte() {
        // TODO
        /* Aca le pasamos todos los pedidos pero a futuro se podria hacer una seleccion de
        que pedidos queremos generar el reporte */
        return gestorReporte.generarReporte(gestorPedidos.getPedidos());
    }

    public void mostrarReporte(int numeroReporte) {
        gestorReporte.mostrarReportePorId(numeroReporte);
    }

    public List<Cliente> getClientes() {
        return gestorClientes.getClientes();
    }

    public List<Personal> getEmpleados() {
        return gestorPersonal.getEmpleados();
    }

    public List<Log> getLogs() {
        return gestorPersonal.getLogs();
    }

    public List<Pedido> getPedidos() {
        return gestorPedidos.getPedidos();
    }
}