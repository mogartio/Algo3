package edu.fiuba.algo3.modelo.Defensas;

import edu.fiuba.algo3.modelo.Observer.Emisor;

public class Construido implements EstadoConstruccion {
    public EstadoConstruccion pasoUnTurno(Emisor emisor, Defensa defensa){
        return new Construido();
    }

    public boolean estoyConstruida(){
        return true;
    }
}