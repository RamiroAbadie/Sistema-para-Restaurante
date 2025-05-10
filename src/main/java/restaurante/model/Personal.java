package main.java.restaurante.model;

public abstract class Personal {
    private String nombre;

    public Personal(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() { return nombre; }

    public void avanzarPedido(Pedido pedido) {
        pedido.avanzarEstado();
    }

    public void atenderPedido(Pedido pedido) {
        // lógica opcional según rol
    }
}
