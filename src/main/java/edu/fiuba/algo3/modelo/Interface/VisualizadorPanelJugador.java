package edu.fiuba.algo3.modelo.Interface;

import edu.fiuba.algo3.modelo.juego.Jugador;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class VisualizadorPanelJugador {
    public static HBox crearPanelJugador() {
        HBox hboxJugador = new HBox();
        hboxJugador.setStyle("-fx-background-color: #000000;");
        hboxJugador.setPadding(new Insets(0, 1000, 20, 0));

        String mensajeNombreYVida = String.format("\nLa vida de %s es %s",Jugador.getInstance().getNombre(), Jugador.getInstance().getVida() );

        Text nombreJugador = new Text(mensajeNombreYVida);
        nombreJugador.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        nombreJugador.setFill(Color.RED);
        nombreJugador.setWrappingWidth(1150);
        nombreJugador.setTextAlignment(TextAlignment.CENTER);

        hboxJugador.getChildren().add(nombreJugador);

        return hboxJugador;
    }
}