package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Defensas.*;
import edu.fiuba.algo3.modelo.Enemigos.Arania;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Hormiga;
import edu.fiuba.algo3.modelo.Excepciones.PasarelaInexistente;
import edu.fiuba.algo3.modelo.juego.Credito;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Mapa;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.miscelanea.RandomGenerator;
import edu.fiuba.algo3.modelo.parcelas.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CasosDeUsoTest {

    @Test
    public void test01LaVidaYCreditosDelJugadorSonLosCorrectosAlEmpezar() {
        //el jugador comienza con 20 puntos de vida y con 100 creditos
        Juego juego = Juego.getInstance();
        juego.reestablecerJuego();

        for (int i=1 ; i <= 5 ; i++) { //se gastan los creditos
            juego.comprar("TorrePlateada");
        }

        ArrayList<String> torresDisponibles = juego.verificarConstruccionesPosibles(); //se obtienen el listado de compras posibles

        for (int i = 0 ; i <= 19 ; i++){
            juego.daniarAlJugador(1);
        }


        assertTrue(torresDisponibles.isEmpty()); // se verifica que el listado de torres comprables este vacio
        assertFalse(juego.jugadorVivo());
    }

    @Test
    public void test02aLaTorreBlancaTardaEnConstruirse1Turno() {
        Defensa torre = new TorreBlanca();

        assertFalse(torre.estaConstruida());

        torre.pasarTurno();

        assertTrue(torre.estaConstruida());
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
        Jugador jugador = new Jugador();

        jugador.reestablecerEstadoInicial();

        //el jugador le envia sus creditos a instancia de tienda para que esta provea una
        //lista con las estructuras que este puede comprar
        ArrayList<String> torresComprables = jugador.verificarConstruccionesPosibles();

        //assertions
        assertTrue(torresComprables.contains("TorrePlateada"));
        assertTrue(torresComprables.contains("TorreBlanca"));
    }

    @Test
    public void test04VerificarSoloSePuedaConstruirDefensasSobreTierra() {
        Defensa torre = new TorrePlateada();

        Coordenada coordTierra = new Coordenada(0, 0);
        Coordenada coordRocosa = new Coordenada(1, 0);

        Tierra tierra = new Tierra(coordTierra);
        Rocosa rocosa = new Rocosa(coordRocosa);
        assertTrue(tierra.puedeConstruir(torre.sprayID()));
        assertFalse(rocosa.puedeConstruir(torre.sprayID()));
    }

    @Test
    public void test05VerificarQueLasDefensasAtaquenDentroDelRangoEsperado() {
        RandomGenerator generadorRandom = new RandomGenerator(0,10);

        Coordenada coordenada = new Coordenada(3, 0);
        Pasarela pasarela = new Pasarela(coordenada, new Normal());

        Coordenada coordenada2 = new Coordenada(6, 0);
        Pasarela pasarela2 = new Pasarela(coordenada2, new Normal());

        Enemigo enemigo = new Arania(generadorRandom);
        enemigo.actualizarPosicionActual(pasarela);

        Enemigo enemigo2 = new Arania(generadorRandom);
        enemigo2.actualizarPosicionActual(pasarela2);

        ArrayList<Enemigo> listaDeEnemigos = new ArrayList<>();
        listaDeEnemigos.add(enemigo);
        listaDeEnemigos.add(enemigo2);

        TorrePlateada torre = new TorrePlateada();
        torre.asignarPosicion(new Coordenada(0,0));
        torre.pasarTurno();
        torre.pasarTurno();

        torre.atacar(listaDeEnemigos);

        assertFalse(enemigo.estaVivo());

        listaDeEnemigos.remove(0);

        torre.pasarTurno();
        torre.atacar(listaDeEnemigos);

        assertTrue(enemigo2.estaVivo());

    }

    @Test
    public void test06VerificarQueLosEnemigosSonDaniadosAcordeAlAtaqueRecibido() {
        RandomGenerator generadorRandom = new RandomGenerator(0,10);

        Coordenada coordenada = new Coordenada(3, 0);
        Pasarela pasarela1 = new Pasarela(coordenada, new Normal());
        Pasarela pasarela2 = new Pasarela(coordenada, new Normal());

        Enemigo enemigo = new Arania(generadorRandom);
        Enemigo enemigo2 = new Hormiga();
        enemigo.actualizarPosicionActual(pasarela1);
        enemigo2.actualizarPosicionActual(pasarela2);

        ArrayList<Enemigo> listaEnemigos = new ArrayList<>();
        listaEnemigos.add(enemigo);
        listaEnemigos.add(enemigo2);

        TorreBlanca torre = new TorreBlanca();
        torre.asignarPosicion(new Coordenada(0,0));
        torre.pasarTurno();

        torre.atacar(listaEnemigos);
        listaEnemigos.remove(0); //elimino al primer enemigo para que no lo vuelva a atacar
        torre.atacar(listaEnemigos);

        assertTrue(enemigo.estaVivo());
        assertFalse(enemigo2.estaVivo());
    }


    @Test
    public void test07aVerificarQueLasAraniasSeDesplazanLaCantidadCorrectaDeParcelas() throws PasarelaInexistente {

        Mapa mapa = new Mapa();

        RandomGenerator generadorRandom = new RandomGenerator(0,10);


        Coordenada coordenadaFinal = new Coordenada(20, 1);
        Pasarela pasarelaFinal = new Pasarela(coordenadaFinal, new Meta());

        Coordenada coordenadaMedio = new Coordenada(1, 2);
        Pasarela pasarelaMedio = new Pasarela(coordenadaMedio, new Normal());

        Coordenada coordenadaInicial = new Coordenada(1, 3);
        Pasarela pasarelaInicial = new Pasarela(coordenadaInicial, new Normal());

        pasarelaInicial.agregarSiguiente(pasarelaMedio);
        pasarelaMedio.agregarSiguiente(pasarelaFinal);

        Defensa torre = new TorrePlateada();
        torre.asignarPosicion(new Coordenada(3,4));

        ArrayList<Enemigo> lista = new ArrayList<>();

        Enemigo enemigo = new Arania(generadorRandom);
        enemigo.actualizarPosicionActual(pasarelaInicial);

        lista.add(enemigo);

        enemigo.avanzar(mapa);
        torre.atacar(lista); // la araña debe estar fuera del rango de ataque de la torre por ende estaria viva


        assertTrue(enemigo.estaVivo());
    }

    @Test
    public void test07bVerificarQueLasAraniasSeDesplazanLaCantidadCorrectaDeParcelas() throws PasarelaInexistente {
        Mapa mapa = new Mapa();

        RandomGenerator generadorRandom = new RandomGenerator(0,10);

        Coordenada coordenadaFinal = new Coordenada(1, 1);
        Pasarela pasarelaFinal = new Pasarela(coordenadaFinal, new Meta());

        Coordenada coordenadaMedio = new Coordenada(1, 2);
        Pasarela pasarelaMedio = new Pasarela(coordenadaMedio, new Normal());

        Coordenada coordenadaInicial = new Coordenada(1, 3);
        Pasarela pasarelaInicial = new Pasarela(coordenadaInicial, new Normal());

        pasarelaInicial.agregarSiguiente(pasarelaMedio);
        pasarelaMedio.agregarSiguiente(pasarelaFinal);

        Defensa torre = new TorrePlateada();
        torre.asignarPosicion(new Coordenada(3,4));

        torre.pasarTurno();
        torre.pasarTurno();

        ArrayList<Enemigo> lista = new ArrayList<>();

        Enemigo enemigo = new Arania(generadorRandom);
        enemigo.actualizarPosicionActual(pasarelaInicial);

        lista.add(enemigo);

        enemigo.avanzar(mapa);
        torre.atacar(lista); // la araña debe estar dentro del rango de ataque de la torre por ende estaria muerta

        assertFalse(enemigo.estaVivo());
    }

    @Test
    public void test08aHormigaDaCreditosCorrespondientesAlMorirSinMultiplicador() {
        Juego juego = Juego.getInstance();
        juego.reestablecerJuego();

        Coordenada coordenadaInicializadora = new Coordenada(1, 1);
        Pasarela pasarelaInicializadora = new Pasarela(coordenadaInicializadora, new Normal());
        Credito creditoADescotnar = new Credito(100);


        juego.descontarCreditos(creditoADescotnar);
        for (int i=1; i<=10; i++){
            Enemigo hormiga = new Hormiga(pasarelaInicializadora);
            hormiga.morir();
        }

        ArrayList<String> comprasPosibles = juego.verificarConstruccionesPosibles();

        assertTrue(comprasPosibles.contains("TorreBlanca"));
    }

    @Test
    public void test08bHormigaDaCreditosCorrespondientesAlMorirConMultiplicador() {
        Juego juego = Juego.getInstance();
        juego.reestablecerJuego();

        Coordenada coordenadaInicializadora = new Coordenada(1, 1);
        Pasarela pasarelaInicializadora = new Pasarela(coordenadaInicializadora, new Normal());
        Credito creditoADescontar = new Credito(100);

        juego.descontarCreditos(creditoADescontar);

        for (int i=1; i<=15; i++){
            Enemigo hormiga = new Hormiga(pasarelaInicializadora);
            hormiga.morir();
        }

        ArrayList<String> comprasPosibles = juego.verificarConstruccionesPosibles();

        assertTrue(comprasPosibles.contains("TorrePlateada"));
        assertTrue(comprasPosibles.contains("TorreBlanca"));
    }

    @Test
    public void test08cAraniaDaCreditosCorrespondientesAlMorir() {
        Juego juego = Juego.getInstance();
        juego.reestablecerJuego();

        Credito creditoADescotnar = new Credito(100);

        RandomGenerator generadorRandomMockeado1 = mock(RandomGenerator.class);
        RandomGenerator generadorRandomMockeado2 = mock(RandomGenerator.class);
        RandomGenerator generadorRandomMockeado3 = mock(RandomGenerator.class);

        Arania arania1 = new Arania(generadorRandomMockeado1);
        Arania arania2 = new Arania(generadorRandomMockeado2);
        Arania arania3 = new Arania(generadorRandomMockeado3);

        when(generadorRandomMockeado1.obtenerUnNumero()).thenReturn(2);
        when(generadorRandomMockeado2.obtenerUnNumero()).thenReturn(7);
        when(generadorRandomMockeado3.obtenerUnNumero()).thenReturn(1);

        juego.descontarCreditos(creditoADescotnar);
        arania1.morir();
        arania2.morir();
        arania3.morir();

        ArrayList<String> posiblesCompras = juego.verificarConstruccionesPosibles();

        assertTrue(posiblesCompras.contains("TorreBlanca"));
    }

    @Test
    public void test09aCrearHormigaYAvanzarHaceQueAvanceUnaPosicion() throws PasarelaInexistente {
        Mapa mapa = new Mapa();

        Coordenada coordenadaMedio = new Coordenada(1, 2);
        Pasarela pasarelaMedio = new Pasarela(coordenadaMedio, new Normal());

        Coordenada coordenadaInicial = new Coordenada(1, 3);
        Pasarela pasarelaInicial = new Pasarela(coordenadaInicial, new Normal());

        pasarelaInicial.agregarSiguiente(pasarelaMedio);

        Defensa torre = new TorrePlateada();
        torre.asignarPosicion(new Coordenada(3,4));

        torre.pasarTurno();
        torre.pasarTurno();

        ArrayList<Enemigo> lista = new ArrayList<>();

        Enemigo enemigo = new Hormiga();
        enemigo.actualizarPosicionActual(pasarelaInicial);

        lista.add(enemigo);

        enemigo.avanzar(mapa);
        torre.atacar(lista); // la hormiga debe estar dentro del rango de ataque de la torre por ende estaria muerta


        assertFalse(enemigo.estaVivo());
    }

    @Test
    public void test09UnaAraniaAlLlegarALaMetaDejaDeMoverse() {
        Mapa mapa = new Mapa();

        Coordenada coordenadaMeta = new Coordenada(1, 1);
        Pasarela pasarelaMeta = new Pasarela(coordenadaMeta, new Meta());

        Arania arania = new Arania(pasarelaMeta, new RandomGenerator(1,10));

        try{
            arania.avanzar(mapa);
        } catch (PasarelaInexistente e) {
            fail("Pasarela inexistente");
        }
    }

   @Test
    public void test09cCrearUnaColeccionDeEnemigosYHacerQueAvanzenActualizaLasPosicionesDeTodos() {
        RandomGenerator generadorRandom = new RandomGenerator(0,10);

        Coordenada coordenadaFinal = new Coordenada(0, 1);
        Pasarela pasarelaFinal = new Pasarela(coordenadaFinal, new Meta());

        Coordenada coordenadaTercera = new Coordenada(1, 1);
        Pasarela pasarelaTercera = new Pasarela(coordenadaTercera, new Normal());
        pasarelaTercera.agregarSiguiente(pasarelaFinal);

        Coordenada coordenadaSegunda = new Coordenada(1, 2);
        Pasarela pasarelaSegunda = new Pasarela(coordenadaSegunda, new Normal());
        pasarelaSegunda.agregarSiguiente(pasarelaTercera);

        Coordenada coordenadaPrimera = new Coordenada(2, 2);
        Pasarela pasarelaPrimera = new Pasarela(coordenadaPrimera, new Normal());
        pasarelaPrimera.agregarSiguiente(pasarelaSegunda);

        Enemigo araniaUno = new Arania(pasarelaPrimera,generadorRandom);
        Enemigo araniaDos = new Arania(pasarelaSegunda,generadorRandom);
        Enemigo hormigaUno = new Hormiga(pasarelaTercera);
        Enemigo hormigaDos = new Hormiga(pasarelaPrimera);


       Mapa mapaJuego = new Mapa();
       mapaJuego.setPasarelaInicialFinal(pasarelaPrimera, pasarelaFinal);
       mapaJuego.agregar(coordenadaPrimera, pasarelaPrimera);
       mapaJuego.agregar(coordenadaSegunda, pasarelaSegunda);
       mapaJuego.agregar(coordenadaTercera, pasarelaTercera);
       mapaJuego.agregar(coordenadaFinal, pasarelaFinal);

       LinkedList<ArrayList<Enemigo>> oleadas = new LinkedList<>();
       mapaJuego.cargarOleadas(oleadas);

       Juego juego =  Juego.getInstance();
       juego.reestablecerJuego();

       juego.nuevoEnemigo(araniaUno);
       juego.nuevoEnemigo(araniaDos);
       juego.nuevoEnemigo(hormigaUno);
       juego.nuevoEnemigo(hormigaDos);


       juego.jugarTurno(1);

       assertTrue(hormigaDos.estaEnRango(coordenadaSegunda, 0, new Ataque(1)));
       assertTrue(hormigaUno.estaEnRango(coordenadaFinal, 0, new Ataque(1)));
       assertTrue(araniaDos.estaEnRango(coordenadaFinal, 0, new Ataque(1)));
       assertTrue(araniaUno.estaEnRango(coordenadaTercera, 0, new Ataque(1)));

    }

    @Test
    public void test10aCrearUnJuegoConUnEnemigoSeGanaCuandoElEnemigoMuere() {
        Jugador jugador = new Jugador();
        jugador.reestablecerEstadoInicial();
        RandomGenerator generadorRandom = new RandomGenerator(0,10);

        Juego juego = Juego.getInstance();
        juego.reestablecerJuego();

        Coordenada coordenadaTercera = new Coordenada(2, 0);
        Pasarela pasarelaTercera = new Pasarela(coordenadaTercera, new Normal());

        Coordenada coordenadaSegunda = new Coordenada(2,1);
        Pasarela pasarelaSegunda = new Pasarela(coordenadaSegunda, new Normal());
        pasarelaSegunda.agregarSiguiente(pasarelaTercera);

        Coordenada coordenadaPrimera = new Coordenada(2, 2);

        Pasarela pasarelaPrimera = new Pasarela(coordenadaPrimera, new Normal());
        pasarelaPrimera.agregarSiguiente(pasarelaSegunda);

        Enemigo arania = new Arania(pasarelaPrimera,generadorRandom);


        juego.nuevoEnemigo(arania);

        assertFalse(juego.finalizado());

        arania.recibirDanio(2);
        juego.jugarTurno(1);

        assertTrue(jugador.estaVivo()); //Estos dos assert son equivalentes a decir que el juego esta en estado "ganado"
        assertTrue(juego.finalizado());
    }

    @Test
    public void test10bCrearUnJuegoConMuchosEnemigosSeGanaSoloCuandoTodosMueren() {

        Jugador jugador = new Jugador();
        jugador.reestablecerEstadoInicial();
        RandomGenerator generadorRandom = new RandomGenerator(0,10);

        Coordenada coordenada = new Coordenada(2, 2);
        Pasarela pasarelaFinal = new Pasarela(coordenada, new Meta());
        Pasarela pasarelaQuinta = new Pasarela(coordenada, pasarelaFinal, new Normal());
        Pasarela pasarelaCuarta = new Pasarela(coordenada, pasarelaQuinta, new Normal());
        Pasarela pasarelaTercera = new Pasarela(coordenada, pasarelaCuarta,  new Normal());
        Pasarela pasarelaSegunda = new Pasarela(coordenada, pasarelaTercera,  new Normal());
        Pasarela pasarelaPrimera = new Pasarela(coordenada, pasarelaSegunda,  new Normal());

        Arania araniaUno = new Arania(pasarelaPrimera,generadorRandom);
        Arania araniaDos = new Arania(pasarelaPrimera,generadorRandom);
        Arania araniaTres = new Arania(pasarelaPrimera,generadorRandom);
        Arania araniaCuatro = new Arania(pasarelaPrimera,generadorRandom);
        Hormiga HormigaUno = new Hormiga(pasarelaPrimera);
        Hormiga HormigaDos = new Hormiga(pasarelaPrimera);
        Hormiga HormigaTres = new Hormiga(pasarelaPrimera);
        Hormiga HormigaCuatro = new Hormiga(pasarelaPrimera);

        ArrayList<Enemigo> enemigos = new ArrayList<>();

        enemigos.add(araniaUno);
        enemigos.add(araniaDos);
        enemigos.add(araniaTres);
        enemigos.add(araniaCuatro);
        enemigos.add(HormigaUno);
        enemigos.add(HormigaDos);
        enemigos.add(HormigaTres);
        enemigos.add(HormigaCuatro);

        Juego juego = Juego.getInstance();
        juego.reestablecerJuego();

        enemigos.forEach(juego::nuevoEnemigo);
        enemigos.forEach(enemigo -> enemigo.recibirDanio(1)); //Las hormigas mueren pero las arañas siguen vivas
        juego.jugarTurno(1);

        assertFalse(juego.finalizado());

        araniaUno.recibirDanio(1);
        araniaDos.recibirDanio(1);
        araniaTres.recibirDanio(1);
        juego.jugarTurno(2);
        //Queda solo la última Araña viva
        assertFalse(juego.finalizado());

        araniaCuatro.recibirDanio(1);
        juego.jugarTurno(3);

        assertTrue(jugador.estaVivo()); //Estos dos assert son equivalentes a decir que el juego esta en estado "ganado"
        assertTrue(juego.finalizado());
    }
    @Test
    public void test11ElJugadorSobreviveConUnEnemigoLlegandoALaMetaYGanaIgual() {
        Jugador jugador = new Jugador();
        jugador.reestablecerEstadoInicial();

        Juego juego = Juego.getInstance();
        juego.reestablecerJuego();

        Coordenada coordenadaFinal = new Coordenada(2,3);
        Pasarela pasarelaFinal = new Pasarela(coordenadaFinal, new Meta());
        Coordenada coordenadaPrimera = new Coordenada(2, 2);
        Pasarela pasarelaPrimera = new Pasarela(coordenadaPrimera, pasarelaFinal, new Normal());
        Enemigo hormiga = new Hormiga(pasarelaPrimera);

        juego.nuevoEnemigo(hormiga);
        juego.jugarTurno(1); //El enemigo llega a la meta

        assertFalse(juego.finalizado());
        juego.jugarTurno(2); //El enemigo llega a la meta

        assertTrue(jugador.estaVivo()); //Estos dos assert son equivalentes a decir que el juego esta en estado "ganado"
        assertTrue(juego.finalizado());
    }

    @Test
    public void test12ElJugadorMuereYPierdeElJuego() {

        Juego juego = Juego.getInstance();
        juego.reestablecerJuego();

        Coordenada coordenadaFinal = new Coordenada(2,3);
        Pasarela pasarelaFinal = new Pasarela(coordenadaFinal, new Meta());

        Coordenada coordenadaPrimera = new Coordenada(2, 2);
        Pasarela pasarelaPrimera = new Pasarela(coordenadaPrimera, pasarelaFinal, new Normal());

        for(int i=0; i<20; i++){
            Enemigo hormiga = new Hormiga(pasarelaPrimera);
            juego.nuevoEnemigo(hormiga);
            juego.jugarTurno(i);
        }

        assertFalse(juego.finalizado()); //Queda una hormiga sola en el mapa, si avanza hará el último punto de daño al jugador

        juego.jugarTurno(20);

        assertFalse(juego.jugadorVivo()); //Estos dos assert son equivalentes a decir que el juego esta en estado "perdido"
        assertTrue(juego.finalizado());
    }
}



