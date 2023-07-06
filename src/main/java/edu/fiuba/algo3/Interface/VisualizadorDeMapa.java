package edu.fiuba.algo3.Interface;

import edu.fiuba.algo3.modelo.juego.Mapa;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.vista.ConstanteImagenes;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.util.*;

public class VisualizadorDeMapa {

    private VBox panelTienda;
    private GridPane grilla;
    private BorderPane layout;
    private ArrayList<Coordenada> coordenadasSpraysDelTurno;
    private Coordenada coordenadaMeta;

    public VisualizadorDeMapa(){
        layout = new BorderPane();
        panelTienda = VisualizadorTienda.crearPanelTienda();
        grilla = new GridPane();
        grilla.setGridLinesVisible(false);
        layout.setLeft(grilla);
        layout.setCenter(panelTienda);
        coordenadasSpraysDelTurno = new ArrayList<>();
    }

    public void actualizarPanelJugador(HBox panelJugador) {
        layout.setTop(panelJugador);
    }

    public void actualizarPanelTienda() {
        panelTienda = VisualizadorTienda.updateInfo();
    }

    public void agregarParcela(Parcela tipoDeParcela, int coordX, int coordY){
        Color color = tipoDeParcela.getColor();
        Rectangle rect = new Rectangle(40, 40, color);
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
        if (coordenada.equals(coordenadaMeta)){
            return;
        }
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

    public void mostrarMensajeError(Text mensajeError) {
        FadeTransition ft = new FadeTransition(Duration.millis(3000), mensajeError);
        ft.setToValue(0);
        panelTienda.getChildren().add(mensajeError);
        ft.play();
    }

    public void cargarMapa(Mapa mapa) {
        HashMap<Coordenada, Parcela> parcelas = mapa.getParcelas();
        Set<Coordenada> claves = parcelas.keySet();
        for (Coordenada clave : claves) {
            if (parcelas.get(clave).equals(mapa.verFinalCamino())) {
                coordenadaMeta = clave;
                mostrarMeta(parcelas.get(clave), clave.getAbscisa(), clave.getOrdenada());
                continue;
            }
            agregarParcela(parcelas.get(clave), clave.getAbscisa(), clave.getOrdenada());
        }
    }

    private void mostrarMeta(Parcela parcela, int abscisa, int ordenada) {
        try {
            ImageView meta = ConstanteImagenes.getImagen("meta");
            grilla.add(meta, abscisa, ordenada);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
