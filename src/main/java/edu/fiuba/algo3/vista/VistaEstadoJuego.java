package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.Interface.AudioPlayer;
import edu.fiuba.algo3.Interface.VisualizadorDeMapa;
import edu.fiuba.algo3.modelo.Defensas.EstadoDeConstruccion.EstadoConstruccion;
import edu.fiuba.algo3.modelo.juego.EstadoJuego;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
        String representacionEstado = verEstado(juego);
        if(arg == "Turno") {
            visualizadorDeMapa.borrarEnemigosDelTurnoAnterior();
        }
        try {
            ImageView mensajeFinal = ConstanteImagenes.getImagen(representacionEstado);
            mensajeFinal.setFitWidth(700);
            mensajeFinal.setFitHeight(200);
            visualizadorDeMapa.mostrarMensajeFinal(mensajeFinal);
            AudioPlayer.playEfectoSonido(representacionEstado);

        } catch (FileNotFoundException | NullPointerException e) {
        }
    }

    private String verEstado(Juego juego) {
        String nombre = "";
        Field field;

        try {
            field = juego.getClass().getDeclaredField("estadoJuego");
            field.setAccessible(true);
            EstadoJuego est = (EstadoJuego) field.get(juego);
            field = est.getClass().getDeclaredField("versionString");
            nombre = (String) field.get(est);
            field.set(est, "");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Me rompi en verEstado en VistaEstadoJuego" + e.getMessage());
        }

        return nombre;
    }
}
