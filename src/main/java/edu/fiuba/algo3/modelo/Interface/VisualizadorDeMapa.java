package edu.fiuba.algo3.modelo.Interface;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class VisualizadorDeMapa {

    private VBox panelTienda;
    private GridPane grilla;
    private BorderPane layout;

    private ArrayList<ImageView> listaEnemigos;

    private Map<ImageView, Integer> diccionarioX;
    private Map<ImageView, Integer> diccionarioY;
    public VisualizadorDeMapa(int largo){

        layout = new BorderPane();
        panelTienda = VisualizadorTienda.crearPanelTienda();
        grilla = new GridPane();
        grilla.setGridLinesVisible(false);
        layout.setLeft(grilla);
        layout.setCenter(panelTienda);
        listaEnemigos = new ArrayList<>();
        diccionarioX = new HashMap<>();
        diccionarioY = new HashMap<>();
    }

    public void actualizarPanelJugador(HBox panelJugador) {
        //System.out.println();
        layout.setTop(panelJugador);
    }

    public void actualizarPanelTienda() {
        panelTienda = VisualizadorTienda.updateInfo();
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
        listaEnemigos.add(spray);
        diccionarioX.put(spray, coordX);
        diccionarioY.put(spray, coordY);
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
    public void mostrarMensajeFinal(ImageView mensajeFinal) {
        layout.setLeft(mensajeFinal);
    }
}
