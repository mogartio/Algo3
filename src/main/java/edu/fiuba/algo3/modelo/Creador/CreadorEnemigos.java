
package edu.fiuba.algo3.modelo.Creador;


import edu.fiuba.algo3.modelo.Enemigos.Araña;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Hormiga;
import edu.fiuba.algo3.modelo.lectorJSON.Lector;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Normal;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class CreadorEnemigos {
    Lector lector;

    public CreadorEnemigos(){
        this.lector = new Lector();
    }

    /* esto si es importante */
    public LinkedList<ArrayList<Enemigo>> crearEnemigosDeNivel(String direccionDeArchivo) {
        LinkedList<ArrayList<Enemigo>> enemigosDelNivel = new LinkedList<ArrayList<Enemigo>>();
        JSONArray lectura =(JSONArray) Lector.leer(direccionDeArchivo);

        for (int fila = 1 ; fila <= lectura.size() ; fila++ ) { // comienza en 1 para que sea representativo
            //cada ciclo es un turno
            //pensar el JSONObject como un diccionario
            JSONObject informacionDelTurno = (JSONObject) lectura.get(fila - 1); // obtiene la informacion de la fila correspondiente

            agregarEnemigosEnTurno(enemigosDelNivel, informacionDelTurno);
        }
        return enemigosDelNivel;
    }

    private void agregarEnemigosEnTurno(LinkedList<ArrayList<Enemigo>> enemigosDelNivel,JSONObject informacionDelTurno){

        JSONObject enemigosEnTurno = (JSONObject) informacionDelTurno.get("enemigos");// obtiene el value asociado a al key "enemigos"
        ArrayList<Enemigo> enemigosAAgregarEnNivelEnEsteTurno =  new ArrayList<Enemigo>();

        enemigosEnTurno.forEach( (tipoDeEnemigo , cantidadDelTipo) -> {

            //Dado que la bibliteca solo permite trabajar con strings
            int cantidadDelTipoEnInt = Integer.valueOf( cantidadDelTipo.toString() ); // casteo el numero de cantidadDelTipo a int

            for (int i = 0 ; i < cantidadDelTipoEnInt ; i++){
                Enemigo enemigoAAgregar = crearInstanciaDeEnemigo( tipoDeEnemigo.toString());

                enemigosAAgregarEnNivelEnEsteTurno.add( enemigoAAgregar );
            }
        });

        enemigosDelNivel.add(enemigosAAgregarEnNivelEnEsteTurno);
    }

    public Enemigo crearInstanciaDeEnemigo(String tipoDeEnemigoACrear){
        Enemigo instanciaDeEnemigo = null;

        if (tipoDeEnemigoACrear.equals("hormiga") ){
            instanciaDeEnemigo = new Hormiga();

        } else if (tipoDeEnemigoACrear.equals("arana") ){
            instanciaDeEnemigo = new Araña();
        }
        return instanciaDeEnemigo;

    }

}