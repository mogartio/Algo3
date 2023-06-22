package edu.fiuba.algo3.modelo.ObserverPropio;

public abstract class ObservablePropio {
    protected Emisor emisor;
    public ObservablePropio(){
        this.emisor = new Emisor();
    };
    public void agregarSubscriptor(Subscriptor subscriptor){
        this.emisor.subcribir(subscriptor);
    }

    public void eliminarSubscriptor(Subscriptor subscriptor){
        emisor.desSubscribir(subscriptor);
    }
}
