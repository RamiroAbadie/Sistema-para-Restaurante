package main.java.restaurante.service;

import main.java.restaurante.model.Personal;

import java.util.ArrayList;
import java.util.List;

public class GestorPersonal {
    private List<Personal> empleados;

    public GestorPersonal() {
        this.empleados = new ArrayList<>();
    }

    public void agregarEmpleado(Personal empleado) {
        empleados.add(empleado);
    }

    public List<Personal> getEmpleados() {
        return empleados;
    }
}
