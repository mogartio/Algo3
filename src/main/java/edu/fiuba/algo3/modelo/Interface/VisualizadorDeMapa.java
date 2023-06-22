package edu.fiuba.algo3.modelo.Interface;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Hormiga;
import edu.fiuba.algo3.vista.ConstanteImagenes;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class VisualizadorDeMapa {

    private GridPane grilla;

    public VisualizadorDeMapa(int largo){
        //recordando que se parte del supuesto de la misma cantidad de celdas en Y como en X
        grilla = new GridPane();
        grilla.setGridLinesVisible(true);
        grilla.setAlignment( Pos.CENTER );
    }

    public void agregarParcela(String tipoDeParcela, int coordX, int coordY){
        Color color = Color.GREY;
        switch (tipoDeParcela) {
            case "Rocoso":
                color = Color.BLACK;
                break;
            case "Pasarela":
                color = Color.LIGHTBLUE;
                break;
            case "Tierra":
                color = Color.ORANGE;
                break;
        }
        Rectangle rect = new Rectangle(60, 60, color);
        rect.setStroke(Paint.valueOf("#CCCCCC"));
        rect.setStyle("-fx-stroke-width: 1;");
        grilla.add(rect, coordX, coordY );
    }

    public void mostrar() {
        Stage ventana = VentanaDeJuego.getInstance();

        if (ventana == null){
            return;
        }
        ventana.setScene(new Scene(grilla));
        ventana.setTitle("mapeanding");

        Enemigo hormiga = new Hormiga();
        ventana.show();
    }

    public void agregarSpray(ImageView spray, int coordX, int coordY){
        grilla.add(spray, coordX, coordY);
    }

    public void removeNodeByRowColumnIndex(final int row,final int column) {

        ObservableList<Node> childrens = grilla.getChildren();

        for(Node node : childrens) {
            if(node instanceof ImageView && grilla.getRowIndex(node) == row && grilla.getColumnIndex(node) == column) {
                 // use what you want to remove
                grilla.getChildren().remove(node);
                break;
            }
        }
    }
}
