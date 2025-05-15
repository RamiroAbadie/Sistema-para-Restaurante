package main.java.restaurante.service;

import main.java.restaurante.model.Personal;

import java.util.ArrayList;
import java.util.List;

public class GestorPersonal {
    private static GestorPersonal instancia;
    private List<Personal> empleados;

    private GestorPersonal() {
        this.empleados = new ArrayList<>();
    }

    public static GestorPersonal getInstancia() {
        if (instancia == null) {
            instancia = new GestorPersonal();
        }
        return instancia;
    }

    public void agregarEmpleado(Personal empleado) {
        empleados.add(empleado);
    }

    public List<Personal> getEmpleados() {
        return empleados;
    }
}
