package edu.fiuba.algo3.modelo;
import edu.fiuba.algo3.modelo.Observer.Emisor;
import edu.fiuba.algo3.modelo.Observer.Observable;

public abstract class Enemigo extends Observable {
    protected Vida vida;
    protected int cantidadMovimientos;
    protected int creditosRecompensa;
    protected int poderAtaque;
    public Pasarela posicionActual;

    public Enemigo(){
        super();
    }

    public boolean estaVivo() {
        return vida.sigueVivo();
    }

    public void recibirDaño(int daño){
        this.vida.quitarVida(daño);
        if (!vida.sigueVivo()) {
            this.morir();
        }
    }

    public void actualizarPosicionActual(Pasarela pasarelaActual) {
        this.posicionActual = pasarelaActual;
    }

    public void dañarJugador() {
        Jugador.getInstance().recibirDaño(poderAtaque);
        vida = new Vida(0);
    }

    public abstract void morir();

    public abstract String representacionString();

    public void avanzar() {
        /*for (int i = 0; i < cantidadMovimientos; i++) {
            this.posicionActual = posicionActual.verSiguiente();
        }*/
        if (vida.sigueVivo())
            posicionActual.actualizarPosicion(this, this.cantidadMovimientos);
    }

    public Pasarela verPosicion() { return posicionActual; }

    /*public boolean muerto(){
        return (this.vida.obtenerPuntos() == 0);
    }*/
}