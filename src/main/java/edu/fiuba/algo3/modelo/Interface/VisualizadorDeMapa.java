package edu.fiuba.algo3.modelo.Interface;

import edu.fiuba.algo3.Main;
import edu.fiuba.algo3.modelo.parcelas.Rocosa;
import edu.fiuba.algo3.modelo.parcelas.Tierra;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VisualizadorDeMapa {

    private TilePane grilla;

    public VisualizadorDeMapa(){
        //crea una grilla de 5x5
        grilla = new TilePane();
        grilla.setPrefColumns(15);
        grilla.setPrefRows(15);

        //hmm.. nose
        grilla.setTileAlignment( Pos.CENTER );
    }

    public void agregar(String tipoDeParcela){
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
        Rectangle rect = new Rectangle(50, 50, color);
        rect.setStroke(Paint.valueOf("#CCCCCC"));
        rect.setStyle("-fx-stroke-width: 1;");
        grilla.getChildren().add(rect);
    }

    public void mostrar() {
        Stage ventana = VentanaDeJuego.getIntance();

        ventana.setScene(new Scene(grilla));
        ventana.setTitle("mapeanding");
        ventana.show();
    }
}
