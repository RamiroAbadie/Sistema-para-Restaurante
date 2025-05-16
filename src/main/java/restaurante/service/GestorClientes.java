package main.java.restaurante.service;

import main.java.restaurante.app.Restaurante;
import main.java.restaurante.model.Cliente;
import main.java.restaurante.model.Cupon;
import main.java.restaurante.model.Pedido;
import main.java.restaurante.model.Personal;
import main.java.restaurante.state.Entregado;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class GestorClientes {
    private static GestorClientes instancia;
    private static List<Cliente> clientes;

    private GestorClientes() {
        this.clientes = new ArrayList<>();
    }

    public static GestorClientes getInstancia() {
        if (instancia == null) {
            instancia = new GestorClientes();
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
        cliente.asignarCupon();
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
        cliente.pagarPedido(pedido);
    }

    private Cliente buscarClientePorEmail(String email) {
        for (Cliente c : clientes) {
            if (c.getEmail().equals(email)) {
                return c;
            }
        }
        return null;
    }

    public static List<Cliente> getClientes() {
        return Collections.unmodifiableList(clientes);
    }
}
