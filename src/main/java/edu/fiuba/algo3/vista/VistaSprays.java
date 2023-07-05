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
        if (sprayable instanceof TorrePlateada){
            System.out.println("antes de datos: ");
        }
        ArrayList<String> datos = verDatos(sprayable);
        if (sprayable instanceof TorrePlateada){
            System.out.println("datos: " + datos);
        }


        if(datos.size() == 3){ //Cuando no es 3 es porque no debe mostrar el spray}
            try {
                if(datos.get(0) != "") {
                    ImageView imagen = ConstanteImagenes.getImagen(datos.get(0));

                    String coordenadasComoString = datos.get(2);
                    String pathAudio = datos.get(1);

                    String[] coordenadas = coordenadasComoString.substring(1, coordenadasComoString.length() - 1).split(",");
                    int x = Integer.parseInt(coordenadas[0]); // x
                    int y = Integer.parseInt(coordenadas[1]); // y
                    AudioPlayer.playEfectoSonido(pathAudio);
                    visualizadorDeMapa.agregarSpray(imagen, x, y);
                }

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private ArrayList<String> verDatos(Sprayable sprayable){
        ArrayList<String> datos = new ArrayList<String>();
        String nombre;
        String sonido;
        Coordenada pos;
        Field field = null;
        try {
            field = sprayable.getClass().getSuperclass().getDeclaredField("representacionString");
            field.setAccessible(true);
            nombre = (String) field.get(sprayable);
            datos.add(0, nombre);
        } catch (IllegalAccessException e) {
        } catch (NoSuchFieldException e) {
        }
//        try {
//            Method method = sprayable.getClass().getSuperclass().getDeclaredMethod("representacionString");
//            method.setAccessible(true);
//            nombre = (String) method.invoke(sprayable);
//            System.out.println("Agrego nombre: " + nombre);
//            datos.add(0, nombre);
//        } catch (IllegalAccessException e) {
//        } catch (NoSuchMethodException e) {
//        } catch (InvocationTargetException e) {
//        }
        try {
            field = sprayable.getClass().getSuperclass().getDeclaredField("estadoDeConstruccion");
            field.setAccessible(true);
            EstadoConstruccion est = (EstadoConstruccion) field.get(sprayable);
            Method method = est.getClass().getDeclaredMethod("representacionString", Sprayable.class);
            nombre = (String) method.invoke(est, sprayable);
            datos.add(0, nombre);
//            System.out.println("Paso agregado en datos");
//            field.set(est, "");
//            System.out.println("Paso set");
        } catch (NoSuchFieldException e){
            System.out.println("NoSuchFieldException");
        } catch (IllegalAccessException e){
            System.out.println("IllegalAccessException");
        } catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException");
        } catch (InvocationTargetException e) {
            System.out.println("InvocationTargetException");
        }

        try {
            field = sprayable.getClass().getSuperclass().getDeclaredField("sonido");
            field.setAccessible(true);
            sonido = (String) field.get(sprayable);
            datos.add(1, sonido);
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }
        try {
            field = sprayable.getClass().getSuperclass().getDeclaredField("estadoDeConstruccion");
            field.setAccessible(true);
            EstadoConstruccion est = (EstadoConstruccion) field.get(sprayable);
            field = est.getClass().getDeclaredField("sonido");
            sonido = (String) field.get(est);
            datos.add(1, sonido);
            field.set(est, "");
        } catch (NoSuchFieldException e){
        } catch (IllegalAccessException e){
        }

        try {
            System.out.println(datos);
            field = sprayable.getClass().getSuperclass().getDeclaredField("tipoMovimiento");
            field.setAccessible(true);
            Movimiento mov = (Movimiento) field.get(sprayable);
            field = mov.getClass().getDeclaredField("posicionActual");
            pos = (Coordenada) field.get(mov);
            datos.add(2, pos.representacionString());
        } catch (NoSuchFieldException e) {
        }catch (IllegalAccessException e) {
        }

        try {
            System.out.println(datos);
            field = sprayable.getClass().getSuperclass().getDeclaredField("posicion");
            field.setAccessible(true);
            pos = (Coordenada) field.get(sprayable);
            datos.add(2, pos.representacionString());
            datos.forEach(dato -> System.out.println(dato));
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }

        return datos;
    }
}
