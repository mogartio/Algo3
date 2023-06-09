package edu.fiuba.algo3.modelo.Creador;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.lectorJSON.Lector;

public class CreadorDeJuego { // fachada

    private CreadorEnemigos creadorDeEnemigos;
    private CreadorDeMapa creadorDeMapa;
    private Lector lectorDeArchivos;


    public CreadorDeJuego() {
        this.creadorDeEnemigos = new CreadorEnemigos();
        this.creadorDeMapa = new CreadorDeMapa();
        this.lectorDeArchivos = new Lector();
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
