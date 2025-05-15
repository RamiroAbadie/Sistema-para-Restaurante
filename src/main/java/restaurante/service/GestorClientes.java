package main.java.restaurante.service;

import main.java.restaurante.app.Restaurante;
import main.java.restaurante.model.Cliente;
import main.java.restaurante.model.Cupon;
import main.java.restaurante.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class GestorClientes {
    private static GestorClientes instancia;
    private List<Cliente> clientes;

    private GestorClientes() {
        this.clientes = new ArrayList<>();
    }

    public static GestorClientes getInstancia() {
        if (instancia == null) {
            instancia = new GestorClientes();
        }
        return instancia;
    }

    public void registrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void asignarCupon(Cliente cliente, Cupon cupon) {
        cliente.asignarCupon(cupon);
    }

    public void asignarPedido(Cliente cliente, Pedido pedido) {
        cliente.asignarPedido(pedido);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
}
