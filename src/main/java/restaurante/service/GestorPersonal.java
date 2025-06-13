package main.java.restaurante.service;

import main.java.restaurante.model.Log;
import main.java.restaurante.model.Mesero;
import main.java.restaurante.model.Personal;
import main.java.restaurante.state.EstadoPedido;

import java.util.*;

public class GestorPersonal {
    private static GestorPersonal instancia;
    private List<Personal> empleados;

    private List<Log> logs;

    private GestorPersonal() {
        this.empleados = new ArrayList<>();
        this.logs = new ArrayList<>();
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
        Mesero mesero2 = new Mesero("Rocio Mesera", "DEF456");
        empleados.add(mesero);
        empleados.add(mesero2);
    }

    public void avanzoPedido(String idEmpleado, int nroOrden, String estadoPedido){
        Log newLog = new Log(idEmpleado, nroOrden, estadoPedido);
        logs.add(newLog);
    }

    private Personal buscarEmpleadoPorId(String idEmpleado) {
        for (Personal p : empleados) {
            if (p.getId().equalsIgnoreCase(idEmpleado)) {
                return p;
            }
        }
        return null;
    }

    public List<Log> getLogs() {
        return Collections.unmodifiableList(logs);
    }
    public List<Personal> getEmpleados() {
        return Collections.unmodifiableList(empleados);
    }
}
