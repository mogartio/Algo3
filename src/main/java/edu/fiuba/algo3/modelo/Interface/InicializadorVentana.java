package edu.fiuba.algo3.modelo.Interface;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InicializadorVentana {
    public static void start(Stage stage) throws Exception {

        stage.setTitle("TowerDefense: El juego!");
        stage.setFullScreen(true);

        GridPane layout = addGridPane();

        Scene scene = new Scene(layout);

        stage.setScene(scene);

        stage.show();

        InicializadorPantallaInicio.start(layout);
    }

    public static GridPane addGridPane() {
        GridPane grid = new GridPane();
        grid.setHgap(100);
        grid.setVgap(50);
        grid.setPadding(new Insets(0, 10, 0, 10));

        return grid;
    }
}
