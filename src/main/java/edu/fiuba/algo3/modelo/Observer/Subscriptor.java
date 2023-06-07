package edu.fiuba.algo3.modelo.Observer;

public interface Subscriptor {
    public void notificar(String tipoEvento, String mensaje);
}
