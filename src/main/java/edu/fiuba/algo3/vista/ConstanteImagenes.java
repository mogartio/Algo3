package edu.fiuba.algo3.vista;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class ConstanteImagenes {
    private static Map<String, String> diccionarioImagenes;

    static {
        diccionarioImagenes = Map.ofEntries(
                Map.entry("Araña", "ImagenesGUI/arania.png"),
                Map.entry("Hormiga", "ImagenesGUI/hormiga.png"),
                Map.entry("Lechuza", "ImagenesGUI/lechuza.png"),
                Map.entry("Topo", "ImagenesGUI/topo.png"),
                Map.entry("TorreBlanca", "ImagenesGUI/torreBlanca.png"),
                Map.entry("TorrePlateada", "ImagenesGUI/torrePlateada.png"),
                Map.entry("TrampaArenosa", "ImagenesGUI/trampaArena.png"),
                Map.entry("Ganado", "ImagenesGUI/victoria.png"),
                Map.entry("Perdido", "ImagenesGUI/Derrota.png"),
                Map.entry("meta", "ImagenesGUI/meta.png"),
                Map.entry("enConstruccion", "ImagenesGUI/enConstruccion.png")
        );
    }
    public static ImageView getImagen(String imagenBuscada) throws FileNotFoundException {
        InputStream stream = new FileInputStream((String) diccionarioImagenes.get(imagenBuscada));
        ImageView image = new ImageView((new Image(stream)));
        image.setFitHeight(50);
        image.setFitWidth(50);
        return image;
    }
}
