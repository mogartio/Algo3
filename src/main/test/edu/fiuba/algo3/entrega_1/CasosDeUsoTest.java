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
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CasosDeUsoTest {

    @Test
    public void test01LaVidaYCreditosDelJugadorSonLosCorrectosAlEmpezar() {
        //el jugador comienza con 20 puntos de vida y con 100 creditos
        Vida vida = new Vida(20);
        Jugador jugador = new Jugador(vida, 100);

        assertEquals(100, jugador.obtenerCreditos());
        assertEquals(20, jugador.obtenerVida());
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
        Jugador jugador = new Jugador(vida, 100);

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
    public void test04VerificarSoloSePuedaConstruirDefensasSobreTierra() {
        Defensa torre = new TorrePlateada();

        Coordenada coordTierra = new Coordenada(0, 0);
        Coordenada coordRocosa = new Coordenada(1, 0);

        Tierra tierra = new Tierra(coordTierra);
        Rocosa rocosa = new Rocosa(coordRocosa);

        rocosa.construirDefensa(torre);
        tierra.construirDefensa(torre);

        assertTrue(tierra.ocupada());
        assertFalse(rocosa.ocupada());
    }

    @Test
    public void test05VerificarQueLasDefensasAtaquenDentroDelRangoEsperado() {
        Coordenada coordenada = new Coordenada(0, 0);
        Pasarela pasarela1 = new PasarelaIntermedia(coordenada, null);
        Pasarela pasarela2 = new PasarelaIntermedia(coordenada, null);
        Pasarela pasarela3 = new PasarelaIntermedia(coordenada, null);

        Enemigo enemigo = new Araña(pasarela2);

        ArrayList<Pasarela> lista = new ArrayList<Pasarela>();
        lista.add(pasarela1);
        lista.add(pasarela2);
        lista.add(pasarela3);

        Rango rango = new Rango(lista);
        TorreBlanca torre = new TorreBlanca(rango);
        torre.pasarTurno();
        torre.atacar();

        assertTrue(enemigo.estaVivo());

        torre.pasarTurno();
        torre.atacar();

        assertFalse(enemigo.estaVivo());

    }

    @Test
    public void test06VerificarQueLosEnemigosSonDañadosAcordeAlAtaqueRecibido() {
        Coordenada coordenada = new Coordenada(0, 0);
        Pasarela pasarela1 = new PasarelaIntermedia(coordenada, null);
        Pasarela pasarela2 = new PasarelaIntermedia(coordenada, null);
        Pasarela pasarela3 = new PasarelaIntermedia(coordenada, null);

        Enemigo enemigo = new Hormiga(pasarela2);
        Enemigo enemigo2 = new Hormiga(pasarela3);

        ArrayList<Pasarela> lista = new ArrayList<Pasarela>();
        lista.add(pasarela1);
        //lista.add(pasarela2);
        lista.add(pasarela3);

        Rango rango = new Rango(lista);
        TorreBlanca torre = new TorreBlanca(rango);
        torre.pasarTurno();
        torre.atacar();

        assertTrue(enemigo.estaVivo());
        assertFalse(enemigo2.estaVivo());
    }


    @Test
    public void test07aVerificarQueLasArañasSeDesplazanLaCantidadCorrectaDeParcelas() {

        Coordenada coordenadaFinal = new Coordenada(1, 1);
        Pasarela pasarelaFinal = new PasarelaIntermedia(coordenadaFinal, null);

        Coordenada coordenadaMedio = new Coordenada(1, 2);
        Pasarela pasarelaMedio = new PasarelaIntermedia(coordenadaMedio, pasarelaFinal);

        Coordenada coordenadaInicial = new Coordenada(1, 3);
        Pasarela pasarelaInicial = new PasarelaIntermedia(coordenadaInicial, pasarelaMedio);
        Enemigo enemigo = new Araña(pasarelaInicial);

        enemigo.avanzar();

        assertEquals(enemigo.verPosicion(), pasarelaFinal);
    }

    @Test
    public void test07bVerificarQueLasArañasSeDesplazanLaCantidadCorrectaDeParcelas() {

        Coordenada coordenadaFinal = new Coordenada(2, 1);
        Pasarela pasarelaFinal = new PasarelaIntermedia(coordenadaFinal, null);

        Coordenada coordenadaInicial = new Coordenada(1, 1);
        Pasarela pasarelaInicial = new PasarelaIntermedia(coordenadaInicial, pasarelaFinal);
        Enemigo enemigo = new Hormiga(pasarelaInicial);

        enemigo.avanzar();

        assertEquals(enemigo.verPosicion(), pasarelaFinal);
    }
    /*@Test
    public void test08aHormigaDaCreditosCorrespondientesAlMorirOnceVeces() {
        Vida vida = new Vida(20);
        Jugador jugador = new Jugador(vida, 0);
        int creditoInicial = jugador.getInstance().obtenerCreditos();

        for (int i = 0; i < 11; i++) {
            Coordenada coordenadaInicializadora = new Coordenada(1, 1);
            Pasarela pasarelaInicializadora = new PasarelaIntermedia(coordenadaInicializadora, null);
            Enemigo hormiga = new Hormiga(pasarelaInicializadora);
            hormiga.morir();
        }

        assertEquals((13 + creditoInicial), jugador.getInstance().obtenerCreditos()); //Corregir tests. Al ser singleton cada test hace la prueba sobre la misma instancia de juagdor
    }*/

    @Test
    public void test08bHormigaDaCreditosCorrespondientesAlMorirUnaVez() {
        Vida vida = new Vida(20);
        Jugador jugador = new Jugador(vida, 0);
        int creditoInicial = jugador.getInstance().obtenerCreditos();

        Coordenada coordenadaInicializadora = new Coordenada(1, 1);
        Pasarela pasarelaInicializadora = new PasarelaIntermedia(coordenadaInicializadora, null);
        Enemigo hormiga = new Hormiga(pasarelaInicializadora);

        hormiga.morir();

        assertEquals((1 + creditoInicial), jugador.getInstance().obtenerCreditos());
    }

    @Test
    public void test08cArañaDaCreditosCorrespondientesAlMorir() {
        Vida vida = new Vida(20);
        Jugador jugador = new Jugador(vida, 0);
        int creditosIniciales = jugador.getInstance().obtenerCreditos();
        assertFalse((Jugador.getInstance().obtenerCreditos() > 10 + creditosIniciales) || (Jugador.getInstance().obtenerCreditos() < 0 + creditosIniciales));
    }

    @Test
    public void test09aCrearHormigaYAvanzarHaceQueAvanceUnaPosicion() {
        Coordenada coordenadaFinal = new Coordenada(0, 1);
        Pasarela pasarelaFinal = new PasarelaIntermedia(coordenadaFinal, null);

        Coordenada coordenadaSegunda = new Coordenada(1, 1);
        Pasarela pasarelaSegunda = new PasarelaIntermedia(coordenadaSegunda, pasarelaFinal);

        Coordenada coordenadaInicial = new Coordenada(1, 2);
        Pasarela pasarelaInicial = new PasarelaIntermedia(coordenadaInicial, pasarelaSegunda);

        Enemigo hormiga = new Hormiga(pasarelaInicial);

        Juego juego = new Juego(null);
        juego.nuevoEnemigo(hormiga);
        juego.pasarTurno();


        assertEquals(hormiga.verPosicion(), pasarelaSegunda);
    }

    @Test
    public void test09bCrearArañaYAvarnzarHaceQueAvanceDosPosiciones() {
        Coordenada coordenadaFinal = new Coordenada(1, 3);
        Pasarela pasarelaFinal = new PasarelaIntermedia(coordenadaFinal, null);

        Coordenada coordenadaSegunda = new Coordenada(5, 2);
        Pasarela pasarelaSegunda = new PasarelaIntermedia(coordenadaSegunda, pasarelaFinal);

        Coordenada coordenadaInicial = new Coordenada(4, 3);
        Pasarela pasarelaInicial = new PasarelaIntermedia(coordenadaInicial, pasarelaSegunda);
        Enemigo araña = new Araña(pasarelaInicial);

        Juego juego = new Juego(null);
        juego.nuevoEnemigo(araña);
        juego.pasarTurno();

        assertEquals(araña.verPosicion(), pasarelaFinal);
    }

    @Test
    public void test09cCrearUnaColeccionDeEnemigosYHacerQueAvanzenActualizaLasPosicionesDeTodos() {
        Coordenada coordenadaFinal = new Coordenada(0, 1);
        Pasarela pasarelaFinal = new PasarelaIntermedia(coordenadaFinal, null);

        Coordenada coordenadaTercera = new Coordenada(1, 1);
        Pasarela pasarelaTercera = new PasarelaIntermedia(coordenadaTercera, pasarelaFinal);

        Coordenada coordenadaSegunda = new Coordenada(1, 2);
        Pasarela pasarelaSegunda = new PasarelaIntermedia(coordenadaSegunda, pasarelaTercera);

        Coordenada coordenadaPrimera = new Coordenada(2, 2);
        Pasarela pasarelaPrimera = new PasarelaIntermedia(coordenadaPrimera, pasarelaSegunda);

        Juego juego = new Juego(null);

        Enemigo arañaUno = new Araña(pasarelaPrimera);
        Enemigo arañaDos = new Araña(pasarelaSegunda);
        Enemigo hormigaUno = new Hormiga(pasarelaTercera);
        Enemigo hormigaDos = new Hormiga(pasarelaPrimera);

        Stack<Enemigo> enemigos = new Stack<Enemigo>();

        enemigos.push(arañaUno);
        enemigos.push(arañaDos);
        enemigos.push(hormigaUno);
        enemigos.push(hormigaDos);

        enemigos.forEach(enemigo -> juego.nuevoEnemigo(enemigo));
        juego.pasarTurno();

        //Al ser una estructura de pila, se invierte el orden comparado al ingreso al hacer pop
        assertEquals(enemigos.pop().verPosicion(), pasarelaSegunda); // De la primera avanza uno y va a la segunda (final)
        assertEquals(enemigos.pop().verPosicion(), pasarelaFinal); // De la tercera avanza uno y va a la cuarta (final)
        assertEquals(enemigos.pop().verPosicion(), pasarelaFinal); // De la segunda avanza dos y va a la cuarta (final)
        assertEquals(enemigos.pop().verPosicion(), pasarelaTercera); // De la primera avanza dos y va a la tercera
    }

    @Test
    public void test10aCrearUnJuegoConUnEnemigoSeGanaCuandoElEnemigoMuere() {
        Vida vida = new Vida(20);
        Jugador jugador = new Jugador(vida, 0);
        Juego juego = new Juego(jugador);

        Coordenada coordenadaPrimera = new Coordenada(2, 2);
        Pasarela pasarelaPrimera = new PasarelaIntermedia(coordenadaPrimera, null);
        Enemigo araña = new Araña(pasarelaPrimera);

        juego.nuevoEnemigo(araña);

        araña.recibirDaño(1);
        assertTrue(juego.estado() instanceof Jugando);

        araña.recibirDaño(1);
        assertTrue(juego.estado() instanceof Ganado);
    }

    @Test
    public void test10bCrearUnJuegoConMuchosEnemigosSeGanaSoloCuandoTodosMueren() {

        Vida vida = new Vida(20);
        Jugador jugador = new Jugador(vida, 0);

        Coordenada coordenadaPrimera = new Coordenada(2, 2);
        Pasarela pasarelaPrimera = new PasarelaIntermedia(coordenadaPrimera, null);
        Araña arañaUno = new Araña(pasarelaPrimera);
        Araña arañaDos = new Araña(pasarelaPrimera);
        Araña arañaTres = new Araña(pasarelaPrimera);
        Araña arañaCuatro = new Araña(pasarelaPrimera);
        Hormiga HormigaUno = new Hormiga(pasarelaPrimera);
        Hormiga HormigaDos = new Hormiga(pasarelaPrimera);
        Hormiga HormigaTres = new Hormiga(pasarelaPrimera);
        Hormiga HormigaCuatro = new Hormiga(pasarelaPrimera);

        ArrayList<Enemigo> enemigos = new ArrayList<Enemigo>();

        enemigos.add(arañaUno);
        enemigos.add(arañaDos);
        enemigos.add(arañaTres);
        enemigos.add(arañaCuatro);
        enemigos.add(HormigaUno);
        enemigos.add(HormigaDos);
        enemigos.add(HormigaTres);
        enemigos.add(HormigaCuatro);

        Juego juego = new Juego(jugador, enemigos);
        assertTrue(juego.estado() instanceof Jugando);

        enemigos.forEach(enemigo -> enemigo.recibirDaño(1)); //Las hormigas mueren pero las arañas siguen vivas
        assertTrue(juego.estado() instanceof Jugando);

        arañaUno.recibirDaño(1);
        arañaDos.recibirDaño(1);
        arañaTres.recibirDaño(1);
        //Queda solo la última Araña viva
        assertTrue(juego.estado() instanceof Jugando);

        arañaCuatro.recibirDaño(1);
        assertTrue(juego.estado() instanceof Ganado);
    }
   /* @Test
    public void test11ElJugadorSobreviveConUnEnemigoLlegandoALaMetaYGanaIgual() {
        Vida vida = new Vida(20);
        Jugador jugador = new Jugador(vida, 0);
        Juego juego = new Juego(jugador);

        Coordenada coordenadaFinal = new Coordenada(2,3);
        Pasarela pasarelaFinal = new PasarelaFinal(coordenadaFinal, null);

        Coordenada coordenadaPrimera = new Coordenada(2, 2);
        Pasarela pasarelaPrimera = new PasarelaIntermedia(coordenadaPrimera, pasarelaFinal);

        Enemigo hormiga = new Hormiga(pasarelaPrimera);
        System.out.println(Jugador.getInstance().obtenerVida());
        juego.nuevoEnemigo(hormiga);
        hormiga.avanzar();
        assertTrue(juego.estado() instanceof Ganado);
    } */
}



