package edu.fiuba.algo3.vista;

import java.util.ArrayList;
import java.util.Observable;

public abstract class Sprayable extends Observable {

    protected String sprayID;
    public abstract String obtenerSprayID();
}
