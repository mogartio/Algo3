package edu.fiuba.algo3.modelo.Interface;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.Jugador;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class VisualizadorPanelJugador {
    public static HBox crearPanelJugador() {
        HBox hboxJugador = new HBox();
        hboxJugador.setStyle("-fx-background-color: #000000;");
        hboxJugador.setPadding(new Insets(0, 1000, 20, 0));

        String mensajeNombreYVida = String.format("\nLa vida de %s es %s", Juego.getInstance().getNombreDelJugador(), Juego.getInstance().getVidaDelJugador() );

        Text nombreJugador = new Text(mensajeNombreYVida);
        nombreJugador.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        nombreJugador.setFill(Color.RED);
        nombreJugador.setWrappingWidth(1150);
        nombreJugador.setTextAlignment(TextAlignment.CENTER);
        hboxJugador.getChildren().add(nombreJugador);

        return hboxJugador;
    }
}