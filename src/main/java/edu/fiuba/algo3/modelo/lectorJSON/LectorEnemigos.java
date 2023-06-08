package edu.fiuba.algo3.modelo.lectorJSON;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import edu.fiuba.algo3.modelo.Enemigos.Araña;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Hormiga;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;
import edu.fiuba.algo3.modelo.parcelas.PasarelaIntermedia;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LectorEnemigos {
    public Queue<ArrayList<Enemigo>> colaSpawner;
    LectorEnemigos(){
        colaSpawner = new LinkedList<>();
    }

    public Queue<ArrayList<Enemigo>> leerEnemigos() {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("ArchivosJson/enemigos.json")) {
            Object obj = jsonParser.parse(reader);

            JSONArray listaTurnos = (JSONArray) obj;
            listaTurnos.forEach( turno -> crearTurno( (JSONObject) turno) );

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        //System.out.println(colaSpawner);
        return colaSpawner;
    }
    private void crearTurno(JSONObject turno) {
        final int POSICION_HORMIGAS = 0;
        final int POSICION_ARAÑAS = 1;

        Coordenada coordenadaInicial = new Coordenada(0, 0);
        Pasarela pasarelaInicial = new PasarelaIntermedia(coordenadaInicial, null);

        ArrayList<Enemigo> enemigosTurnoActual = new ArrayList<Enemigo>();
        JSONObject enemigoObject = (JSONObject) turno.get("enemigos");

        JSONArray jsonArray = new JSONArray();
        Iterator x =  enemigoObject.keySet().iterator();
        while (x.hasNext()){
            String key = (String) x.next();
            jsonArray.add(enemigoObject.get(key));
        }


        int cantidadHormigas = ((Long) jsonArray.get(POSICION_HORMIGAS)).intValue();
        for(int i=0; i<cantidadHormigas;i++){
            enemigosTurnoActual.add(new Hormiga(pasarelaInicial));
        }

        int cantidadArañas = ((Long) jsonArray.get(POSICION_ARAÑAS)).intValue();
        for(int i=0; i<cantidadArañas;i++){
            enemigosTurnoActual.add(new Araña(pasarelaInicial));
        }
        colaSpawner.add(enemigosTurnoActual);
    }
}


