package main.java.restaurante.factory;

public class ValidadorCuponNulo implements ValidadorCupon{
    @Override
    public boolean validar(Integer codigo) {
        return false;
    }

    @Override
    public void agregarCuponValido(Integer codigo) {
        // No hace nada
    }
}
