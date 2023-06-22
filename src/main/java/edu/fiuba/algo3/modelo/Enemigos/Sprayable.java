package edu.fiuba.algo3.modelo.Enemigos;

import java.util.ArrayList;
import java.util.Observable;

public abstract class Sprayable extends Observable {

    protected String sprayID;
    public abstract ArrayList<String> ObtenerSprayIDYPosicion();
}
