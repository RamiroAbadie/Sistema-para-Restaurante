package main.java.restaurante.model;

import main.java.restaurante.state.EstadoPedido;
import main.java.restaurante.model.Personal;

public class Log {
    private final String idEmpleado;
    private final int nroOrden;
    private final String estadoPedido;

    public Log(String idEmpleado, int nroOrden, String estadoPedido) {
        this.idEmpleado = idEmpleado;
        this.nroOrden = nroOrden;
        this.estadoPedido = estadoPedido;
    }

    public String getEmpleado() {
        return idEmpleado;
    }

    public int getNroOrden() {
        return nroOrden;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }
}
