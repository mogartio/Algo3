package edu.fiuba.algo3.modelo.Enemigos;

import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.miscelanea.RandomGenerator;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;

import java.util.concurrent.ThreadLocalRandom;


public class Arania extends Enemigo {
    private RandomGenerator generadorRandom;

    public Arania(RandomGenerator generadorRandom) {
        super(2, 2, 2);
        this.tipoMovimiento = new MovimientoPasarela(this);

        this.generadorRandom = generadorRandom;
    }

    public Arania(Pasarela posicionActual,RandomGenerator generadorRandom) { //Constructor para test
        super(2, 2, 2);

        this.tipoMovimiento = new MovimientoPasarela(this);
        this.tipoMovimiento.actualizarPosicion(posicionActual);
        this.generadorRandom = generadorRandom;

    }

    public void morir(){
        int cantidadARecompensar = generadorRandom.obtenerUnNumero();
        this.emisor.notificarSubscriptores("log", "Araña muere y otorga " + cantidadARecompensar + " créditos al jugador");
        Jugador.getInstance().recompensar(cantidadARecompensar); // devuelve int entre 0 y 10
    }

    public String representacionString() {
        return "Araña";
    }
}
