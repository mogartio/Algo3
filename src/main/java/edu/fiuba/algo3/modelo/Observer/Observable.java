package edu.fiuba.algo3.modelo.Observer;

public abstract class Observable {
    protected Emisor emisor;
    public Observable(){
        emisor = new Emisor();
    };
    public void agregarSubscriptor(Subscriptor subscriptor){
        emisor.subcribir(subscriptor);
    }

    public void eliminarSubscriptor(Subscriptor subscriptor){
        emisor.desSubscribir(subscriptor);
    }
}
