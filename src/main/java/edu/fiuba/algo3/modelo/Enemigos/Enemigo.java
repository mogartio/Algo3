package edu.fiuba.algo3.modelo.Enemigos;
import edu.fiuba.algo3.modelo.Observer.Emisor;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.Observer.Observable;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;
import edu.fiuba.algo3.modelo.miscelanea.Vida;

public abstract class Enemigo extends Observable {
    protected Vida vida;
    protected int cantidadMovimientos;

    protected Emisor emisor;
    protected int poderAtaque;
    public Pasarela posicionActual;

    public Enemigo( int puntosVida, int ataque, int cantidadMovimientos){
        this.emisor = new Emisor();
        this.vida = new Vida(puntosVida);
        this.poderAtaque = ataque;
        this.cantidadMovimientos = cantidadMovimientos;
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
        this.vida = new Vida(0);
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

    public boolean estaEnRango(Coordenada posicion, int distancia){
        return this.posicionActual.estaEnRango(posicion, distancia);
    }

    /*public boolean muerto(){
        return (this.vida.obtenerPuntos() == 0);
    }*/
}