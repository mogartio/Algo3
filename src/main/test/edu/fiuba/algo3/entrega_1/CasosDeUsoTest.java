package edu.fiuba.algo3.entrega_1;

import java.util.ArrayList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import java.util.Stack;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CasosDeUsoTest {

    @Test
    public void test01LaVidaYCreditosDelJugadorSonLosCorrectosAlEmpezar() {
    //el jugador comienza con 20 puntos de vida y con 100 creditos
        Vida vida = new Vida(20);
        Jugador jugador = new Jugador(vida,100);

        assertEquals (100,jugador.obtenerCreditos());
        assertEquals (20,jugador.obtenerVida());
    }

    @Test
    public void test02aLaTorreBlancaTardaEnConstruirse1Turno() {
        Defensa torre = new TorrePlateada();

        assertFalse(torre.estaConstruida());

        torre.pasarTurno();
        assertFalse(torre.estaConstruida());
    }

    @Test
    public void test02bLaTorrePlateadaTardaEnConstruirse2Turnos() {
        Defensa torre = new TorrePlateada();

        //se verifica que la torre no se encuentra construida
        assertFalse(torre.estaConstruida());

        // se hace pasar un turno para la torre
        torre.pasarTurno();

        // se verifica que sigue en construccion
        assertFalse(torre.estaConstruida());

        //se hace pasar un segundo turno
        torre.pasarTurno();

        //se verifica que la torre ya se encuentra construida
        assertTrue(torre.estaConstruida());
    }

    @Test
    public void test03SeVerficaQueElJugadorDispongaDeCreditoParaConstruirTorres() {
        // se crea un jugador con los recursos base (tambien podrian ser otros)
        Vida vida = new Vida(20);
        Jugador jugador = new Jugador(vida,100);

        //se crea la tienda que sera quien proveera y verificara las torres comprables
        Tienda proveedor = new Tienda();

        //el jugador le envia sus creditos a instancia de tienda para que esta provea un
        //stack/pila con las estructuras que este puede comprar
        Stack<Defensa> torresComprables = jugador.verificarConstruccionesPosibles(proveedor);

        //assertions
        assertTrue(torresComprables.pop() instanceof TorrePlateada);
        assertTrue(torresComprables.pop() instanceof TorreBlanca);
    }

    @Test
    public void test04VerificarSoloSePuedaConstruirDefensasSobreTierra(){
        Defensa torre = new TorrePlateada();

        Coordenada coordTierra = new Coordenada(0,0);
        Coordenada coordRocosa = new Coordenada(1,0);

        Tierra tierra = new Tierra(coordTierra);
        Rocosa rocosa = new Rocosa(coordRocosa);

        rocosa.construirDefensa(torre);
        tierra.construirDefensa(torre);

        assertTrue(tierra.ocupada());
        assertFalse(rocosa.ocupada());
    }

    @Test
    public void test05VerificarQueLasDefensasAtaquenDentroDelRangoEsperado(){
        Coordenada coordenada = new Coordenada(0,0);
        Pasarela pasarela1 = new Pasarela(coordenada);
        Pasarela pasarela2 = new Pasarela(coordenada);
        Pasarela pasarela3 = new Pasarela(coordenada);

        Enemigo enemigo = new Enemigo();
        pasarela2.añadirEnemigo(enemigo);

        ArrayList<Pasarela> lista = new ArrayList<Pasarela>();
        lista.add(pasarela1);
        lista.add(pasarela2);
        lista.add(pasarela3);

        Rango rango = new Rango(lista);
        TorreBlanca torre = new TorreBlanca(rango);
        torre.pasarTurno();
        torre.atacar();

        assertTrue(enemigo.muerto());

    }

    @Test
    public void test06VerificarQueLasUnidadesEnemigosSonDañadasAcordeAlAtaqueRecibido(){}

}



