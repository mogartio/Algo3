package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Enemigos.*;
import edu.fiuba.algo3.modelo.Excepciones.NoHayCamino;
import edu.fiuba.algo3.modelo.Excepciones.PasarelaInexistente;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    public void test03UnEnemigoCausaDañoAlJugadorYDespuesMuere(){
        Enemigo arania = new Araña();

        arania.dañarJugador();

        assertFalse(arania.estaVivo());
    }

}
