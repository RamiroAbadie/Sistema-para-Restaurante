package main.java.restaurante.service;

import main.java.restaurante.model.Cliente;
import main.java.restaurante.model.Cupon;
import main.java.restaurante.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class GestorClientes {
    private List<Cliente> clientes;

    public GestorClientes() {
        this.clientes = new ArrayList<>();
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
