package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Enemigos.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemigoTest {

    @Test
    public void test01UnEnemigoRecibeDanioYMuere(){
        Enemigo arania = new Arania();

        arania.recibirDanio(2);

        assertFalse(arania.estaVivo());
    }

    @Test
    public void test02UnEnemigoRecibeDanioYSigueVivo(){
        Enemigo arania = new Arania();

        arania.recibirDanio(1);

        assertTrue(arania.estaVivo());
    }

    @Test
    public void test03UnEnemigoCausaDanioAlJugadorYDespuesMuere(){
        Enemigo arania = new Arania();

        arania.daniarJugador();

        assertFalse(arania.estaVivo());
    }

}
