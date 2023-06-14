package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Enemigos.*;
import edu.fiuba.algo3.modelo.miscelanea.RandomGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemigoTest {

    @Test
    public void test01UnEnemigoRecibeDanioYMuere(){
        RandomGenerator generadorRandom = new RandomGenerator(0,10);
        Enemigo arania = new Arania(generadorRandom);

        arania.recibirDanio(2);

        assertFalse(arania.estaVivo());
    }

    @Test
    public void test02UnEnemigoRecibeDanioYSigueVivo(){
        RandomGenerator generadorRandom = new RandomGenerator(0,10);
        Enemigo arania = new Arania(generadorRandom);

        arania.recibirDanio(1);

        assertTrue(arania.estaVivo());
    }

    @Test
    public void test03UnEnemigoCausaDañoAlJugadorYDespuesMuere(){
        RandomGenerator generadorRandom = new RandomGenerator(0,10);
        Enemigo arania = new Arania(generadorRandom);

        arania.daniarJugador();

        assertFalse(arania.estaVivo());
    }

}
