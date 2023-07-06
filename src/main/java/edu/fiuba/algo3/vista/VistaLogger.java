package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.Interface.AudioPlayer;
import edu.fiuba.algo3.Interface.VisualizadorLogger;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.juego.EstadoJuego;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.Jugador;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class VistaLogger implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        ArrayList<String> mensajes = obtenerMensajes(o);
        if(mensajes.size() != 0) {
            for (String mensaje : mensajes) {
                VisualizadorLogger.getInstance().mostrarMensaje("Log: " + mensaje);
            }
        }
    }

    private ArrayList<String> obtenerMensajes(Observable loggeable) {
        ArrayList<String> mensajes = new ArrayList<>();
        Field eventos;

        try { //Para Jugador y Juego
            eventos = loggeable.getClass().getSuperclass().getDeclaredField("eventos");
            mensajes = (ArrayList<String>) eventos.get(loggeable);
            eventos.set(loggeable, new ArrayList<String>());
        } catch (NoSuchFieldException | IllegalAccessException e) {
        }
        try { // Para enemigos y defensas
            eventos = loggeable.getClass().getSuperclass().getSuperclass().getSuperclass().getDeclaredField("eventos");
            mensajes = (ArrayList<String>) eventos.get(loggeable);
            eventos.set(loggeable, new ArrayList<String>());
        } catch (NoSuchFieldException | IllegalAccessException e) {
        }
        return mensajes;
    }
}
