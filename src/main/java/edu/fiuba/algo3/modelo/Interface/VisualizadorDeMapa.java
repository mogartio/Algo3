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

    public VisualizadorDeMapa(int largo){
        //recordando que se parte del supuesto de la misma cantidad de celdas en Y como en X
        grilla = new TilePane();
        grilla.setPrefColumns(largo);
        grilla.setPrefRows(largo);

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

        if (ventana == null){
            return;
        }

        ventana.setScene(new Scene(grilla));
        ventana.setTitle("mapeanding");
        ventana.show();
    }
}
