package edu.fiuba.algo3.modelo.Creador;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.NoHayCamino;
import edu.fiuba.algo3.modelo.Jugable;
import edu.fiuba.algo3.modelo.Observer.Logger;
import edu.fiuba.algo3.modelo.Turnero;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.lectorJSON.Lector;
import edu.fiuba.algo3.modelo.lectorJSON.Mapa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CreadorDeJuego { // fachada

    /*private CreadorEnemigos creadorDeEnemigos;
    private CreadorDeMapa creadorDeMapa;
    private Lector lectorDeArchivos;

    public CreadorDeJuego() {
        this.creadorDeEnemigos = new CreadorEnemigos();
        this.creadorDeMapa = new CreadorDeMapa();
        this.lectorDeArchivos = new Lector();
    }*/

    public static Jugable crearJugable(String pathArchivoEnemigos, String pathArchivoMapa) throws NoHayCamino {

        CreadorEnemigos creadorEnemigos = new CreadorEnemigos(pathArchivoEnemigos);
        CreadorDeMapa creadorMapa = new CreadorDeMapa(pathArchivoMapa);

        Mapa mapa = creadorMapa.crearMapa();
        LinkedList<ArrayList<Enemigo>> enemigos = creadorEnemigos.crearEnemigos();

        mapa.cargarOleadas(enemigos);

        Logger logger = new Logger();

        Juego juego = new Juego(mapa);

        juego.agregarSubscriptor(logger);

        Turnero turnero = new Turnero(juego);

        return turnero;
    }
}
