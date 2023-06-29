package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.Enemigos.Sprayable;
import edu.fiuba.algo3.modelo.Interface.VentanaDeJuego;
import edu.fiuba.algo3.modelo.juego.EstadoJuego;
import edu.fiuba.algo3.modelo.juego.Juego;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class VistaEstadoJuego implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        Juego juego = (Juego) o;

        EstadoJuego estadoJuego = juego.obtenerNuevoEstado();
        //Ver que hacer con la info del estado del juego
        //VentanaDeJuego.getInstance(); //hacer algo con esto
    }
}
