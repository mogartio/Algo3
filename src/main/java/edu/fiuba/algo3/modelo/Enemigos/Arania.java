package edu.fiuba.algo3.modelo.Enemigos;

import edu.fiuba.algo3.modelo.Defensas.TipoDeDefensa;
import edu.fiuba.algo3.modelo.Enemigos.Movimiento.MovimientoPasarela;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.miscelanea.RandomGenerator;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;


public class Arania extends Enemigo {
    private final RandomGenerator generadorRandom;
    private String sonido = "Avanzar";

    public Arania(RandomGenerator generadorRandom) {
        super(2, 2, 2);
        this.tipoMovimiento = new MovimientoPasarela(this);

        this.generadorRandom = generadorRandom;
        this.representacionString = "Araña";
    }

    public Arania(Pasarela posicionActual,RandomGenerator generadorRandom) { //Constructor para test
        super(2, 2, 2);

        this.tipoMovimiento = new MovimientoPasarela(this);
        this.tipoMovimiento.actualizarPosicion(posicionActual);
        this.generadorRandom = generadorRandom;
    }

    @Override
    public String verSonido() {
        return sonido;
    }

    public void morir(){
        int cantidadARecompensar = generadorRandom.obtenerUnNumero();
        //this.emisor.notificarSubscriptores("log", "Araña muere y otorga " + cantidadARecompensar + " créditos al jugador");
        Juego.getInstance().recompensarJugador(cantidadARecompensar); // devuelve int entre 0 y 10
        sonido = "Morir";
        setChanged();
    }

    @Override
    public boolean esVisiblePara(TipoDeDefensa tipo){
        return true;
    }
}
