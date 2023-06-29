package edu.fiuba.algo3.modelo.Interface;

import edu.fiuba.algo3.modelo.Jugable;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.Jugador;
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

        VisualizadorDeMapa visualizadorGeneral;

        Button botonTerminarTurno;
        Text textoCreditos;
        Text textoNombreYVida;
        public VisualizadorPanelJugador(VisualizadorDeMapa visualizadorGeneral) {
                this.visualizadorGeneral =  visualizadorGeneral;

                Text creditosDisponibles = new Text();
                creditosDisponibles.setTextAlignment(TextAlignment.CENTER);
                creditosDisponibles.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                creditosDisponibles.setFill(Color.RED);
                creditosDisponibles.setWrappingWidth(400);

                this.textoCreditos = creditosDisponibles;

                Text nombreYVidaJugador = new Text();
                nombreYVidaJugador.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                nombreYVidaJugador.setFill(Color.RED);
                nombreYVidaJugador.setWrappingWidth(400);
                nombreYVidaJugador.setTextAlignment(TextAlignment.CENTER);

                this.textoNombreYVida = nombreYVidaJugador;
        }

        public void inicializarPanelJugador(Jugable turnero) {
                HBox hboxJugador = new HBox();
                hboxJugador.setStyle("-fx-background-color: #000000;");
                hboxJugador.setPadding(new Insets(0, 0, 20, 0));

                Button botonFinalizarTurno = new Button();
                BotonTurnero funcion = new BotonTurnero(turnero);
                botonFinalizarTurno.setAlignment(Pos.CENTER);

                botonFinalizarTurno.setText("Finalizar turno");
                botonFinalizarTurno.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
                botonFinalizarTurno.setPrefSize(400, 40);
                botonFinalizarTurno.setAlignment(Pos.CENTER);
                botonFinalizarTurno.setOnAction(funcion);

                this.botonTerminarTurno = botonFinalizarTurno;

                hboxJugador.getChildren().addAll(textoNombreYVida, botonTerminarTurno, textoCreditos);

                visualizadorGeneral.actualizarPanelJugador(hboxJugador);
        }

        public void updateInfo(String nombre, int puntosVida, int creditos) {
                HBox hboxJugador = new HBox();
                hboxJugador.setStyle("-fx-background-color: #000000;");
                hboxJugador.setPadding(new Insets(0, 0, 20, 100));

                textoCreditos.setText(String.format("Créditos disponibles:\n%d", creditos));
                textoNombreYVida.setText(String.format("\nLa vida de %s es %s",nombre , puntosVida));

                hboxJugador.getChildren().addAll(textoNombreYVida, botonTerminarTurno, textoCreditos);
                visualizadorGeneral.actualizarPanelJugador(hboxJugador);
        }
}