package edu.fiuba.algo3.modelo.parcelas.pasarela;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;

public class Inicial implements TipoPasarela {

    public TipoPasarela verSiguiente() {}

    public TipoPasarela verSiguiente(int cantidadPasos);

    public void recibir(Enemigo nuevoEnemigo);
}
