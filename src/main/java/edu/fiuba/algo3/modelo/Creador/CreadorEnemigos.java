
package edu.fiuba.algo3.modelo.Creador;


import edu.fiuba.algo3.modelo.Enemigos.*;
import edu.fiuba.algo3.modelo.juego.Lector;
import edu.fiuba.algo3.modelo.miscelanea.RandomGenerator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observer;

public class CreadorEnemigos {
    Lector lector;

    public CreadorEnemigos(){
        this.lector = new Lector();
    }


    public LinkedList<ArrayList<Enemigo>> crearEnemigosDeNivel(String direccionDeArchivo, Observer observerParaEnemigos) {

        LinkedList< ArrayList<Enemigo> > enemigosDelNivel = new LinkedList<ArrayList<Enemigo>>();
        JSONArray lectura = (JSONArray) Lector.leer(direccionDeArchivo);


        for (int fila = 1 ; fila <= lectura.size() ; fila++ ) { // comienza en 1 para que sea representativo
            //cada ciclo es un turno

            //pensar el JSONObject como un diccionario
            JSONObject informacionDelTurno = (JSONObject) lectura.get(fila - 1); // obtiene la informacion de la fila correspondiente

            agregarEnemigosEnTurno(enemigosDelNivel, informacionDelTurno, observerParaEnemigos);
        }
        return enemigosDelNivel;
    }

    private void agregarEnemigosEnTurno(LinkedList<ArrayList<Enemigo>> enemigosDelNivel,JSONObject informacionDelTurno, Observer observerParaEnemigos){

        String numeroDeTurno = informacionDelTurno.get("turno").toString();
        JSONObject enemigosEnTurno = (JSONObject) informacionDelTurno.get("enemigos");// obtiene el value asociado a al key "enemigos"
        ArrayList<Enemigo> enemigosAAgregarEnEsteTurno =  new ArrayList<Enemigo>();

        enemigosEnTurno.forEach( (tipoDeEnemigo , cantidadDelTipo) -> {

            //Dado que la bibliteca solo permite trabajar con strings
            int cantidadDelTipoEnInt = Integer.valueOf( cantidadDelTipo.toString() ); // casteo el numero de cantidadDelTipo a int

            for (int i = 0 ; i < cantidadDelTipoEnInt ; i++){
                Enemigo enemigoAAgregar = crearInstanciaDeEnemigo( tipoDeEnemigo.toString() , Integer.parseInt(numeroDeTurno) );

                enemigoAAgregar.addObserver(observerParaEnemigos);

                enemigosAAgregarEnEsteTurno.add( enemigoAAgregar );
            }
        });

        enemigosDelNivel.add(enemigosAAgregarEnEsteTurno);
    }

    public Enemigo crearInstanciaDeEnemigo(String tipoDeEnemigoACrear , int numeroDeTurno){
        Enemigo instanciaDeEnemigo = null;

        if (tipoDeEnemigoACrear.equals("hormiga") ){
            instanciaDeEnemigo = new Hormiga();

        } else if (tipoDeEnemigoACrear.equals("arana") ){
            RandomGenerator generadorRandom = new RandomGenerator(0,10);
            instanciaDeEnemigo = new Arania(generadorRandom);

        } else if (tipoDeEnemigoACrear.equals("topo")){
            instanciaDeEnemigo = new Topo(numeroDeTurno);

        } else if (tipoDeEnemigoACrear.equals("lechuza")){
            instanciaDeEnemigo = new Lechuza();
        }
        return instanciaDeEnemigo;

    }

}