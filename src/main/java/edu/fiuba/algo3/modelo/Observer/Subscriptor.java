package edu.fiuba.algo3.modelo.Observer;

public interface Subscriptor {
    void notificar(String tipoEvento, String mensaje);
}
