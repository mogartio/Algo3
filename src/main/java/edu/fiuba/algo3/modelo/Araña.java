package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Observer.Emisor;

import java.util.concurrent.ThreadLocalRandom;

// nextInt is normally exclusive of the top value,
// so add 1 to make it inclusive


public class Araña extends Enemigo {

    private final int MINIMO = 0;
    private final int MAXIMO = 10;
    public Araña(Pasarela pasarelaInicial) {
        super();
        pasarelaInicial.recibir(this);
        this.vida = new Vida(2);
        this.poderAtaque = 2;
        this.cantidadMovimientos = 2;
        this.posicionActual = pasarelaInicial;
    }
    public void morir(){
        int cantidadARecompensar = ThreadLocalRandom.current().nextInt(MINIMO, MAXIMO + 1);
        emisor.notificarSubscriptores("log", "Araña muere y otorga " + cantidadARecompensar + " créditos al jugador");
        Jugador.getInstance().recompensar(cantidadARecompensar, false); // devuelve int entre 0 y 10
    }

    public String representacionString() {
        return "Araña con " + vida.obtenerPuntos() + " puntos de vida";
    }
}
