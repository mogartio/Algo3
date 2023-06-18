package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.mocks.VendedorMock;
import edu.fiuba.algo3.modelo.Defensas.*;
import edu.fiuba.algo3.modelo.Enemigos.Arania;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Hormiga;
import edu.fiuba.algo3.modelo.Excepciones.PasarelaInexistente;
import edu.fiuba.algo3.modelo.Observer.Logger;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.lectorJSON.Mapa;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.miscelanea.Tienda;
import edu.fiuba.algo3.modelo.parcelas.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;


public class CasosDeUsoTest {

    @Test
    public void test01LaVidaYCreditosDelJugadorSonLosCorrectosAlEmpezar() {
        //el jugador comienza con 20 puntos de vida y con 100 creditos
        Jugador jugador = Jugador.getInstance();
        jugador.reestablecerEstadoInicial();
        Tienda proveedor = new Tienda();

        for (int i=1 ; i <= 5 ; i++) { //se gastan los creditos
            jugador.comprar(proveedor,"TorrePlateada");
        }
        ArrayList<String> torresDisponibles = jugador.verificarConstruccionesPosibles(proveedor); //se obtienen el listado de compras posibles

        for (int i=0 ; i <= 19 ; i++){
            Jugador.getInstance().recibirDanio(1);
        }


        assertTrue(torresDisponibles.isEmpty()); // se verifica que el listado de torres comprables este vacio
        assertFalse(jugador.estaVivo());
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
        Jugador jugador = Jugador.getInstance();

        jugador.reestablecerEstadoInicial();

        //se crea la tienda que sera quien proveera y verificara las torres comprables
        Tienda proveedor = new Tienda();

        //el jugador le envia sus creditos a instancia de tienda para que esta provea una
        //lista con las estructuras que este puede comprar
        ArrayList<String> torresComprables = jugador.verificarConstruccionesPosibles(proveedor);

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

        rocosa.construirDefensa(torre);
        tierra.construirDefensa(torre);

        assertTrue(tierra.ocupada());
        assertFalse(rocosa.ocupada());
    }

    @Test
    public void test05VerificarQueLasDefensasAtaquenDentroDelRangoEsperado() {
        Coordenada coordenada = new Coordenada(3, 0);
        Pasarela pasarela = new Pasarela(coordenada, new Normal());

        Coordenada coordenada2 = new Coordenada(6, 0);
        Pasarela pasarela2 = new Pasarela(coordenada2, new Normal());

        Enemigo enemigo = new Arania();
        enemigo.actualizarPosicionActual(pasarela);

        Enemigo enemigo2 = new Arania();
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
        Coordenada coordenada = new Coordenada(3, 0);
        Pasarela pasarela1 = new Pasarela(coordenada, new Normal());
        Pasarela pasarela2 = new Pasarela(coordenada, new Normal());

        Enemigo enemigo = new Arania();
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

        Enemigo enemigo = new Arania();
        enemigo.actualizarPosicionActual(pasarelaInicial);

        lista.add(enemigo);

        enemigo.avanzar(mapa);
        torre.atacar(lista); // la araña debe estar fuera del rango de ataque de la torre por ende estaria viva


        assertTrue(enemigo.estaVivo());
    }

    @Test
    public void test07bVerificarQueLasAraniasSeDesplazanLaCantidadCorrectaDeParcelas() throws PasarelaInexistente {
        Mapa mapa = new Mapa();
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

        Enemigo enemigo = new Arania();
        enemigo.actualizarPosicionActual(pasarelaInicial);

        lista.add(enemigo);

        enemigo.avanzar(mapa);
        torre.atacar(lista); // la araña debe estar dentro del rango de ataque de la torre por ende estaria muerta

        assertFalse(enemigo.estaVivo());
    }

    @Test
    public void test08aHormigaDaCreditosCorrespondientesAlMorirSinMultiplicador() {

        Jugador jugador = Jugador.getInstance();
        jugador.reestablecerEstadoInicial();

        VendedorMock vendedorMock = new VendedorMock();

        Coordenada coordenadaInicializadora = new Coordenada(1, 1);
        Pasarela pasarelaInicializadora = new Pasarela(coordenadaInicializadora, new Normal());

        for (int i=1; i<9; i++){
            Enemigo hormiga = new Hormiga(pasarelaInicializadora);
            hormiga.morir();
            int creditosJugador = Integer.parseInt(jugador.verificarConstruccionesPosibles(vendedorMock).get(0));
            assertEquals((i + 100), creditosJugador);
        }
    }

    @Test
    public void test08bHormigaDaCreditosCorrespondientesAlMorirConMultiplicador() {
        Jugador jugador = Jugador.getInstance();
        jugador.reestablecerEstadoInicial();

        Coordenada coordenadaInicializadora = new Coordenada(1, 1);
        Pasarela pasarelaInicializadora = new Pasarela(coordenadaInicializadora, new Normal());

        VendedorMock vendedorMock = new VendedorMock();
        int cantidadCreditosAnteriores = Integer.parseInt(jugador.verificarConstruccionesPosibles(vendedorMock).get(0));

        for (int i=1; i<15; i++){
            Enemigo hormiga = new Hormiga(pasarelaInicializadora);
            hormiga.morir();
            int creditosJugador = Integer.parseInt(jugador.verificarConstruccionesPosibles(vendedorMock).get(0));
            if(i >= 10) {
                assertEquals(cantidadCreditosAnteriores + 2, creditosJugador);
            } else {
                assertEquals(cantidadCreditosAnteriores + 1, creditosJugador);
            }
            cantidadCreditosAnteriores = creditosJugador;
        }
    }

    @Test
    public void test08cAraniaDaCreditosCorrespondientesAlMorir() {
        Jugador jugador = Jugador.getInstance();
        jugador.reestablecerEstadoInicial();

        VendedorMock vendedorMock = new VendedorMock();
        int creditosJugador = Integer.parseInt(jugador.verificarConstruccionesPosibles(vendedorMock).get(0));

        assertFalse((creditosJugador > 10 + 100) || (creditosJugador < 100));
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
    public void test09UnaAraniaAlLlegarALaMetaDejaDeMoverse() throws PasarelaInexistente {
        Mapa mapa = new Mapa();

        Coordenada coordenadaMeta = new Coordenada(1, 1);
        Pasarela pasarelaMeta = new Pasarela(coordenadaMeta, new Meta());

        Arania arania = new Arania(pasarelaMeta);

        try{
            arania.avanzar(mapa);
        }catch (PasarelaInexistente e) {
            fail("Pasarela inexistente");
        }
    }

   @Test
    public void test09cCrearUnaColeccionDeEnemigosYHacerQueAvanzenActualizaLasPosicionesDeTodos() {
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

        Enemigo araniaUno = new Arania(pasarelaPrimera);
        Enemigo araniaDos = new Arania(pasarelaSegunda);
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

       Logger logger = new Logger();
       Juego juego = new Juego(mapaJuego, logger);

       juego.nuevoEnemigo(araniaUno);
       juego.nuevoEnemigo(araniaDos);
       juego.nuevoEnemigo(hormigaUno);
       juego.nuevoEnemigo(hormigaDos);


       juego.jugarTurno(1);

       assertTrue(hormigaDos.estaEnRango(coordenadaSegunda, 0));
       assertTrue(hormigaUno.estaEnRango(coordenadaFinal, 0));
       assertTrue(araniaDos.estaEnRango(coordenadaFinal, 0));
       assertTrue(araniaUno.estaEnRango(coordenadaTercera, 0));

    }

    @Test
    public void test10aCrearUnJuegoConUnEnemigoSeGanaCuandoElEnemigoMuere() {
        Jugador jugador = Jugador.getInstance();
        jugador.reestablecerEstadoInicial();

        Logger logger = new Logger();
        Mapa mapa = new Mapa();

        Juego juego = new Juego(mapa, logger);
        juego.agregarSubscriptor(logger);

        Coordenada coordenadaTercera = new Coordenada(2, 0);
        Pasarela pasarelaTercera = new Pasarela(coordenadaTercera, new Normal());

        Coordenada coordenadaSegunda = new Coordenada(2,1);
        Pasarela pasarelaSegunda = new Pasarela(coordenadaSegunda, new Normal());
        pasarelaSegunda.agregarSiguiente(pasarelaTercera);

        Coordenada coordenadaPrimera = new Coordenada(2, 2);
        Pasarela pasarelaPrimera = new Pasarela(coordenadaPrimera, new Normal());
        pasarelaPrimera.agregarSiguiente(pasarelaSegunda);
        Enemigo arania = new Arania(pasarelaPrimera);

        juego.nuevoEnemigo(arania);

        assertFalse(juego.finalizado());

        arania.recibirDanio(2);
        juego.jugarTurno(1);

        assertTrue(jugador.estaVivo()); //Estos dos assert son equivalentes a decir que el juego esta en estado "ganado"
        assertTrue(juego.finalizado());
    }

    @Test
    public void test10bCrearUnJuegoConMuchosEnemigosSeGanaSoloCuandoTodosMueren() {

        Jugador jugador = Jugador.getInstance();
        jugador.reestablecerEstadoInicial();

        Logger logger = new Logger();
        Mapa mapa = new Mapa();

        Coordenada coordenada = new Coordenada(2, 2);
        Pasarela pasarelaFinal = new Pasarela(coordenada, new Meta());
        Pasarela pasarelaQuinta = new Pasarela(coordenada, pasarelaFinal, new Normal());
        Pasarela pasarelaCuarta = new Pasarela(coordenada, pasarelaQuinta, new Normal());
        Pasarela pasarelaTercera = new Pasarela(coordenada, pasarelaCuarta,  new Normal());
        Pasarela pasarelaSegunda = new Pasarela(coordenada, pasarelaTercera,  new Normal());
        Pasarela pasarelaPrimera = new Pasarela(coordenada, pasarelaSegunda,  new Normal());

        Arania araniaUno = new Arania(pasarelaPrimera);
        Arania araniaDos = new Arania(pasarelaPrimera);
        Arania araniaTres = new Arania(pasarelaPrimera);
        Arania araniaCuatro = new Arania(pasarelaPrimera);
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

        Juego juego = new Juego(mapa, logger);
        juego.agregarSubscriptor(logger);

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
        Jugador jugador = Jugador.getInstance();
        jugador.reestablecerEstadoInicial();

        Logger logger = new Logger();
        Mapa mapa = new Mapa();

        Juego juego = new Juego(mapa, logger);
        juego.agregarSubscriptor(logger);

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
        Jugador jugador = Jugador.getInstance();
        jugador.reestablecerEstadoInicial();

        Logger logger = new Logger();
        Mapa mapa = new Mapa();

        Juego juego = new Juego(mapa, logger);
        juego.agregarSubscriptor(logger);

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

        assertFalse(jugador.estaVivo()); //Estos dos assert son equivalentes a decir que el juego esta en estado "perdido"
        assertTrue(juego.finalizado());
    }
}



