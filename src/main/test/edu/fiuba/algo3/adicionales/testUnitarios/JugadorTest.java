package edu.fiuba.algo3.adicionales.testUnitarios;

import edu.fiuba.algo3.modelo.juego.Jugador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JugadorTest {
    @Test
    public void test01SeInicalizaAlJugadorYDespuesDeRecibirDanioSigueVivo(){
        Jugador jugador = Jugador.getInstance();
        jugador.reestablecerEstadoInicial();
        jugador.recibirDanio(3);

        assertTrue(Jugador.getInstance().estaVivo());
    }
}
