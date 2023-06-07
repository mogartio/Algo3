package edu.fiuba.algo3.modelo.Observer;

public class Logger implements Subscriptor{

    private int mensajesObservados = 0;
    public void notificar(String tipoEvento, String mensaje) {
        if(tipoEvento.equals("log")) {
            System.out.println(mensaje);
            mensajesObservados++;
        }
    }

    public boolean verificarCantidadDeMensajesObservados(int cantidad) {
        return mensajesObservados == cantidad;
    }
}
