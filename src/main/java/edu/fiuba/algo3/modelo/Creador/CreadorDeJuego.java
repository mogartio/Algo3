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
import edu.fiuba.algo3.vista.VistaJugador;
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

    public static void crearJuego(String pathArchivoEnemigos, String pathArchivoMapa, int tamanioMapa) throws NoHayCamino, NoHayInicial {

        CreadorEnemigos creadorEnemigos = new CreadorEnemigos();
        VisualizadorDeMapa visualizadorDeMapa = new VisualizadorDeMapa(tamanioMapa);
        CreadorDeMapa creadorMapa = new CreadorDeMapa(pathArchivoMapa,tamanioMapa, visualizadorDeMapa);

        VistaSprays vistaSprays = new VistaSprays(visualizadorDeMapa);

        Mapa mapa = creadorMapa.crearMapa();
        LinkedList<ArrayList<Enemigo>> enemigos = creadorEnemigos.crearEnemigosDeNivel(pathArchivoEnemigos, vistaSprays);

        mapa.cargarOleadas(enemigos);

       /* Logger logger = new Logger();

        mapa.agregarSubscriptor(logger); //Sacar esto o hacer que el logger sea un observer, establecer la comunicacion*/

        Juego.getInstance().reestablecerJuego();
        Juego.getInstance().setMapa(mapa);
        Juego.getInstance().addObserver(new VistaEstadoJuego());
        Juego.getInstance().cargarObserverParaDefensas(vistaSprays);
        Juego.getInstance().setNombreDelJugador("test"); // Aca tendria que agregarse el nombre recibido por param

        Turnero turnero = new Turnero();

        VisualizadorPanelJugador visualizadorPanelJugador = new VisualizadorPanelJugador(visualizadorDeMapa);

        visualizadorPanelJugador.inicializarPanelJugador(turnero);

        VistaJugador vistaJugador = new VistaJugador(visualizadorPanelJugador);

        Juego.getInstance().cargarObserverParaJugador(vistaJugador);

        Juego.getInstance().notifyObservers();

        visualizadorDeMapa.mostrar();

        //return Juego.getInstance() ;
    }
}