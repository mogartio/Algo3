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

import java.util.ArrayList;

public class VisualizadorDeMapa {

    private GridPane grilla;

    private BorderPane layout;

    private ArrayList<Coordenada> coordenadasSpraysDelTurno;
    public VisualizadorDeMapa(int largo){

        layout = new BorderPane();
        VBox panelTienda = VisualizadorTienda.crearPanelTienda();
        grilla = new GridPane();
        grilla.setGridLinesVisible(true);
        layout.setLeft(grilla);
        layout.setCenter(panelTienda);
        coordenadasSpraysDelTurno = new ArrayList<>();
    }

    public void actualizarPanelJugador(HBox panelJugador) {
        //System.out.println();
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

         rect.setOnMouseClicked(mouseEvent -> {
                     ControladorCompra controladorCompra = ControladorCompra.getInstance();
                     controladorCompra.ponerDefensaEn(coordX, coordY);
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
        Coordenada coordenada = new Coordenada(coordX, coordY);
        coordenadasSpraysDelTurno.add(coordenada);
        grilla.add(spray, coordX, coordY);
    }

    public void borrarNodoEnPosicion(final int fila,final int columna) {

        ObservableList<Node> childrens = grilla.getChildren();

        for(Node node : childrens) {
            if(node instanceof ImageView) {
                if(grilla.getRowIndex(node) == fila && grilla.getColumnIndex(node) == columna) {
                    grilla.getChildren().remove(node);
                    break;
                }
            }

        }
    }

    public void borrarEnemigosDelTurnoAnterior(){
        coordenadasSpraysDelTurno.forEach(coordenada -> borrarNodoEnPosicion(coordenada.getOrdenada(), coordenada.getAbscisa()));
    }

    public void mostrarMensajeFinal(ImageView mensajeFinal) {
        layout.setLeft(mensajeFinal);
    }
}
