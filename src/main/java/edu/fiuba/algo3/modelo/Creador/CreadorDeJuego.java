package edu.fiuba.algo3.modelo.Creador;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
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

    public static Juego crearJuego(String pathArchivoEnemigos, String pathArchivoMapa){

        CreadorEnemigos creadorEnemigos = new CreadorEnemigos(pathArchivoEnemigos);
        CreadorDeMapa creadorMapa = new CreadorDeMapa(pathArchivoMapa);

        Mapa mapa = creadorMapa.crearMapa();
        LinkedList<ArrayList<Enemigo>> enemigos = creadorEnemigos.crearEnemigos();

        mapa.cargarOleadas(enemigos);

        Juego juego = new Juego(mapa);
    }
/*
    public Juego crearJuego(String rutaArchivoDeMapa , String rutaArchivoDeEnemigos){

        lectorDeArchivos.leer(rutaArchivoDeEnemigos);
        //creadorDeEnemigos.crearNivel();

        lectorDeArchivos.leer(rutaArchivoDeMapa);
        // mapa = creadorDeMapa.crearMapa();
        Juego juego = new juego();
        return juego;
    }
*/
}
