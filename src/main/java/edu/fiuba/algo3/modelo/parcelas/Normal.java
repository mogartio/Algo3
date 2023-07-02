package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.Enemigos.Movimiento.Movimiento;
import javafx.scene.paint.Color;

public class Normal implements TipoPasarela{

    @Override
    public void moverEnemigos(Movimiento tipoMovimiento, Pasarela pasarelaSiguiente) {
        tipoMovimiento.actualizarPosicion(pasarelaSiguiente);
    }

    @Override
    public Construible getConstruible() {
        return new DisponibleTrampa();
    }

    @Override
    public Color getColor() {
        return Color.ORANGE;
    }
}
