package edu.fiuba.algo3.modelo.miscelanea;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.TorreBlanca;
import edu.fiuba.algo3.modelo.Defensas.TorrePlateada;
import edu.fiuba.algo3.modelo.juego.Credito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Tienda { // se encargara de administrar los precios de las defensas y de comunicarse con
    //un CreadorDeDefensas para obtener las intacias de las torres
    private HashMap<String,Credito> catalogo;

    public Tienda(){
        catalogo = new HashMap<String,Credito>();
        catalogo.put("TorreBlanca",new Credito(10));
        catalogo.put("TorrePlateada",new Credito(20));
    }

    public ArrayList<String> catalogoDisponible(Credito cantidadRecusos){
        ArrayList<String> defensasDisponibles = new ArrayList<String>();

        for (String i : catalogo.keySet()){
            if (cantidadRecusos.esMayorOIgualQue(catalogo.get(i))){
                defensasDisponibles.add(i);
            }
        }

        return defensasDisponibles;
    }

    public Defensa vendeme(String unaDefensa, Credito tarasca){
        Credito cantidadACobrar = catalogo.get(unaDefensa);
        tarasca.descontar(cantidadACobrar);

        // la defensa deberia proveerla un CreadorDeDefensa
        Defensa defensa = null;
        if (unaDefensa == "TorreBlanca"){
            defensa = new TorreBlanca();
        }
        if (unaDefensa == "TorrePlateada"){
            defensa = new TorrePlateada();
        }

        return defensa;
    }
}
