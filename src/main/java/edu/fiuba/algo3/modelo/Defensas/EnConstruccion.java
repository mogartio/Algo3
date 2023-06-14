package edu.fiuba.algo3.modelo.Defensas;

import edu.fiuba.algo3.modelo.Observer.Emisor;

public class EnConstruccion implements EstadoConstruccion{
    private int turnosHastaTerminar;
    public EnConstruccion(int turnosHastaTerminar) { this.turnosHastaTerminar = turnosHastaTerminar; }

    public EstadoConstruccion pasoUnTurno(Emisor emisor, Defensa defensa){
        if (turnosHastaTerminar - 1 <= 0){
            emisor.notificarSubscriptores("log", "La defensa " + defensa.representationString() + "se termino de construir");
            return new Construido();
        }

        return new EnConstruccion(turnosHastaTerminar - 1);

    }
    public boolean estoyConstruida(){
        return false;
    }
}