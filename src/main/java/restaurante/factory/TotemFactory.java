package main.java.restaurante.factory;

public class TotemFactory implements PlataformaFactory {
    @Override
    public Notificador crearNotificadorEmpleado(TipoNotificador tipo) {
        return new NotificadorNulo();
    }

    @Override
    public ValidadorCupon crearValidadorCupon() {
        return new ValidadorCuponNulo();
    }
}
