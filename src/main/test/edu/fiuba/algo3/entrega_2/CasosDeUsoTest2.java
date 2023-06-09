package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Creador.Creador;
import edu.fiuba.algo3.modelo.Creador.CreadorEnemigos;
import edu.fiuba.algo3.modelo.Creador.CreadorDeMapa;
import edu.fiuba.algo3.modelo.Enemigos.Araña;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Hormiga;
import edu.fiuba.algo3.modelo.Observer.Logger;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.lectorJSON.Lector;
import edu.fiuba.algo3.modelo.lectorJSON.Mapa;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;
import edu.fiuba.algo3.modelo.parcelas.PasarelaIntermedia;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class CasosDeUsoTest2 {
  /*  @Test
    public void test13SeVerificaLaValidesDelJSONDeEnemigos(){
        Lector lectorDeEnemigos = new Lector();

        JSONArray parseoDeEnemigos = null;
        parseoDeEnemigos = Lector.leer("ArchivosJson/enemigosDePrueba.json");

        //JSONObject resultado = (JSONObject) parseoDeEnemigos.get(0);
        System.out.println(parseoDeEnemigos);
        // JSONArray filas = (JSONArray) parseoDeEnemigos.get();
    }*/

    @Test
    public void test14SeVerificaLaValidesDelJSONDeMapa(){
        Lector lectorDeEnemigos = new Lector();
        ArrayList <ArrayList<String> > filasEnArchivo = new ArrayList<ArrayList<String> >();
        filasEnArchivo.add(new ArrayList<String>(Arrays.asList(
                "Rocoso","Pasarela","Tierra","Tierra","Tierra"
        )));
        filasEnArchivo.add(new ArrayList<String>(Arrays.asList(
                "Tierra","Pasarela","Tierra","Tierra","Tierra"
        )));
        filasEnArchivo.add(new ArrayList<String>(Arrays.asList(
                "Tierra","Pasarela","Tierra","Tierra","Tierra"
        )));

        JSONArray parseoDeEnemigos = null;
        parseoDeEnemigos = Lector.leer("ArchivosJson/mapaDePrueba");

        JSONObject filas = (JSONObject) parseoDeEnemigos.get(1);//saltea a "Mapa" y obtiene un diccionario de filas

        for (int i = 1; i <= filas.size(); i++){ //por cada fila en filas

            JSONArray filaAComparar = (JSONArray) filas.get(String.valueOf(i)); //crea un array con lo que hay en la fila

            for (int j = 0; j < filaAComparar.size(); j++){ // por cada elemento en el en el JSONArray
                assertEquals(filaAComparar.get(j),filasEnArchivo.get(i-1).get(j));
            }
        }
    }

    @Test
    public void test15aJSONConUnSoloTurnoYUnaHormigaCreaAlEnemigoCorrectamente(){
        Creador creadorEnemigo = new CreadorEnemigos();
        Queue<ArrayList<Enemigo>> colaEnemigos = (Queue<ArrayList<Enemigo>>) creadorEnemigo.crear("ArchivosJson/tests/test15/enemigosTest15a.txt");
        ArrayList<Enemigo> arrayList = colaEnemigos.remove();
        Enemigo enemigo = arrayList.remove(0);
        assertTrue(enemigo instanceof Hormiga);
        assertTrue(colaEnemigos.isEmpty());
        assertTrue(arrayList.isEmpty());
    }
    @Test
    public void test15bJSONConUnSoloTurnoYDosEnemigos(){
        Creador creadorEnemigo = new CreadorEnemigos();
        Queue<ArrayList<Enemigo>> colaEnemigos = (Queue<ArrayList<Enemigo>>) creadorEnemigo.crear("ArchivosJson/tests/test15/enemigoTest15b.txt");
        ArrayList<Enemigo> arrayList = colaEnemigos.remove();
        Enemigo enemigoA = arrayList.remove(0);
        Enemigo enemigoB = arrayList.remove(0);
        assertTrue(enemigoA instanceof Hormiga && enemigoB instanceof Araña);
        assertTrue(colaEnemigos.isEmpty());
        assertTrue(arrayList.isEmpty());
    }

    @Test
    public void test15cJSONConVariosTurnosYVariosEnemigos(){
        Creador creadorEnemigo = new CreadorEnemigos();
        ArrayList<ClassValue> arrayAComparar = new ArrayList<>();
        Queue<ArrayList<Enemigo>> colaEnemigos = (Queue<ArrayList<Enemigo>>) creadorEnemigo.crear("ArchivosJson/tests/test15/enemigosTest15c.txt");
        while (colaEnemigos.isEmpty()) equals(false);{
            ArrayList<Enemigo> turno = colaEnemigos.remove();
            turno.forEach(enemigo -> assertTrue(enemigo instanceof Enemigo));
        }
    }

    @Test
    public void test16aElMapaCreadoNoEsNull() {
        Creador creadorMapa = new CreadorDeMapa();
        Mapa mapa = (Mapa) creadorMapa.crear("ArchivosJson/tests/Test16/mapaTest16");
        assertFalse(mapa.equals(null));
    }

    /* @Test
    public void test16b() {
        Creador creadorMapa = new CreadorDeMapa();
        Mapa mapa = (Mapa) creadorMapa.crear("ArchivosJson/tests/Test16/mapaTest16");
        String arrayAComparar[] = new String[] {
                "Rocoso","Pasarela","Tierra","Tierra","Tierra","Tierra","Tierra","Tierra","Tierra","Tierra","Rocoso","Rocoso","Rocoso","Rocoso","Rocoso"
        };
        for(int i=1; i<arrayAComparar.length; i++){
            Coordenada coordenada = new Coordenada(1, 1);
            assertEquals(mapa.ver(coordenada), Array.get(arrayAComparar,0));
        }
    } */





    @Test
    public void test20aSiNoSubsriboAlLoggerNoCausaQueElLoggerRecibaUnaNotificacion() {
        Logger logger = new Logger();

        assertTrue(logger.verificarCantidadDeMensajesObservados(0));

        Jugador jugador = Jugador.getInstance();
        //jugador.agregarSubscriptor(logger);
        jugador.recibirDaño(1);

        assertTrue(logger.verificarCantidadDeMensajesObservados(0));
    }

    @Test
    public void test20bDañarAUnJugadorCausaQueElLoggerRecibaUnaNotificacion() {
        Logger logger = new Logger();

        assertTrue(logger.verificarCantidadDeMensajesObservados(0));

        Jugador jugador = Jugador.getInstance();
        jugador.agregarSubscriptor(logger);
        jugador.recibirDaño(1);

        assertTrue(logger.verificarCantidadDeMensajesObservados(1));
    }

    @Test
    public void test20cRecompensarAUnJugadorCausaQueElLoggerRecibaUnaNotificacion() {
        Logger logger = new Logger();

        assertTrue(logger.verificarCantidadDeMensajesObservados(0));

        Jugador jugador = Jugador.getInstance();
        jugador.agregarSubscriptor(logger);

        assertTrue(logger.verificarCantidadDeMensajesObservados(0));

        jugador.recompensar(10, false);

        assertTrue(logger.verificarCantidadDeMensajesObservados(1));
    }

    @Test
    public void test20dUnaHormigaQueMuereCausaQueElLoggerRecibaUnaNotificacion() {
        Logger logger = new Logger();
        Coordenada coord = new Coordenada(10, 20);
        Pasarela pasarela = new PasarelaIntermedia(coord, null);

        Hormiga hormiga = new Hormiga(pasarela);
        hormiga.agregarSubscriptor(logger);

        assertTrue(logger.verificarCantidadDeMensajesObservados(0));

        hormiga.morir(); //Aunque active 2 eventos, al no estar el logger suscrito al jugador, el logger no recibe la notificacion
        assertTrue(logger.verificarCantidadDeMensajesObservados(1));
    }

    @Test
    public void test20eUnaArañaQueMuereCausaQueElLoggerRecibaUnaNotificacion() {
        Logger logger = new Logger();
        Coordenada coord = new Coordenada(10, 20);
        Pasarela pasarela = new PasarelaIntermedia(coord, null);

        Araña araña = new Araña(pasarela);
        araña.agregarSubscriptor(logger);

        assertTrue(logger.verificarCantidadDeMensajesObservados(0));

        araña.morir(); //Aunque active 2 eventos, al no estar el logger suscrito al jugador, el logger no recibe la notificacion
        assertTrue(logger.verificarCantidadDeMensajesObservados(1));
    }

    @Test
    public void test20fAgregarUnEnemigoAlJuegoCausaQueElLoggerRecibaUnaNotificacion() {
        Logger logger = new Logger();
        Coordenada coord = new Coordenada(10, 20);
        Pasarela pasarela = new PasarelaIntermedia(coord, null);

        Araña araña = new Araña(pasarela);
        Juego juego = new Juego();
        assertTrue(logger.verificarCantidadDeMensajesObservados(0));
        juego.agregarSubscriptor(logger);

        juego.nuevoEnemigo(araña);
        assertTrue(logger.verificarCantidadDeMensajesObservados(1));
    }

    @Test
    public void test20gSubscribirElMismoLoggerADistintosObservablesCausaQueSeAcumulenLasNotificaciones() {
        Logger logger = new Logger();
        Coordenada coord = new Coordenada(10, 20);
        Pasarela pasarela = new PasarelaIntermedia(coord, null);

        Araña araña = new Araña(pasarela);
        Hormiga hormiga = new Hormiga(pasarela);
        Juego juego = new Juego();
        Jugador jugador = Jugador.getInstance();

        juego.agregarSubscriptor(logger);
        araña.agregarSubscriptor(logger);
        hormiga.agregarSubscriptor(logger);
        jugador.agregarSubscriptor(logger);

        assertTrue(logger.verificarCantidadDeMensajesObservados(0));

        //Serie de eventos que notfican al logger
        juego.nuevoEnemigo(araña);
        jugador.recompensar(10, false);
        hormiga.morir(); //Activa 2 eventos, porque muere y recompensa al jugador
        araña.morir(); //Activa 2 eventos, porque muere y recompensa al jugador

        assertTrue(logger.verificarCantidadDeMensajesObservados(6));
    }
}
