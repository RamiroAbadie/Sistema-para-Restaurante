package main.java.restaurante.factory;

import java.util.List;

public class ValidadorCuponReal implements ValidadorCupon{
    private List<Integer> codigosValidos;
    @Override
    public boolean validar(Integer codigo) {
        return this.codigosValidos.contains(codigo);
    }

    @Override
    public void agregarCuponValido(Integer codigo) {
        this.codigosValidos.add(codigo);
    }
}
