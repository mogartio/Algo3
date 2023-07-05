package edu.fiuba.algo3.modelo.Enemigos.Movimiento;

import edu.fiuba.algo3.modelo.Excepciones.PasarelaInexistente;
import edu.fiuba.algo3.modelo.juego.Mapa;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Parcela;

public interface Movimiento {

    void reasignarPosiciones(Coordenada coordInicial, Coordenada coordFinal);

    void actualizarPosicion(Parcela parcelaSiguiente);

    // Se utilizara para añadir a los enemigos al mapa luego se utiliza el otro metodo
    void actualizarPosicion(Parcela parcelaInicial, Parcela parcelaFinal);

    void avanzar(int cantidadPasos, Mapa mapa) throws PasarelaInexistente;

    void actualizarUbicacion(Coordenada posicionNueva);

    void establecerMeta(Coordenada coordenada);

    void daniarJugador();

    boolean estaEnRango(Coordenada posicion, int distancia);

    Movimiento setMovimiento(Movimiento otroMovimiento);

    String representarUbicacion();
}
