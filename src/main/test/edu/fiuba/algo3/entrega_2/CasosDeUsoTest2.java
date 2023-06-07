package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Observer.Logger;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CasosDeUsoTest2 {

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
