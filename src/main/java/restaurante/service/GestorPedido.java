package main.java.restaurante.service;

import main.java.restaurante.app.Plataforma;
import main.java.restaurante.factory.Notificador;
import main.java.restaurante.factory.PlataformaFactory;
import main.java.restaurante.factory.TipoNotificador;
import main.java.restaurante.model.Pedido;
import main.java.restaurante.menu.Producto;
import main.java.restaurante.observer.RelojSistema;
import main.java.restaurante.observer.PedidoProgramadoObserver;
import main.java.restaurante.state.EnEspera;
import main.java.restaurante.state.Entregado;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class GestorPedido {
    private static GestorPedido instancia;

    private final PlataformaFactory factory;
    private List<Pedido> pedidos;
    private Notificador notificador;

    private GestorPedido(PlataformaFactory factory) {
        this.pedidos = new ArrayList<>();
        this.factory = factory;
        // TODO
        /* Aca hay que ver si hacer una lista de notificadores en los que
        se puedan guardar diferentes tipos de notificadores, por el momento
        solo se puede uno que esta harcodeado aca (se puede cambiar el tipo
        cambiando el Enum) */
        this.notificador = factory.crearNotificadorEmpleado(TipoNotificador.APP);
    }

    public static GestorPedido getInstancia(PlataformaFactory factory) {
        if (instancia == null) {
            instancia = new GestorPedido(factory);
        }
        return instancia;
    }

    public Pedido crearPedido() {
        Pedido pedido = new Pedido(new EnEspera());
        pedidos.add(pedido);
        return pedido;
    }

    public Pedido crearPedidoProgramado(LocalDateTime horarioProgrmado) {
        Pedido pedido = new Pedido(horarioProgrmado);
        this.pedidos.add(pedido);
        PedidoProgramadoObserver observer = new PedidoProgramadoObserver(pedido);
        RelojSistema.getInstance().agregarObservador(observer);
        return pedido;
    }

    public void avanzarEstadoPedido(int numeroOrden) {
        Pedido pedido = buscarPedidoPorId(numeroOrden);
        if (pedido == null) {
            throw new NoSuchElementException("No se encontró el pedido solicitado.");
        }
        if (pedido.getEstado() instanceof Entregado) {
            throw new IllegalStateException("No se puede avanzar un pedido ya entregado.");
        }
        pedido.avanzarEstado();
        notificador.enviarNotificacionAvancePedido(pedido);
    }

    public Pedido cancelarPedido(int numeroOrden) {
        Pedido pedido = buscarPedidoPorId(numeroOrden);
        if (pedido == null) {
            throw new NoSuchElementException("No se encontró el pedido solicitado.");
        }
        return pedido.cancelar();
    }

    public void agregarProductoAlPedido(int numeroOrden, Producto producto, int cantidad) {
        Pedido pedido = buscarPedidoPorId(numeroOrden);
        if (pedido == null) {
            throw new NoSuchElementException("No se encontró el pedido solicitado.");
        }
        if (pedido.getEstado() instanceof Entregado) {
            throw new IllegalStateException("No se puede modificar un pedido ya entregado.");
        }
        pedido.agregarProducto(producto, cantidad);
    }

    public Float getTiempoEsperaPedido(int numeroOrden, Plataforma plataforma) {
        Optional<Pedido> optionalPedido = pedidos.stream()
                .filter(p -> p.getNumeroOrden() == numeroOrden)
                .findFirst();

        if (optionalPedido.isEmpty()) {
            throw new IllegalArgumentException("Pedido no encontrado con número de orden: " + numeroOrden);
        }

        Pedido pedido = optionalPedido.get();
        int cantidadTotalPedidos = pedidos.size();
        return pedido.getTiempoEspera(cantidadTotalPedidos, plataforma);
    }


    public BigDecimal getTotalPedido(int numeroOrden) {
        Pedido pedido = buscarPedidoPorId(numeroOrden);
        if (pedido == null) {
            throw new NoSuchElementException("No se encontró el pedido solicitado.");
        }
        if (pedido.getEstado() instanceof Entregado) {
            throw new IllegalStateException("No se puede modificar un pedido ya entregado.");
        }
        return pedido.calcularTotal();
    }

    public Pedido getPedidoById(int numeroOrden) {
        Pedido pedido = buscarPedidoPorId(numeroOrden);
        if (pedido == null) {
            throw new NoSuchElementException("No se encontró el pedido solicitado.");
        }
        return pedido;
    }

    private Pedido buscarPedidoPorId(int numeroOrden) {
        for (Pedido p : pedidos) {
            if (p.getNumeroOrden() == numeroOrden) {
                return p;
            }
        }
        return null;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}
