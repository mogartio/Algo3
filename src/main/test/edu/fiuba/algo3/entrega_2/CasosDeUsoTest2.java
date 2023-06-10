package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Creador.CreadorEnemigos;
import edu.fiuba.algo3.modelo.Creador.CreadorDeMapa;
import edu.fiuba.algo3.modelo.Enemigos.Araña;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Hormiga;
import edu.fiuba.algo3.modelo.Excepciones.NoHayCamino;
import edu.fiuba.algo3.modelo.Observer.Logger;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.lectorJSON.Lector;
import edu.fiuba.algo3.modelo.lectorJSON.Mapa;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Normal;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class CasosDeUsoTest2 {
    @Test
    public void test13SeVerificaLaValidesDelJSONDeEnemigosDePrueba(){
        Lector lectorDeEnemigos = new Lector();
        ArrayList< HashMap<String,String> > enemigosEnArchivo = new ArrayList<>();
        JSONArray parseoDeEnemigos = null;

        HashMap<String,String> turno1EnArchivo = new HashMap<String,String>();
        HashMap<String,String> turno2EnArchivo = new HashMap<String,String>();
        HashMap<String,String> turno3EnArchivo = new HashMap<String,String>();

        turno1EnArchivo.put("arana","1");
        turno1EnArchivo.put("hormiga","2");
        turno2EnArchivo.put("arana","1");
        turno2EnArchivo.put("hormiga","0");
        turno3EnArchivo.put("arana","1");
        turno3EnArchivo.put("hormiga","1");

        enemigosEnArchivo.add(turno1EnArchivo);
        enemigosEnArchivo.add(turno2EnArchivo);
        enemigosEnArchivo.add(turno3EnArchivo);

        parseoDeEnemigos = Lector.leer("ArchivosJson/enemigosDePrueba");

        for (int numeroDeturno = 1; numeroDeturno <= parseoDeEnemigos.size(); numeroDeturno++){ //se inicializa el ciclo en 1 para que sea representativo
            JSONObject informacionDelTurno = (JSONObject) parseoDeEnemigos.get(numeroDeturno-1);

            JSONObject enemigosEnTurno = (JSONObject) informacionDelTurno.get("enemigos");
            String aranasEnTurno = enemigosEnTurno.get("arana").toString();
            String hormigasEnTurno = enemigosEnTurno.get("hormiga").toString();

            assertEquals(informacionDelTurno.get("turno").toString(),String.valueOf(numeroDeturno));
            assertEquals(enemigosEnArchivo.get(numeroDeturno-1).get("arana"),aranasEnTurno);
            assertEquals(enemigosEnArchivo.get(numeroDeturno-1).get("hormiga"),hormigasEnTurno);
        }


        //JSONObject resultado = (JSONObject) parseoDeEnemigos.get(0);

        // JSONArray filas = (JSONArray) parseoDeEnemigos.get();
    }

    @Test
    public void test14SeVerificaLaValidesDelJSONDeMapaDePrueba(){
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

        JSONArray parseoDeMapa = null;
        parseoDeMapa = Lector.leer("ArchivosJson/mapaDePrueba");

        JSONObject filas = (JSONObject) parseoDeMapa.get(1);//saltea a "Mapa" y obtiene un diccionario de filas

        for (int i = 1; i <= filas.size(); i++){ //por cada fila en filas

            JSONArray filaAComparar = (JSONArray) filas.get(String.valueOf(i)); //crea un array con lo que hay en la fila

            for (int j = 0; j < filaAComparar.size(); j++){ // por cada elemento en el en el JSONArray
                assertEquals(filaAComparar.get(j),filasEnArchivo.get(i-1).get(j));
            }
        }
    }

    @Test
    public void test15aJSONConUnSoloTurnoYUnaHormigaCreaAlEnemigoCorrectamente(){
        CreadorEnemigos creadorEnemigo = new CreadorEnemigos("ArchivosJson/tests/test15/enemigosTest15a.txt");
        Queue<ArrayList<Enemigo>> colaEnemigos = (Queue<ArrayList<Enemigo>>) creadorEnemigo.crearEnemigos();
        ArrayList<Enemigo> arrayList = colaEnemigos.remove();
        Enemigo enemigo = arrayList.remove(0);
        assertTrue(enemigo instanceof Hormiga);
        assertTrue(colaEnemigos.isEmpty());
        assertTrue(arrayList.isEmpty());
    }
    @Test
    public void test15bJSONConUnSoloTurnoYDosEnemigos(){
        CreadorEnemigos creadorEnemigo = new CreadorEnemigos("ArchivosJson/tests/test15/enemigoTest15b.txt");
        Queue<ArrayList<Enemigo>> colaEnemigos = (Queue<ArrayList<Enemigo>>) creadorEnemigo.crearEnemigos();
        ArrayList<Enemigo> arrayList = colaEnemigos.remove();
        Enemigo enemigoA = arrayList.remove(0);
        Enemigo enemigoB = arrayList.remove(0);
        assertTrue(enemigoA instanceof Hormiga && enemigoB instanceof Araña);
        assertTrue(colaEnemigos.isEmpty());
        assertTrue(arrayList.isEmpty());
    }

    @Test
    public void test15cJSONConVariosTurnosYVariosEnemigos(){
        CreadorEnemigos creadorEnemigo = new CreadorEnemigos("ArchivosJson/tests/test15/enemigosTest15c.txt");
        Queue<ArrayList<Enemigo>> colaEnemigos = (Queue<ArrayList<Enemigo>>) creadorEnemigo.crearEnemigos();
        while (colaEnemigos.isEmpty()) equals(false);{
            ArrayList<Enemigo> turno = colaEnemigos.remove();
            turno.forEach(enemigo -> assertTrue(enemigo instanceof Enemigo));
        }
    }

    @Test
    public void test16aElMapaCreadoNoEsNull() {
        CreadorDeMapa creadorMapa = new CreadorDeMapa("ArchivosJson/tests/Test16/mapaTest16");
        try {
            Mapa mapa = creadorMapa.crearMapa();
            assertNotNull(mapa);
        } catch (NoHayCamino noHayCamino) {
            fail();
        }
    }

    @Test
    public void test16bCrearUnMapaSinCaminoDevuelveExcepcion() {
        CreadorDeMapa creadorMapa = new CreadorDeMapa("ArchivosJson/tests/Test16/mapaSinCaminoTest16b");
        try {
            Mapa mapa = creadorMapa.crearMapa();
            fail();
        } catch (NoHayCamino noHayCamino) {
            assertTrue(true);
        }
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
        Pasarela pasarela = new Pasarela(coord, null, new Normal());

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
        Pasarela pasarela = new Pasarela(coord, null, new Normal());

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
        Pasarela pasarela = new Pasarela(coord, null, new Normal());

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
        Pasarela pasarela = new Pasarela(coord, null, new Normal());

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
