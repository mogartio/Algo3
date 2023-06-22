package edu.fiuba.algo3.modelo.ObserverPropio;

public interface Subscriptor {
    void notificar(String tipoEvento, String mensaje);
}
