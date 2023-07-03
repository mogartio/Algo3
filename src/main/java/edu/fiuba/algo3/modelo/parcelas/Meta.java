package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.Enemigos.Movimiento.Movimiento;
import javafx.scene.paint.Color;

public class Meta implements TipoPasarela{
    @Override
    public void moverEnemigos(Movimiento tipoMovimiento, Pasarela pasarelaActual) {
        tipoMovimiento.daniarJugador();
    }

    @Override
    public Construible getConstruible() {
        return new NoDisponible();
    }

    @Override
    public Color getColor() {
        return Color.PINK;
    }
}
