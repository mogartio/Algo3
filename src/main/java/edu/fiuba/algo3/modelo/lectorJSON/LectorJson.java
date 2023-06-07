package edu.fiuba.algo3.modelo.lectorJSON;

import edu.fiuba.algo3.modelo.Enemigo;

import java.util.ArrayList;
import java.util.Queue;

public class LectorJson {
    private final LectorEnemigos lectorEnemigos;

    public LectorJson(){
        lectorEnemigos = new LectorEnemigos();
    }
    public Queue<ArrayList<Enemigo>> leerEnemigos() { return (lectorEnemigos.leerEnemigos()); }
}

