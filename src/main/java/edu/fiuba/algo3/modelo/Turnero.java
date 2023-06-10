package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.juego.Juego;

import java.util.ArrayList;

public class Turnero implements Jugable{

    private Juego juego;
    int contadorTurnos;

    public Turnero(Juego juego) {
        this.juego = juego;
        contadorTurnos = 0;
    }

    public void jugar() {
        while (!juego.finalizado()) {
            juego.jugarTurno(contadorTurnos);
            contadorTurnos++;
        }
    }

   /* public void proximoTurno() {
        ArrayList<Enemigo>
    }*/

}
