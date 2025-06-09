package main.java.restaurante.model;

import main.java.restaurante.state.EstadoPedido;
import main.java.restaurante.model.Personal;

public class Log {
    final Personal empleado;
    final int nroOrden;
    final EstadoPedido estadoPedido;

    public Log(Personal empleado, int nroOrden, EstadoPedido estadoPedido) {
        this.empleado = empleado;
        this.nroOrden = nroOrden;
        this.estadoPedido = estadoPedido;
    }

    public String getEmpleado() {
        return empleado.getId();
    }

    public int getNroOrden() {
        return nroOrden;
    }

    public String getEstadoPedido() {
        return estadoPedido.getNombreEstado();
    }
}
