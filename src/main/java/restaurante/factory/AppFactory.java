package main.java.restaurante.factory;

public class AppFactory implements PlataformaFactory {
    @Override
    public Notificador crearNotificadorEmpleado(TipoNotificador tipo) {
        if (tipo == TipoNotificador.APP) {
            return new NotificadorApp();
        }
        else if (tipo == TipoNotificador.EMAIL) {
            return new NotificadorEmail();
        }
        else if (tipo == TipoNotificador.SMS) {
            return new NotificadorSMS();
        }
        else {
            // ⚠️ NO BORRAR
            //No deberia llegar aca nunca, pero si sacas esto no compila (ni idea porque xd)
            throw new IllegalArgumentException("Tipo de notificador no soportado: " + tipo);
        }
    }

    @Override
    public ValidadorCupon crearValidadorCupon() {
        return null;
    }
}
