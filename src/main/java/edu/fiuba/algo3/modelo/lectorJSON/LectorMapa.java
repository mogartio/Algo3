package edu.fiuba.algo3.modelo.lectorJSON;

import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Rocosa;
import edu.fiuba.algo3.modelo.parcelas.Tierra;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LectorMapa {
    public Camino camino;
    public Mapa mapa;

    public LectorMapa(){
        mapa = new Mapa();
        camino = new Camino(mapa);
    }
    public void leerMapa(){
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("ArchivosJson/mapa.json")) {
            Object obj = jsonParser.parse(reader);
            JSONObject cosa = (JSONObject) ((JSONObject) obj).get("Mapa");
            for(int i=1; i<20; i++){
                ArrayList<String> algo = (ArrayList<String>) cosa.get(String.valueOf(i));
                if (algo == null){
                    break;
                }
                int coordY = i;
                int coordX = 1;
                algo.forEach(parcela -> crearParcela(parcela, coordY, coordX));
            }
            mapa.ver();

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void crearParcela(String tipoDeTerreno, int coordY, int coordX) {
        Coordenada nuevaCoordenada = new Coordenada(coordX, coordY);
        coordX ++;
        if (tipoDeTerreno.equals("Rocoso")) {
            mapa.agregar(new Rocosa(nuevaCoordenada));
        }
        if (tipoDeTerreno.equals("Pasarela")) {
            camino.agregar(nuevaCoordenada);
        }
        if (tipoDeTerreno.equals("Tierra")) {
            mapa.agregar(new Tierra(nuevaCoordenada));
        }
    }
}
