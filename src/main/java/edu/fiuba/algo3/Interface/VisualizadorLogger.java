package edu.fiuba.algo3.Interface;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class VisualizadorLogger {
    private VBox vboxMensajes;
    public StackPane InicializarPanelLogger() {
        StackPane stPane = new StackPane();
        vboxMensajes = new VBox();
        vboxMensajes.setPadding(new Insets(0,0,5,5));
        vboxMensajes.setAlignment(Pos.BOTTOM_LEFT);
        Rectangle rect = new Rectangle(500, 200);
        rect.setFill(Color.BLACK);
        rect.setOpacity(0.5);
        rect.setStyle("-fx-border-style: solid; -fx-border-width: 20; -fx-border-color: black;");
        stPane.getChildren().addAll(rect, vboxMensajes);
        stPane.setAlignment(Pos.BOTTOM_LEFT);
        mostrarMensaje("Probando si funciona");
        return stPane;
    }

    public void mostrarMensaje(String unMensaje){
        Text mensaje = new Text(unMensaje);
        mensaje.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        mensaje.setFill(Color.WHITE);
        mensaje.setTextAlignment(TextAlignment.LEFT);
        vboxMensajes.getChildren().add(mensaje);
    }
}
