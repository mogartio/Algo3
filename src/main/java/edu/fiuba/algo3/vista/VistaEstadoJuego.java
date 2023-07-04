package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.Enemigos.Sprayable;
import edu.fiuba.algo3.modelo.Interface.AudioPlayer;
import edu.fiuba.algo3.modelo.Interface.VentanaDeJuego;
import edu.fiuba.algo3.modelo.Interface.VisualizadorDeMapa;
import edu.fiuba.algo3.modelo.juego.EstadoJuego;
import edu.fiuba.algo3.modelo.juego.Juego;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class VistaEstadoJuego implements Observer {
    VisualizadorDeMapa visualizadorDeMapa;

    public VistaEstadoJuego(VisualizadorDeMapa visualizadorDeMapa) {
        this.visualizadorDeMapa = visualizadorDeMapa;
    }

    @Override
    public void update(Observable o, Object arg) {
        Juego juego = (Juego) o;
        EstadoJuego estadoJuego = juego.obtenerNuevoEstado();
        if(arg == "Turno") {
            visualizadorDeMapa.borrarEnemigosDelTurnoAnterior();
        }
        try {
            ImageView mensajeFinal = ConstanteImagenes.getImagen(estadoJuego.versionString());
            mensajeFinal.setFitWidth(1000);
            mensajeFinal.setFitHeight(200);
            visualizadorDeMapa.mostrarMensajeFinal(mensajeFinal);
            AudioPlayer.playEfectoSonido(estadoJuego.versionString());

        } catch (FileNotFoundException e) {
        } catch (NullPointerException e){
        }
    }
}
