package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Enemigos.*;
import edu.fiuba.algo3.modelo.Excepciones.NoHayCamino;
import edu.fiuba.algo3.modelo.Excepciones.PasarelaInexistente;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EnemigoTest {

    @Test
    public void test01UnEnemigoRecibeDanioYMuere(){
        Enemigo arania = new Araña();

        arania.recibirDaño(2);

        assertFalse(arania.estaVivo());
    }

    @Test
    public void test02UnEnemigoRecibeDanioYSigueVivo(){
        Enemigo arania = new Araña();

        arania.recibirDaño(1);

        assertTrue(arania.estaVivo());
    }

    @Test
    public void test03UnEnemigoSeMueveYCambiaDePasarela() {
        Pasarela mockedPasarela1 = mock(Pasarela.class);
        Pasarela mockedPasarela2 = mock(Pasarela.class);
        when(mockedPasarela1.actualizarPosicion(2)).thenReturn(mockedPasarela2);

        Enemigo arania = new Araña();
        arania.actualizarPosicionActual(mockedPasarela1);

        try {
            arania.avanzar();
        } catch (PasarelaInexistente e) {
            fail("Ha saltado la excepción");
        }
    }

    @Test
    public void test04UnEnemigoCausaDañoAlJugadorYDespuesMuere(){
        Enemigo arania = new Araña();

        arania.dañarJugador();

        assertFalse(arania.estaVivo());
    }

}
