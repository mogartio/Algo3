package edu.fiuba.algo3.modelo.Enemigos;

import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;

import java.util.concurrent.ThreadLocalRandom;

// nextInt is normally exclusive of the top value,
// so add 1 to make it inclusive


public class Arania extends Enemigo {

    private final int MINIMO = 0;
    private final int MAXIMO = 10;
    public Arania() {
        super(2, 2, 2);
    }

    public Arania(Pasarela posicionActual) { //Constructor para test
        super(2, 2, 2);
        this.posicionActual = posicionActual;
    }

    public void morir(){
        int cantidadARecompensar = ThreadLocalRandom.current().nextInt(MINIMO, MAXIMO + 1);
        this.emisor.notificarSubscriptores("log", "Araña muere y otorga " + cantidadARecompensar + " créditos al jugador");
        Jugador.getInstance().recompensar(cantidadARecompensar, false); // devuelve int entre 0 y 10
    }

    public String representacionString() {
        return "Araña";
    }
}
