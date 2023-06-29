package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Movimiento;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import javafx.scene.paint.Color;

public interface TipoPasarela {
    void moverEnemigos(Movimiento tipoMovimiento, Pasarela pasarelaActual);

    Construible getConstruible();

    Color getColor();
}
