
package edu.fiuba.algo3.modelo.Creador;


import edu.fiuba.algo3.modelo.lectorJSON.Camino;
import edu.fiuba.algo3.modelo.lectorJSON.Lector;
import edu.fiuba.algo3.modelo.lectorJSON.Mapa;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Rocosa;
import edu.fiuba.algo3.modelo.parcelas.Tierra;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.concurrent.atomic.AtomicInteger;

public class CreadorDeMapa implements Creador {
    public Camino camino;
    public Mapa mapa;

    public CreadorDeMapa(){
        mapa = new Mapa();
        camino = new Camino(mapa);
    }

    public Object crear(String direccionArchivoJSON){
        JSONArray jsonArray = Lector.leer(direccionArchivoJSON);
        crearMapa((JSONObject) jsonArray.get(1));
        return mapa;
    }

    private void crearMapa(JSONObject listaFilas) {
        listaFilas.keySet().forEach(numeroFila -> crearFila(numeroFila, listaFilas));
    }

    private void crearFila(Object numeroFila, JSONObject listaFilas) {
        JSONArray fila = (JSONArray) listaFilas.get(numeroFila);

        int coordenadaY = Integer.parseInt((String) numeroFila);
        AtomicInteger coordenadaX = new AtomicInteger(1);
        fila.forEach(parcela -> {
                    crearParcela((String) parcela, coordenadaY, coordenadaX.get());
                    coordenadaX.getAndIncrement();
                });
    }

    private void crearParcela(String tipoDeTerreno, int coordY, int coordX){
        Coordenada nuevaCoordenada = new Coordenada(coordX, coordY);
        if (tipoDeTerreno.equals("Rocoso")) {
            mapa.agregar(nuevaCoordenada, new Rocosa(nuevaCoordenada));
        }
        if (tipoDeTerreno.equals("Pasarela")) {
            camino.agregar(nuevaCoordenada);
        }
        if (tipoDeTerreno.equals("Tierra")) {
            mapa.agregar(nuevaCoordenada, new Tierra(nuevaCoordenada));
        }
    }
}