package edu.fiuba.algo3.modelo.Defensas;

import edu.fiuba.algo3.modelo.Observer.Emisor;

public interface EstadoConstruccion {
    public EstadoConstruccion pasoUnTurno(Emisor emisor, Defensa defensa);
    public boolean estoyConstruida();
}