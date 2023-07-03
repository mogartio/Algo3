package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.Enemigos.Movimiento.Movimiento;
import javafx.scene.paint.Color;

public interface TipoPasarela {
    void moverEnemigos(Movimiento tipoMovimiento, Pasarela pasarelaActual);

    Construible getConstruible();

    Color getColor();
}
