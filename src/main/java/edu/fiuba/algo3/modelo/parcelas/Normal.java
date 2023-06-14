package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;

public class Normal implements TipoPasarela{
    @Override
    public void moverEnemigos(Enemigo enemigo, Pasarela pasarelaSiguiente) {
        enemigo.actualizarPosicionActual(pasarelaSiguiente);
        pasarelaSiguiente.actualizarUbicacionAlEnemigo(enemigo);

    }
}
