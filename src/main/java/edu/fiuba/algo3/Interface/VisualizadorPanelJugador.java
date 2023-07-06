package edu.fiuba.algo3.Interface;

import edu.fiuba.algo3.modelo.Jugable;
import edu.fiuba.algo3.modelo.juego.Juego;
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
                this.textoCreditos = actualizarCreditos(100);
                this.textoNombreYVida = actualizarVida("adfsg", 12);
        }

        private Text actualizarCreditos(int creditos){
                Text creditosDisponibles = new Text();
                creditosDisponibles.setTextAlignment(TextAlignment.CENTER);
                creditosDisponibles.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                creditosDisponibles.setFill(Color.RED);
                creditosDisponibles.setText(String.format("Créditos disponibles:\n%d", creditos));
                return creditosDisponibles;
        }

        private Text actualizarVida(String nombreJugador, int vidaJugador){
                Text nombreYVidaJugador = new Text();
                nombreYVidaJugador.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                nombreYVidaJugador.setFill(Color.RED);
                nombreYVidaJugador.setTextAlignment(TextAlignment.CENTER);
                nombreYVidaJugador.setText(String.format("La vida de %s es: \n%s",nombreJugador , vidaJugador));
                return nombreYVidaJugador;
        }
        public void inicializarPanelJugador(Jugable turnero) {
                HBox hboxJugador = new HBox();
                hboxJugador.setStyle("-fx-background-color: #000000;");
                hboxJugador.setPadding(new Insets(10, 0, 10, 70));
                hboxJugador.setSpacing(80);

                Button botonFinalizarTurno = new Button();
                BotonTurnero funcion = new BotonTurnero(turnero);

                botonFinalizarTurno.setText("Finalizar turno");
                botonFinalizarTurno.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
                botonFinalizarTurno.setPrefSize(200, 40);
                botonFinalizarTurno.setAlignment(Pos.CENTER);
                botonFinalizarTurno.setOnAction(funcion);

                this.botonTerminarTurno = botonFinalizarTurno;

                hboxJugador.getChildren().addAll(textoNombreYVida, botonTerminarTurno, textoCreditos);

                visualizadorGeneral.actualizarPanelJugador(hboxJugador);
        }

        public void updateInfo(String nombre, int puntosVida, int creditos) {
                HBox hboxJugador = new HBox();
                hboxJugador.setStyle("-fx-background-color: #000000;");
                hboxJugador.setPadding(new Insets(10, 0, 10, 70));
                hboxJugador.setSpacing(80);

                textoCreditos = actualizarCreditos(creditos);
                textoNombreYVida = actualizarVida(nombre, puntosVida);

                hboxJugador.getChildren().addAll(textoNombreYVida, botonTerminarTurno, textoCreditos);
                visualizadorGeneral.actualizarPanelJugador(hboxJugador);
        }
}