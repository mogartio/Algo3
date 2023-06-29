package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Movimiento;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;

import java.util.Map;

public class Normal implements TipoPasarela{

    @Override
    public void moverEnemigos(Movimiento tipoMovimiento, Pasarela pasarelaSiguiente) {
        tipoMovimiento.actualizarPosicion(pasarelaSiguiente);
    }
}
