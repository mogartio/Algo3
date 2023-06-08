package edu.fiuba.algo3.modelo.Defensas;

public interface EstadoConstruccion {
    public EstadoConstruccion pasoUnTurno();
    public boolean estoyConstruida();
}