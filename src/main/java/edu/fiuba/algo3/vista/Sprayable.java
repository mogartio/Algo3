package edu.fiuba.algo3.vista;

import java.util.Observable;

public abstract class Sprayable extends Loggeable {

    public Sprayable() {
        super();
        sprayID = "";
    }

    protected String sprayID;
    public abstract String sprayID();
}
