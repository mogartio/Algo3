package edu.fiuba.algo3.modelo.Interface;

import edu.fiuba.algo3.modelo.juego.Jugador;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;

public class InicializadorPantallaInicio {
    public static void start(GridPane layout, Stage stage) throws Exception {
        pedirNombreJugador(layout);
        crearBackground(layout);
    }

    private static void crearBackground(GridPane layout) throws Exception {
        // Creación del Objeto imagen
        InputStream stream = new FileInputStream("ImagenesGUI/TitleScreen.png");
        Image image = new Image(stream);

        // Creación del fondo
        BackgroundImage myBI= new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(500, 1500, true, true, true, false));
        Background bg = new Background(myBI);
        layout.setBackground(bg);
    }

    private static void inicializarBotonJugar(GridPane layout){
        Button button = new Button();
        BotonDeInicioDeJuego funcion = new BotonDeInicioDeJuego(button);

        button.setText("Jugar");
        button.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        button.setPrefSize(400, 80);
        button.setOnAction(funcion);

        layout.add(button, 4, 12);
    }

    private static void pedirNombreJugador(GridPane layout){

        Text chartTitle = new Text("Ingresá tu nombre:");
        chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        layout.add(chartTitle, 3, 12);

        TextField textField = new TextField();

        layout.add(textField, 3, 13);




        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                Jugador.getInstance().setNombre(textField.getText());
                Text tuNombre = new Text(String.format("Tu nombre es %s", textField.getText()));    //Borrar el nombre viejo al usar uno nuevo
                layout.add(tuNombre, 4, 9);
                inicializarBotonJugar(layout);
            }
        };
        textField.setOnAction(event);
    }
}
