package edu.fiuba.algo3.entrega_1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.fiuba.algo3.modelo.Defensas.*;
import edu.fiuba.algo3.modelo.Enemigos.Araña;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Hormiga;
import edu.fiuba.algo3.modelo.Excepciones.PasarelaInexistente;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.lectorJSON.Mapa;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.miscelanea.Tienda;
import edu.fiuba.algo3.modelo.miscelanea.Vida;
import edu.fiuba.algo3.modelo.parcelas.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.LinkedList;


public class CasosDeUsoTest {

    @Test
    public void test01LaVidaYCreditosDelJugadorSonLosCorrectosAlEmpezar() {
        //el jugador comienza con 20 puntos de vida y con 100 creditos
        Jugador jugador = Jugador.getInstance();
        Tienda proveedor = new Tienda();

        for (int i=1 ; i <= 5 ; i++) { //se gastan los creditos
            Defensa torre1 = jugador.comprar(proveedor,"TorrePlateada");
        }
        ArrayList<String> torresDisponibles = jugador.verificarConstruccionesPosibles(proveedor); //se obtienen el listado de compras posibles

        for (int i=0 ; i <= 19 ; i++){
            Jugador.getInstance().recibirDaño(1);
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
        EstadoConstruccion estadoInicial = new EnConstruccion(2);

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

    //CREAR UN METODO DE REGENERACION DE JUGADOR
    /*@Test
    public void test03SeVerficaQueElJugadorDispongaDeCreditoParaConstruirTorres() {
        // se crea un jugador con los recursos base (tambien podrian ser otros)
        Jugador jugador = Jugador.getInstance();

        //se crea la tienda que sera quien proveera y verificara las torres comprables
        Tienda proveedor = new Tienda();

        //el jugador le envia sus creditos a instancia de tienda para que esta provea una
        //lista con las estructuras que este puede comprar
        ArrayList<String> torresComprables = jugador.verificarConstruccionesPosibles(proveedor);

        //assertions
        assertTrue(torresComprables.contains("TorrePlateada"));
        assertTrue(torresComprables.contains("TorreBlanca"));
    }*/

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

        Enemigo enemigo = new Araña();
        enemigo.actualizarPosicionActual(pasarela);

        Enemigo enemigo2 = new Araña();
        enemigo2.actualizarPosicionActual(pasarela2);

        ArrayList<Enemigo> listaDeEnemigos = new ArrayList<Enemigo>();
        listaDeEnemigos.add(enemigo);
        listaDeEnemigos.add(enemigo2);

        TorrePlateada torre = new TorrePlateada();
        torre.asignarPosicion(new Coordenada(0,0));
        torre.pasarTurno();

        torre.atacar(listaDeEnemigos);

        assertFalse(enemigo.estaVivo());

        listaDeEnemigos.remove(0);

        torre.pasarTurno();
        torre.atacar(listaDeEnemigos);

        assertTrue(enemigo2.estaVivo());

    }

    @Test
    public void test06VerificarQueLosEnemigosSonDañadosAcordeAlAtaqueRecibido() {
        Coordenada coordenada = new Coordenada(3, 0);
        Pasarela pasarela1 = new Pasarela(coordenada, new Normal());
        Pasarela pasarela2 = new Pasarela(coordenada, new Normal());

        Enemigo enemigo = new Araña();
        Enemigo enemigo2 = new Hormiga();
        enemigo.actualizarPosicionActual(pasarela1);
        enemigo2.actualizarPosicionActual(pasarela2);

        ArrayList<Enemigo> listaEnemigos = new ArrayList<Enemigo>();
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
    public void test07aVerificarQueLasArañasSeDesplazanLaCantidadCorrectaDeParcelas() throws PasarelaInexistente {

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

        ArrayList<Enemigo> lista = new ArrayList<Enemigo>();

        Enemigo enemigo = new Araña();
        enemigo.actualizarPosicionActual(pasarelaInicial);

        lista.add(enemigo);

        enemigo.avanzar();
        torre.atacar(lista); // la araña debe estar fuera del rango de ataque de la torre por ende estaria viva


        assertTrue(enemigo.estaVivo());
    }

    @Test
    public void test07bVerificarQueLasArañasSeDesplazanLaCantidadCorrectaDeParcelas() throws PasarelaInexistente {

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

        ArrayList<Enemigo> lista = new ArrayList<Enemigo>();

        Enemigo enemigo = new Araña();
        enemigo.actualizarPosicionActual(pasarelaInicial);

        lista.add(enemigo);

        enemigo.avanzar();
        torre.atacar(lista); // la araña debe estar dentro del rango de ataque de la torre por ende estaria muerta


        assertFalse(enemigo.estaVivo());
    }

    /*@Test
    public void test08aHormigaDaCreditosCorrespondientesAlMorirSinMultiplicador() {
        Jugador.getInstance();
        int creditoInicial = Jugador.getInstance().obtenerCreditos();

        Coordenada coordenadaInicializadora = new Coordenada(1, 1);
        Pasarela pasarelaInicializadora = new PasarelaIntermedia(coordenadaInicializadora, null);

        for (int i=1; i<9; i++){
            Enemigo hormiga = new Hormiga(pasarelaInicializadora);
            hormiga.morir();
            assertEquals((i + creditoInicial), Jugador.getInstance().obtenerCreditos());
        }
    }

    @Test
    @Order(11)
    public void test08bHormigaDaCreditosCorrespondientesAlMorirConMultiplicador() { //El multiplicador ya esta aplicado porque el jugador mato a 9 hormigas en la prueba anterior
        Jugador jugador = Jugador.getInstance();
        int creditoInicial = Jugador.getInstance().obtenerCreditos();
        Coordenada coordenadaInicializadora = new Coordenada(1, 1);
        Pasarela pasarelaInicializadora = new PasarelaIntermedia(coordenadaInicializadora, null);

        for (int i=1; i<10; i++){
            Enemigo hormiga = new Hormiga(pasarelaInicializadora);
            hormiga.morir();
            assertEquals((i * 2 + creditoInicial), Jugador.getInstance().obtenerCreditos());
        }
    }

    @Test
    @Order(12)
    public void test08cArañaDaCreditosCorrespondientesAlMorir() {
        Jugador jugador = Jugador.getInstance();
        int creditosIniciales = jugador.getInstance().obtenerCreditos();
        assertFalse((Jugador.getInstance().obtenerCreditos() > 10 + creditosIniciales) || (Jugador.getInstance().obtenerCreditos() < 0 + creditosIniciales));
    }*/

    @Test
    public void test09aCrearHormigaYAvanzarHaceQueAvanceUnaPosicion() throws PasarelaInexistente {
        Coordenada coordenadaMedio = new Coordenada(1, 2);
        Pasarela pasarelaMedio = new Pasarela(coordenadaMedio, new Normal());

        Coordenada coordenadaInicial = new Coordenada(1, 3);
        Pasarela pasarelaInicial = new Pasarela(coordenadaInicial, new Normal());

        pasarelaInicial.agregarSiguiente(pasarelaMedio);

        Defensa torre = new TorrePlateada();
        torre.asignarPosicion(new Coordenada(3,4));

        ArrayList<Enemigo> lista = new ArrayList<Enemigo>();

        Enemigo enemigo = new Hormiga();
        enemigo.actualizarPosicionActual(pasarelaInicial);

        lista.add(enemigo);

        enemigo.avanzar();
        torre.atacar(lista); // la hormiga debe estar dentro del rango de ataque de la torre por ende estaria muerta


        assertFalse(enemigo.estaVivo());
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

        Enemigo arañaUno = new Araña(pasarelaPrimera);
        Enemigo arañaDos = new Araña(pasarelaSegunda);
        Enemigo hormigaUno = new Hormiga(pasarelaTercera);
        Enemigo hormigaDos = new Hormiga(pasarelaPrimera);


       Mapa mapaJuego = new Mapa();
       mapaJuego.setPasarelaInicial(pasarelaPrimera);
       mapaJuego.agregar(coordenadaPrimera, pasarelaPrimera);
       mapaJuego.agregar(coordenadaSegunda, pasarelaSegunda);
       mapaJuego.agregar(coordenadaTercera, pasarelaTercera);
       mapaJuego.agregar(coordenadaFinal, pasarelaFinal);

       LinkedList<ArrayList<Enemigo>> oleadas = new LinkedList<ArrayList<Enemigo>>();
       mapaJuego.cargarOleadas(oleadas);

       Juego juego = new Juego(mapaJuego);

       juego.nuevoEnemigo(arañaUno);
       juego.nuevoEnemigo(arañaDos);
       juego.nuevoEnemigo(hormigaUno);
       juego.nuevoEnemigo(hormigaDos);


       juego.jugarTurno(1);

       assertTrue(hormigaDos.estaEnRango(coordenadaSegunda, 0));
       assertTrue(hormigaUno.estaEnRango(coordenadaFinal, 0));
       assertTrue(arañaDos.estaEnRango(coordenadaFinal, 0));
       assertTrue(arañaUno.estaEnRango(coordenadaTercera, 0));

    }

    /*@Test
    public void test10aCrearUnJuegoConUnEnemigoSeGanaCuandoElEnemigoMuere() {
        Jugador.getInstance().regenerar();
        Vida vida = new Vida(20);
        Juego juego = new Juego();

        Coordenada coordenadaPrimera = new Coordenada(2, 2);
        Pasarela pasarelaPrimera = new Pasarela(coordenadaPrimera, null);
        Enemigo araña = new Araña(pasarelaPrimera);

        juego.nuevoEnemigo(araña);

    }

   /* @Test
    @Order(17)
    public void test10bCrearUnJuegoConMuchosEnemigosSeGanaSoloCuandoTodosMueren() {

        Vida vida = new Vida(20);
        Jugador jugador = Jugador.getInstance();

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

        Juego juego = new Juego();
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
    }*/
    /*@Test
    @Order(18)
    public void test11ElJugadorSobreviveConUnEnemigoLlegandoALaMetaYGanaIgual() {
        Jugador jugador = Jugador.getInstance();
        Juego juego = new Juego();

        Coordenada coordenadaFinal = new Coordenada(2,3);
        Pasarela pasarelaFinal = new PasarelaFinal(coordenadaFinal, null);

        Coordenada coordenadaPrimera = new Coordenada(2, 2);
        Pasarela pasarelaPrimera = new PasarelaIntermedia(coordenadaPrimera, pasarelaFinal);

        Enemigo hormiga = new Hormiga(pasarelaPrimera);
        juego.nuevoEnemigo(hormiga);
        juego.pasarTurno();
        assertTrue(juego.estado() instanceof Ganado);
    }

    @Test
    @Order(19)
    public void test12ElJugadorMuereYPierdeElJuego() {
        Jugador jugador = Jugador.getInstance();
        Juego juego = new Juego();

        Coordenada coordenadaFinal = new Coordenada(2,3);
        Pasarela pasarelaFinal = new PasarelaFinal(coordenadaFinal, null);

        Coordenada coordenadaPrimera = new Coordenada(2, 2);
        Pasarela pasarelaPrimera = new PasarelaIntermedia(coordenadaPrimera, pasarelaFinal);

        int vidaInicial = Jugador.getInstance().obtenerVida();

        for(int i=0; i<vidaInicial; i++){
            Enemigo hormiga = new Hormiga(pasarelaPrimera);
            juego.nuevoEnemigo(hormiga);
            juego.pasarTurno();
        }
        assertTrue(juego.estado() instanceof Perdido);
    }*/
}



