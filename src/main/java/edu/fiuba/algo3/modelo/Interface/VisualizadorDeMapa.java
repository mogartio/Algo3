package edu.fiuba.algo3.modelo.Interface;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class VisualizadorDeMapa {

    private GridPane grilla;

    private BorderPane layout;

    private Juego juego;

    public void setJuego(Juego unJuego) { this.juego = unJuego; }
    public VisualizadorDeMapa(int largo){

        layout = new BorderPane();
        VBox panelTienda = VisualizadorTienda.crearPanelTienda();
        HBox panelJugador = VisualizadorPanelJugador.crearPanelJugador();
        grilla = new GridPane();
        grilla.setGridLinesVisible(true);
        layout.setLeft(grilla);
        layout.setCenter(panelTienda);
        layout.setTop(panelJugador);
        mostrar();
    }

    public void agregarPanelJugador(HBox panelJugador) {
        layout.setTop(panelJugador);
    }

    public void agregarParcela(String tipoDeParcela, int coordX, int coordY){
        Color color = Color.GREY;
        switch (tipoDeParcela) {
            case "Rocoso":
                color = Color.GREY;
                break;
            case "Pasarela":
                color = Color.ORANGE;
                break;
            case "Tierra":
                color = Color.GREEN;
                break;
        }
        Rectangle rect = new Rectangle(56, 56, color);
        rect.setStroke(Paint.valueOf("#CCCCCC"));
        rect.setStyle("-fx-stroke-width: 1;");
        rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ControladorCompra.getInstance().ponerDefensaEn(coordX, coordY);
            }
        });
        grilla.add(rect, coordX, coordY );
    }

    public void mostrar() {
        Stage ventana = VentanaDeJuego.getInstance();

        if (ventana == null){
            return;
        }
        ventana.setScene(new Scene(layout));
        ventana.setTitle("mapeanding");

        ventana.show();
    }

    public void agregarSpray(ImageView spray, int coordX, int coordY){
        grilla.add(spray, coordX, coordY);
    }

    public void borrarNodoEnPosicion(final int row,final int column) {

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
