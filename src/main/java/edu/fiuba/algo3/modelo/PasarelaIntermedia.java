package edu.fiuba.algo3.modelo;

public class PasarelaIntermedia extends Pasarela {

    public PasarelaIntermedia(Coordenada coordenada, Pasarela pasarelaSiguiente) {
        super(coordenada);
        this.siguientePasarela = pasarelaSiguiente;
    }

    public void recibir(Enemigo nuevoEnemigo){
        nuevoEnemigo.actualizarPosicionActual(this);
        this.enemigos.add(nuevoEnemigo);
    }

    public Pasarela verSiguiente() { return siguientePasarela; }

    public Pasarela verSiguiente(int cantidadPasos) {
        Pasarela pasarelaAux = this;

        if(cantidadPasos >= 0) { // Este caso no deberia pasar
            for(int i = 0; i < cantidadPasos; i++) {
                pasarelaAux = pasarelaAux.verSiguiente();
            }
        }

        return pasarelaAux;
    }

}
