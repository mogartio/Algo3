package edu.fiuba.algo3.modelo.parcelas.pasarela;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;

public interface TipoPasarela {

    public abstract TipoPasarela siguiente(Pasarela siguiente);

    public abstract void recibir(Enemigo nuevoEnemigo);
}
