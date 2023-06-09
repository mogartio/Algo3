/*
package edu.fiuba.algo3.modelo.Creador;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Hormiga;
import edu.fiuba.algo3.modelo.lectorJSON.Lector;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class CreadorEnemigos implements Creador {
    Queue<ArrayList<Enemigo>> colaSpawner;
    public CreadorEnemigos(){
        colaSpawner = new LinkedList<>();
    }

    public Object crear() {
        crearTurnoEnemigos(Lector.leer("ArchivosJson/enemigos.json", "Enemigo"));
        return colaSpawner;
    }

    public void crearTurnoEnemigos(JSONArray listaTurnos) {
        listaTurnos.forEach( turno -> crearTurno( (JSONObject) turno) );
    }

    private void crearTurno(JSONObject turno) {
        final int POSICION_HORMIGAS = 0;
        final int POSICION_ARAÑAS = 1;
        JSONArray.add(enemigoObject.get(key));

        int cantidadHormigas = ((Long) JSONArray.get(POSICION_HORMIGAS)).intValue();
        for(int i=0; i<cantidadHormigas;i++) {
            enemigosTurnoActual.add(new Hormiga(pasarelaInicial));
        }

        colaSpawner.add(enemigosTurnoActual);
    }
}

*/