package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.Interface.VisualizadorPanelJugador;
import edu.fiuba.algo3.modelo.juego.Jugador;

import java.util.Observable;
import java.util.Observer;

public class VistaJugador implements Observer {

    VisualizadorPanelJugador visualizadorPanelJugador;

    public VistaJugador(VisualizadorPanelJugador visualizadorPanelJugador) {
        this.visualizadorPanelJugador = visualizadorPanelJugador;
    }

    @Override
    public void update(Observable o, Object arg) {
        Jugador jugador = (Jugador) o;

        visualizadorPanelJugador.updateInfo(jugador.getNombre() , jugador.getVida(), jugador.getCreditos());
    }
}
