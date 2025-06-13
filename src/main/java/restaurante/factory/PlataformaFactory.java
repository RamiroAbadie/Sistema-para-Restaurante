package main.java.restaurante.factory;

public interface PlataformaFactory {
    Notificador crearNotificadorEmpleado(TipoNotificador tipo);

    ValidadorCupon crearValidadorCupon();
}
