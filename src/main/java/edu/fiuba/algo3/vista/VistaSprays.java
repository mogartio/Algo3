package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.Defensas.EstadoDeConstruccion.EstadoConstruccion;
import edu.fiuba.algo3.modelo.Defensas.TorreBlanca;
import edu.fiuba.algo3.modelo.Defensas.TorrePlateada;
import edu.fiuba.algo3.modelo.Enemigos.Movimiento.Movimiento;
import edu.fiuba.algo3.modelo.Interface.AudioPlayer;
import edu.fiuba.algo3.modelo.Interface.VisualizadorDeMapa;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
        if(sprayable instanceof TorrePlateada || sprayable instanceof TorreBlanca) {
            System.out.println("Soy instancia de torre");
        }
        ArrayList<String> datos = verDatos(sprayable);
        System.out.println("datos = " + datos);

        if(datos.size() == 3){ //Cuando no es 3 es porque no debe mostrar el spray}
            try {
                if(!datos.get(0).equals("")) {
                    ImageView imagen = ConstanteImagenes.getImagen(datos.get(0));

                    String pathAudio = datos.get(1);
                    AudioPlayer.playEfectoSonido(pathAudio);

                    String coordenadasComoString = datos.get(2);
                    String[] coordenadas = coordenadasComoString.substring(1, coordenadasComoString.length() - 1).split(",");
                    int x = Integer.parseInt(coordenadas[0]); // x
                    int y = Integer.parseInt(coordenadas[1]); // y
                    visualizadorDeMapa.agregarSpray(imagen, x, y);
                }

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private ArrayList<String> verDatos(Sprayable sprayable){
        ArrayList<String> datos = new ArrayList<>();
        String nombre;
        String sonido;
        Coordenada pos;
        Field field;

        try {
            Method method = sprayable.getClass().getSuperclass().getDeclaredMethod("representacionString");
            nombre = (String) method.invoke(sprayable);
            datos.add(0, nombre);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
        }

        try {
            field = sprayable.getClass().getSuperclass().getDeclaredField("sonido");
            field.setAccessible(true);
            sonido = (String) field.get(sprayable);
            datos.add(1, sonido);
        } catch (NoSuchFieldException | IllegalAccessException e) {
        }
        try {
            field = sprayable.getClass().getSuperclass().getDeclaredField("estadoDeConstruccion");
            field.setAccessible(true);
            EstadoConstruccion est = (EstadoConstruccion) field.get(sprayable);
            field = est.getClass().getDeclaredField("sonido");
            sonido = (String) field.get(est);
            datos.add(1, sonido);
            field.set(est, "");
        } catch (NoSuchFieldException | IllegalAccessException e){
            System.out.println(e.getMessage());
        }

        try {
            field = sprayable.getClass().getSuperclass().getDeclaredField("tipoMovimiento");
            field.setAccessible(true);
            Movimiento mov = (Movimiento) field.get(sprayable);
            field = mov.getClass().getDeclaredField("posicionActual");
            pos = (Coordenada) field.get(mov);
            datos.add(2, pos.representacionString());
        } catch (NoSuchFieldException | IllegalAccessException e) {
        }

        try {
            field = sprayable.getClass().getSuperclass().getDeclaredField("posicion");
            field.setAccessible(true);
            pos = (Coordenada) field.get(sprayable);
            datos.add(2, pos.representacionString());
        } catch (NoSuchFieldException | IllegalAccessException e) {
        }

        return datos;
    }
}
