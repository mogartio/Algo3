package edu.fiuba.algo3.modelo.Defensas;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.juego.Juego;

import java.util.ArrayList;

public class TrampaArenosa extends Defensa {
    int duracion;

    public TrampaArenosa() {
        super(0, 0, new Trampa(0.5));
        duracion = 3;
        this.representacionString = "TrampaArenosa";
    }


    @Override
    public void pasarTurno() {
        super.pasarTurno();
        this.duracion = duracion - 1;

        if (duracion == 0) {
            Juego.getInstance().destruirTrampa(this);
        }
    }
}