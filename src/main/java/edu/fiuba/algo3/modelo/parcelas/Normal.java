package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;

public class Normal implements TipoPasarela{
    @Override
    public void moverEnemigos(Enemigo enemigo, Pasarela pasarelaSiguiente) {
        enemigo.actualizarPosicionActual(pasarelaSiguiente);
    }
}
