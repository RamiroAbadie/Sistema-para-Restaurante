package main.java.restaurante.model;

public abstract class Personal {
    private String nombre;

    private final String id;

    public Personal(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
    }

    public void avanzarPedido(Pedido pedido) {
        //TODO: Aca iria logica segun rol
    }

    public void atenderPedido(Pedido pedido) {
        // lógica opcional según rol
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }
}
