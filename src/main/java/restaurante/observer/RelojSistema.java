package main.java.restaurante.observer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RelojSistema {
    private static RelojSistema instancia;
    private List<PedidoObserver> observadores = new ArrayList<>();
    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    private RelojSistema() {
        // Cada 1 minuto
        scheduler.scheduleAtFixedRate(this::notificarObservadores, 0, 1, TimeUnit.MINUTES);
    }

    public static RelojSistema getInstance() {
        if (instancia == null) {
            instancia = new RelojSistema();
        }
        return instancia;
    }

    public void agregarObservador(PedidoObserver obs) {
        observadores.add(obs);
    }

    public void eliminarObservador(PedidoObserver obs) {
        observadores.remove(obs);
    }

    private void notificarObservadores() {
        LocalDateTime ahora = LocalDateTime.now();
        for (PedidoObserver obs : observadores) {
            obs.notificar(ahora);
        }
    }
}
