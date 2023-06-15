package edu.fiuba.algo3.modelo.Observer;

public abstract class Observable {
    protected Emisor emisor;
    public Observable(){
        this.emisor = new Emisor();
    };
    public void agregarSubscriptor(Subscriptor subscriptor){
        this.emisor.subcribir(subscriptor);
    }

    public void eliminarSubscriptor(Subscriptor subscriptor){
        emisor.desSubscribir(subscriptor);
    }
}
