package edu.fiuba.algo3.Interface;

import edu.fiuba.algo3.modelo.juego.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Observer;

public class VisualizadorLogger {
    private VBox vboxMensajes;

    private static final VisualizadorLogger INSTANCE = new VisualizadorLogger();

    private VisualizadorLogger(){
        this.vboxMensajes = new VBox();
        vboxMensajes.setStyle("-fx-background-color: #000000;");
        vboxMensajes.setPadding(new Insets(0,0,5,5));
        vboxMensajes.setAlignment(Pos.BOTTOM_LEFT);
        vboxMensajes.setSpacing(4);
    }

    public static VisualizadorLogger getInstance() {
        return INSTANCE;
    }
    public void InicializarPanelLogger() {
        Stage ventanaLogger = new Stage();
        ventanaLogger.setX(1500);
        ventanaLogger.setHeight(600);
        ventanaLogger.setWidth(600);
        ventanaLogger.setResizable(false);
        ventanaLogger.setTitle("Ventana Logger");

        Scene scene = new Scene(vboxMensajes, Color.BLACK);
        ventanaLogger.setScene(scene);
        ventanaLogger.show();
    }

    public void mostrarMensaje(String unMensaje){
        Text mensaje = new Text(unMensaje);
        mensaje.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        mensaje.setFill(Color.WHITE);
        mensaje.setTextAlignment(TextAlignment.LEFT);
        vboxMensajes.getChildren().add(mensaje);
    }
}
