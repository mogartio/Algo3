package edu.fiuba.algo3.modelo.Creador;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.NoHayCamino;
import edu.fiuba.algo3.modelo.Excepciones.NoHayInicial;
import edu.fiuba.algo3.modelo.Jugable;
import edu.fiuba.algo3.modelo.Observer.Logger;
import edu.fiuba.algo3.modelo.Turnero;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.lectorJSON.Lector;
import edu.fiuba.algo3.modelo.lectorJSON.Mapa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CreadorDeJuego {

    private CreadorEnemigos creadorDeEnemigos;
    private CreadorDeMapa creadorDeMapa;
    private Lector lectorDeArchivos;

    public CreadorDeJuego() {
        this.creadorDeEnemigos = null;
        this.creadorDeMapa = null;
        this.lectorDeArchivos = new Lector();
    }

    public static Juego crearJuego(String pathArchivoEnemigos, String pathArchivoMapa, int tamanioMapa) throws NoHayCamino, NoHayInicial {

        CreadorEnemigos creadorEnemigos = new CreadorEnemigos();
        CreadorDeMapa creadorMapa = new CreadorDeMapa(pathArchivoMapa,tamanioMapa);

        Mapa mapa = creadorMapa.crearMapa();
        LinkedList<ArrayList<Enemigo>> enemigos = creadorEnemigos.crearEnemigosDeNivel(pathArchivoEnemigos);

        mapa.cargarOleadas(enemigos);

        Logger logger = new Logger();

        mapa.agregarSubscriptor(logger);
        Juego juego = new Juego(mapa, logger);

        juego.agregarSubscriptor(logger);

        return juego;
    }
}
