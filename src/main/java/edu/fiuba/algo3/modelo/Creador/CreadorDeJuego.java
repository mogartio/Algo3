package edu.fiuba.algo3.modelo.Creador;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.NoHayCamino;
import edu.fiuba.algo3.modelo.Excepciones.NoHayInicial;
import edu.fiuba.algo3.modelo.Interface.VisualizadorDeMapa;
import edu.fiuba.algo3.modelo.Interface.VisualizadorPanelJugador;
import edu.fiuba.algo3.modelo.Turnero;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.lectorJSON.Lector;
import edu.fiuba.algo3.modelo.lectorJSON.Mapa;
import edu.fiuba.algo3.vista.VistaEstadoJuego;
import edu.fiuba.algo3.vista.VistaSprays;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.LinkedList;


//
// ELIMINAR ESTA CLASE
//


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
/*
        CreadorEnemigos creadorEnemigos = new CreadorEnemigos();
        VisualizadorDeMapa visualizadorDeMapa = new VisualizadorDeMapa(tamanioMapa);
        CreadorDeMapa creadorMapa = new CreadorDeMapa(pathArchivoMapa,tamanioMapa, visualizadorDeMapa);

        VistaSprays vistaSprays = new VistaSprays(visualizadorDeMapa);

        Mapa mapa = creadorMapa.crearMapa();
        LinkedList<ArrayList<Enemigo>> enemigos = creadorEnemigos.crearEnemigosDeNivel(pathArchivoEnemigos, vistaSprays);

        mapa.cargarOleadas(enemigos);

       /* Logger logger = new Logger();

        mapa.agregarSubscriptor(logger); //Sacar esto o hacer que el logger sea un observer, establecer la comunicacion*/

        Juego juego = new Juego(mapa, vistaSprays);

        visualizadorDeMapa.setJuego(juego);

        Turnero turnero = new Turnero(juego);

        HBox panelJugador = VisualizadorPanelJugador.crearPanelJugador(turnero);

        visualizadorDeMapa.agregarPanelJugador(panelJugador);

        visualizadorDeMapa.mostrar();

        juego.addObserver(new VistaEstadoJuego());
        */
        return Juego.getInstance() ;
    }
}