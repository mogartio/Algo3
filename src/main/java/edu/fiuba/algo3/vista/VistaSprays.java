package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.Enemigos.Sprayable;
import edu.fiuba.algo3.modelo.Interface.VisualizadorDeMapa;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class VistaSprays implements Observer {

    VisualizadorDeMapa visualizadorDeMapa;

    public VistaSprays(VisualizadorDeMapa visualizadorDeMapa) {
        this.visualizadorDeMapa = visualizadorDeMapa;
    }

    @Override
    public void update(Observable o, Object arg) {

        Sprayable sprayable = (Sprayable) o;

        ArrayList<String> datos = sprayable.ObtenerSprayIDYPosicion();

        if(datos.size() == 2){ //Cuando no es 2 es porque no debe mostrar el spray}
            try {
                ImageView imagen = ConstanteImagenes.getImagen(datos.get(0));

                String coordenadasComoString = datos.get(1);

                String[] coordenadas = coordenadasComoString.substring(1, coordenadasComoString.length() - 1).split(",");
                int x = Integer.parseInt(coordenadas[0]); // x
                int y = Integer.parseInt(coordenadas[1]); // y

                visualizadorDeMapa.agregarSpray(imagen, x, y);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
