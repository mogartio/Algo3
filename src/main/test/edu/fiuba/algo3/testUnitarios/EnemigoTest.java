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
<<<<<<< HEAD
    public void test03UnEnemigoCausaDanioAlJugadorYDespuesMuere(){
        Enemigo arania = new Arania();
=======
    public void test03UnEnemigoCausaDañoAlJugadorYDespuesMuere(){
        RandomGenerator generadorRandom = new RandomGenerator(0,10);
        Enemigo arania = new Arania(generadorRandom);
>>>>>>> 829fc41bf286f6e0365c850723bf592e74f5011b

        arania.daniarJugador();

        assertFalse(arania.estaVivo());
    }

}
