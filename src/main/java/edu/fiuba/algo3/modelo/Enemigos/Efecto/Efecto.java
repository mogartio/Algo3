package edu.fiuba.algo3.modelo.Enemigos.Efecto;

import edu.fiuba.algo3.modelo.Enemigos.Movimiento.Movimiento;
import edu.fiuba.algo3.modelo.Excepciones.PasarelaInexistente;
import edu.fiuba.algo3.modelo.juego.Mapa;

public interface Efecto {
    Efecto avanzar(int cantPasos, Movimiento movimientoEnemigo, Mapa mapa) throws PasarelaInexistente;
}
