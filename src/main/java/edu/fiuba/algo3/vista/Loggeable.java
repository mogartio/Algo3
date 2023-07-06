package edu.fiuba.algo3.vista;

import java.util.ArrayList;
import java.util.Observable;

public abstract class Loggeable extends Observable {

    public ArrayList<String> eventos;

    public Loggeable() {
        eventos = new ArrayList<>();
    }
    //    public abstract String representacionString();
    protected void aniadirEvento(String mensaje) {
        eventos.add(mensaje);
        setChanged();
    }
//    public void notificar(){
//        if(!this.evento.equals("")) this.notifyObservers(this.evento);
//    }
}
