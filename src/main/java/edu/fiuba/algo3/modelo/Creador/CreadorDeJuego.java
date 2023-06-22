package edu.fiuba.algo3.modelo.Creador;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.NoHayCamino;
import edu.fiuba.algo3.modelo.Excepciones.NoHayInicial;
import edu.fiuba.algo3.modelo.Interface.VentanaDeJuego;
import edu.fiuba.algo3.modelo.Interface.VisualizadorDeMapa;
import edu.fiuba.algo3.modelo.ObserverPropio.Logger;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.lectorJSON.Lector;
import edu.fiuba.algo3.modelo.lectorJSON.Mapa;
import edu.fiuba.algo3.vista.VistaEstadoJuego;
import edu.fiuba.algo3.vista.VistaSprays;

import java.util.ArrayList;
import java.util.LinkedList;

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
        VisualizadorDeMapa visualizadorDeMapa = new VisualizadorDeMapa(tamanioMapa);
        CreadorDeMapa creadorMapa = new CreadorDeMapa(pathArchivoMapa,tamanioMapa, visualizadorDeMapa);

        VistaSprays vistaSprays = new VistaSprays(visualizadorDeMapa);

        Mapa mapa = creadorMapa.crearMapa();
        LinkedList<ArrayList<Enemigo>> enemigos = creadorEnemigos.crearEnemigosDeNivel(pathArchivoEnemigos);

        mapa.cargarOleadas(enemigos);

        Logger logger = new Logger();

        mapa.agregarSubscriptor(logger); //Sacar esto o hacer que el logger sea un observer, establecer la la comunicacion

        Juego juego = new Juego(mapa, vistaSprays);

        juego.addObserver(new VistaEstadoJuego());

        return juego;
    }
}
