package edu.fiuba.algo3.Interface;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.InputStream;

public class InicializadorPantallaInicio {
    public static void start(BorderPane layout) throws Exception {

        crearPanelInteractivo(layout);
        crearBackground(layout);
        AudioPlayer.playBGMusic();
    }

    private static void crearPanelInteractivo(BorderPane layout) {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(0,0, 50, 0));
        hbox.setAlignment(Pos.CENTER);
        pedirNombreJugador(hbox);
        layout.setBottom(hbox);
    }

    private static void crearBackground(BorderPane layout) throws Exception {
        // Creación del Objeto imagen
        InputStream stream = new FileInputStream("ImagenesGUI/TitleScreen.png");
        Image image = new Image(stream);

        // Creación del fondo
        BackgroundImage myBI= new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(900, 680, false, false, false, false));
        Background bg = new Background(myBI);
        layout.setBackground(bg);
    }

    private static void inicializarBotonJugar(HBox hbox, String nombreJugador){
        Button button = new Button();
        BotonDeInicioDeJuego funcion = new BotonDeInicioDeJuego(button, nombreJugador );

        button.setText("Jugar");
        button.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        button.setPrefSize(400, 80);
        button.setOnAction(funcion);
        hbox.getChildren().clear();
        hbox.setPadding(new Insets(0,0, 20, 0));
        hbox.getChildren().add(button);
    }

    private static void pedirNombreJugador(HBox hbox){

        Text text = new Text("Ingresá tu nombre:");
        text.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        hbox.getChildren().add(text);

        TextField textField = new TextField();
        textField.setPrefWidth(250);
        textField.setPrefHeight(40);
        hbox.getChildren().add(textField);

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                if (textField.getText().length() == 0 || textField.getText().length() > 10){
                    Text text = new Text("El nombre debe tener menos de 10 caracteres");
                    text.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                    hbox.getChildren().add(text);
                    return;
                }
                inicializarBotonJugar(hbox, textField.getText());
            }
        };
        textField.setOnAction(event);
    }
}
