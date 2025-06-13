package main.java.restaurante.factory;

public interface ValidadorCupon {
    boolean validar(Integer codigo);
    void agregarCuponValido(Integer codigo);
}
