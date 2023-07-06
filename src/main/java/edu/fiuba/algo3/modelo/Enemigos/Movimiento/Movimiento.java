package edu.fiuba.algo3.modelo.Enemigos.Movimiento;

import edu.fiuba.algo3.modelo.Excepciones.PasarelaInexistente;
import edu.fiuba.algo3.modelo.juego.Mapa;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Parcela;

public interface Movimiento {

    void establecerInicioYMeta(Parcela parcelaInicial, Parcela parcelaFinal);

    void reasignarPosiciones(Coordenada coordInicial, Coordenada coordFinal);

    void actualizarPosicion(Parcela parcelaSiguiente);

    void actualizarCoordenadaActual(Coordenada posicionNueva);

    void avanzar(int cantidadPasos, Mapa mapa) throws PasarelaInexistente;

    void establecerMeta(Coordenada coordenada);

    void daniarJugador();

    boolean estaEnRango(Coordenada posicion, int distancia);

    Movimiento setMovimiento(Movimiento otroMovimiento);

    String representarUbicacion();
}
