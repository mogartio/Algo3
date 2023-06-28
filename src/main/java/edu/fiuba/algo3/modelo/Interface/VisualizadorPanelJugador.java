package edu.fiuba.algo3.modelo.Interface;

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
        hboxJugador.setSpacing(200);
        hboxJugador.setStyle("-fx-background-color: #000000;");
        hboxJugador.setPadding(new Insets(0, 0, 20, 400));

        String mensajeNombreYVida = String.format("\nLa vida de %s es %s",Jugador.getInstance().getNombre(), Jugador.getInstance().getVida() );

        Text nombreJugador = new Text(mensajeNombreYVida);
        nombreJugador.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        nombreJugador.setFill(Color.RED);

        Text creditosJugador = new Text(String.format("Créditos disponibles:\n%d", Jugador.getInstance().getCreditos()));
        creditosJugador.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        creditosJugador.setFill(Color.RED);
        creditosJugador.setTextAlignment(TextAlignment.CENTER);
        hboxJugador.getChildren().addAll(nombreJugador, creditosJugador);

        return hboxJugador;
    }
}