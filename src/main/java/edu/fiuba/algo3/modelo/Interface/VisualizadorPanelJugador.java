package edu.fiuba.algo3.modelo.Interface;

import edu.fiuba.algo3.modelo.Jugable;
import edu.fiuba.algo3.modelo.Turnero;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.Jugador;
import javafx.beans.Observable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class VisualizadorPanelJugador {
    public static HBox crearPanelJugador(Jugable turnero) {
        HBox hboxJugador = new HBox();
        hboxJugador.setSpacing(200);
        hboxJugador.setStyle("-fx-background-color: #000000;");
<<<<<<< HEAD
        hboxJugador.setPadding(new Insets(0, 0, 20, 400));
=======
        hboxJugador.setPadding(new Insets(0, 0, 20, 0));

        Button botonFinalizarTurno = new Button();
        BotonTurnero funcion = new BotonTurnero(botonFinalizarTurno, turnero);
        //botonFinalizarTurno.setPadding(new Insets(0,0, 50, 0));
        botonFinalizarTurno.setAlignment(Pos.CENTER_LEFT);

        botonFinalizarTurno.setText("Finalizar turno");
        botonFinalizarTurno.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        //botonFinalizarTurno.setPrefSize(400, 80);
        botonFinalizarTurno.setOnAction(funcion);
>>>>>>> 90fd9789bc93e130b4dd04caa24743d32ce5d75f

        String mensajeNombreYVida = String.format("\nLa vida de %s es %s",Jugador.getInstance().getNombre(), Jugador.getInstance().getVida() );

        Text nombreJugador = new Text(mensajeNombreYVida);
        nombreJugador.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        nombreJugador.setFill(Color.RED);
<<<<<<< HEAD

        Text creditosJugador = new Text(String.format("Créditos disponibles:\n%d", Jugador.getInstance().getCreditos()));
        creditosJugador.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        creditosJugador.setFill(Color.RED);
        creditosJugador.setTextAlignment(TextAlignment.CENTER);
        hboxJugador.getChildren().addAll(nombreJugador, creditosJugador);
=======
        nombreJugador.setWrappingWidth(1150);
        nombreJugador.setTextAlignment(TextAlignment.CENTER);

        hboxJugador.getChildren().add(nombreJugador);
        hboxJugador.getChildren().add(botonFinalizarTurno);
>>>>>>> 90fd9789bc93e130b4dd04caa24743d32ce5d75f

        return hboxJugador;
    }
}