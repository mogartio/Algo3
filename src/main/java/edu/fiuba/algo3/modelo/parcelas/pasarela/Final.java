package edu.fiuba.algo3.modelo.parcelas.pasarela;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;

public class Final implements TipoPasarela {

    public void recibir(Enemigo nuevoEnemigo){
        nuevoEnemigo.dañarJugador();
    }

    public TipoPasarela verSiguiente() { return this; }

    public TipoPasarela verSiguiente(int cantidadPasos) {
        return this;
    }
}
