package main.java.restaurante.service;

import main.java.restaurante.factory.Notificador;
import main.java.restaurante.factory.PlataformaFactory;
import main.java.restaurante.factory.TipoNotificador;
import main.java.restaurante.factory.ValidadorCupon;
import main.java.restaurante.model.Cliente;
import main.java.restaurante.model.Cupon;
import main.java.restaurante.model.Pedido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class GestorClientes {
    private static GestorClientes instancia;

    private final PlataformaFactory factory;
    private final Notificador notificador;
    private final ValidadorCupon validadorCupon;
    private static List<Cliente> clientes;

    private GestorClientes(PlataformaFactory factory) {
        this.clientes = new ArrayList<>();
        this.factory = factory;
        validadorCupon = factory.crearValidadorCupon();
        // TODO
        /* Aca hay que ver si hacer una lista de notificadores en los que
        se puedan guardar diferentes tipos de notificadores, por el momento
        solo se puede uno que esta harcodeado aca (se puede cambiar el tipo
        cambiando el Enum) */
        this.notificador = factory.crearNotificadorEmpleado(TipoNotificador.EMAIL);

    }

    public static GestorClientes getInstancia(PlataformaFactory factory) {
        if (instancia == null) {
            instancia = new GestorClientes(factory);
        }
        return instancia;
    }

    public void registrarCliente() {
        //TODO: Aca se deberia pedir al usuario ingresar los datos del cliente a registar?
        Cliente cliente = new Cliente("Lionel Messi", "LeoMessi@InterMiami.com");
        clientes.add(cliente);
    }

    public void agregarMedioDePago(String email){
        Cliente cliente = buscarClientePorEmail(email);
        if (cliente == null) {
            throw new NoSuchElementException("No se encontró el cliente solicitado.");
        }
        cliente.agregarMedioDePago();
    }

    public void asignarCupon(String email) {
        Cliente cliente = buscarClientePorEmail(email);
        if (cliente == null) {
            throw new NoSuchElementException("No se encontró el cliente solicitado.");
        }
        // TODO: Aca estaria la logica de creacion de un cupon
        Cupon cupon = new Cupon(50001, 0.15);
        validadorCupon.agregarCuponValido(cupon.getCodigo());
        cliente.asignarCupon(cupon);
    }

    public void asignarPedido(String email, Pedido pedido) {
        Cliente cliente = buscarClientePorEmail(email);
        if (cliente == null) {
            throw new NoSuchElementException("No se encontró el cliente solicitado.");
        }
        cliente.asignarPedido(pedido);
    }

    public void clientePagarPedido(Pedido pedido, String email){
        Cliente cliente = buscarClientePorEmail(email);
        if (cliente == null) {
            throw new NoSuchElementException("No se encontró el cliente solicitado.");
        }
        cliente.pagarPedido(pedido, validadorCupon);
    }

    private Cliente buscarClientePorEmail(String email) {
        for (Cliente c : clientes) {
            if (c.getEmail().equals(email)) {
                return c;
            }
        }
        return null;
    }

    public List<Cliente> getClientes() {
        return Collections.unmodifiableList(clientes);
    }
}
