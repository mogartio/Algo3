package edu.fiuba.algo3.modelo.Enemigos;

import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.miscelanea.RandomGenerator;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;

import java.util.concurrent.ThreadLocalRandom;

// nextInt is normally exclusive of the top value,
// so add 1 to make it inclusive


public class Arania extends Enemigo {
    private RandomGenerator generadorRandom;

    public Arania(RandomGenerator generadorRandom) {
        super(2, 2, 2);
<<<<<<< HEAD
        this.tipoMovimiento = new MovimientoPasarela(this);
=======
        this.generadorRandom = generadorRandom;
>>>>>>> 829fc41bf286f6e0365c850723bf592e74f5011b
    }

    public Arania(Pasarela posicionActual,RandomGenerator generadorRandom) { //Constructor para test
        super(2, 2, 2);
<<<<<<< HEAD
        this.tipoMovimiento = new MovimientoPasarela(this);
        this.tipoMovimiento.actualizarPosicion(posicionActual);
=======
        this.posicionActual = posicionActual;
        this.generadorRandom = generadorRandom;
>>>>>>> 829fc41bf286f6e0365c850723bf592e74f5011b
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
