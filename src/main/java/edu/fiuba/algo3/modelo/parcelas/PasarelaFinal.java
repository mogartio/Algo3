package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;

public class PasarelaFinal extends Pasarela {

    public PasarelaFinal(Coordenada coordenada) {
        super(coordenada, null);
    }

    public void recibir(Enemigo nuevoEnemigo){
        nuevoEnemigo.dañarJugador();
    }

    public Pasarela verSiguiente() { return this; }

    public Pasarela verSiguiente(int cantidadPasos) {
        return this;
    }
}
