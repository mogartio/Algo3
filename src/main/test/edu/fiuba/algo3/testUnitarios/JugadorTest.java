package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Defensas.TorreBlanca;
import edu.fiuba.algo3.modelo.juego.Credito;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.miscelanea.Tienda;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class JugadorTest {
    @Test
    public void test01SeInicalizaAlJugadorYDespuesDeRecibirDaioSigueVivo(){
        Jugador.getInstance().recibirDaño(3);

        assertTrue(Jugador.getInstance().estaVivo());
    }
}
