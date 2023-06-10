package edu.fiuba.algo3.modelo.parcelas.pasarela;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;

public class Intermedia implements TipoPasarela {

    public abstract TipoPasarela verSiguiente();

    public abstract TipoPasarela verSiguiente(int cantidadPasos);

    public abstract void recibir(Enemigo nuevoEnemigo);
}
