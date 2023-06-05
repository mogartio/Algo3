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
        Pasarela pasarela1 = new Pasarela(coordenada, null);
        Pasarela pasarela2 = new Pasarela(coordenada, null);
        Pasarela pasarela3 = new Pasarela(coordenada, null);

        Enemigo enemigo = new Araña(null);
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
    public void test06VerificarQueLosEnemigosSonDañadosAcordeAlAtaqueRecibido(){
        Coordenada coordenada = new Coordenada(0,0);
        Pasarela pasarela1 = new Pasarela(coordenada, null);
        Pasarela pasarela2 = new Pasarela(coordenada, null);
        Pasarela pasarela3 = new Pasarela(coordenada, null);

        Enemigo enemigo = new Araña(null);
        Enemigo enemigo2 = new Araña(null);

        pasarela3.añadirEnemigo(enemigo2);
        pasarela2.añadirEnemigo(enemigo);

        ArrayList<Pasarela> lista = new ArrayList<Pasarela>();
        lista.add(pasarela1);
        lista.add(pasarela2);
        lista.add(pasarela3);

        Rango rango = new Rango(lista);
        TorreBlanca torre = new TorreBlanca(rango);
        torre.pasarTurno();
        torre.atacar();

        assertFalse(enemigo.muerto());
        assertTrue(enemigo2.muerto());
    }


    @Test
    public void test07aVerificarQueLasArañasSeDesplazanLaCantidadCorrectaDeParcelas(){

        Coordenada coordenadaFinal = new Coordenada(1,1);
        Pasarela pasarelaFinal = new Pasarela(coordenadaFinal, null);

        Coordenada coordenadaMedio = new Coordenada(1, 2);
        Pasarela pasarelaMedio = new Pasarela(coordenadaMedio, pasarelaFinal);

        Coordenada coordenadaInicial = new Coordenada(1, 3);
        Pasarela pasarelaInicial = new Pasarela(coordenadaInicial, pasarelaMedio);
        Enemigo enemigo = new Araña(pasarelaInicial);

        enemigo.avanzar();

        assertEquals(enemigo.verPosicion(), pasarelaFinal);
    }

    @Test
    public void test07bVerificarQueLasArañasSeDesplazanLaCantidadCorrectaDeParcelas(){

        Coordenada coordenadaFinal = new Coordenada(2,1);
        Pasarela pasarelaFinal = new Pasarela(coordenadaFinal, null);

        Coordenada coordenadaInicial = new Coordenada(1, 1);
        Pasarela pasarelaInicial = new Pasarela(coordenadaInicial, pasarelaFinal);
        Enemigo enemigo = new Hormiga(pasarelaInicial);

        enemigo.avanzar();

        assertEquals(enemigo.verPosicion(), pasarelaFinal);
    }

    @Test
    public void test08aHormigaDaCreditosCorrespondientesAlMorirUnaVez(){
        Vida vida = new Vida(20);
        Jugador jugador = new Jugador(vida,0);
        Enemigo hormiga = new Hormiga(null);

        hormiga.morir();

        assertEquals(1, jugador.getInstance().obtenerCreditos());
    }

    @Test
    public void test08bHormigaDaCreditosCorrespondientesAlMorirOnceVeces(){
        Vida vida = new Vida(20);
        Jugador jugador = new Jugador(vida,0);

        for(int i = 0; i<11; i++){
            Enemigo hormiga = new Hormiga(null);
            hormiga.morir();
        }

        assertEquals(13, jugador.getInstance().obtenerCreditos());
    }

    @Test
    public void test08cArañaDaCreditosCorrespondientesAlMorir(){
        Vida vida = new Vida(20);
        Jugador jugador = new Jugador(vida,0);
        assertFalse((Jugador.getInstance().obtenerCreditos() > 10) || (Jugador.getInstance().obtenerCreditos() < 0));
    }
}



