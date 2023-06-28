package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Movimiento;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;

public class Meta implements TipoPasarela{
    @Override
    public void moverEnemigos(Movimiento tipoMovimiento, Pasarela pasarelaActual) {
        tipoMovimiento.daniarJugador();
    }

}
