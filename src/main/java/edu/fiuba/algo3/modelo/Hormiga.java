package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Observer.Emisor;

public class Hormiga extends Enemigo{
    private final int CREDITOS_HORMIGA = 1;
    public Hormiga(Pasarela pasarelaInicial) {
        super();
        emisor = new Emisor();
        pasarelaInicial.recibir(this);
        this.poderAtaque = 1;
        this.vida = new Vida(1);
        this.cantidadMovimientos = 1;
        this.posicionActual = pasarelaInicial;
    }
    public void morir(){
        emisor.notificarSubscriptores("log", "Hormiga muere y otorga " + CREDITOS_HORMIGA + " créditos al jugador");
        Jugador.getInstance().registrarHormigaMuerta();
        Jugador.getInstance().recompensar(CREDITOS_HORMIGA, true);
    }

    public String representacionString() {
        return "Hormiga con " + vida.obtenerPuntos() + " puntos de vida";
    }
}
