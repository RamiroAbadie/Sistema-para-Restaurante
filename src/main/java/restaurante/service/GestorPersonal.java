package main.java.restaurante.service;

import main.java.restaurante.model.Mesero;
import main.java.restaurante.model.Personal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class GestorPersonal {
    private static GestorPersonal instancia;
    private List<Personal> empleados;

    private Set<String> logs;

    private GestorPersonal() {
        this.empleados = new ArrayList<>();
    }

    public static GestorPersonal getInstancia() {
        if (instancia == null) {
            instancia = new GestorPersonal();
        }
        return instancia;
    }

    public void agregarEmpleado() {
        //TODO: Aca se deberia pedir al usuario ingresar los datos del personal a registar?
        Mesero mesero = new Mesero("Juan Mesero", "ABC123");
        empleados.add(mesero);
    }

    public void avanzoPedido(String nombreEmpleado, int nroOrden){
        //TODO Aca se buscaria al empleado y se agregaria a un log junto con el pedido que avanzo
    }

    private Personal buscarEmpleadoPorId(String id) {
        for (Personal p : empleados) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    public List<Personal> getEmpleados() {
        return Collections.unmodifiableList(empleados);
    }
}
