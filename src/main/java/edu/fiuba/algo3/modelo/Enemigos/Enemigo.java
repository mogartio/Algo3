package edu.fiuba.algo3.modelo.Enemigos;
import edu.fiuba.algo3.modelo.Excepciones.PasarelaInexistente;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.Observer.Observable;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;
import edu.fiuba.algo3.modelo.miscelanea.Vida;

public abstract class Enemigo extends Observable {
    protected Vida vida;
    protected int cantidadMovimientos;
    protected int poderAtaque;
    protected Pasarela posicionActual;

    protected Coordenada ubicacion;


    public Enemigo( int puntosVida, int ataque, int cantidadMovimientos){
        super();
        this.vida = new Vida(puntosVida);
        this.poderAtaque = ataque;
        this.cantidadMovimientos = cantidadMovimientos;
        this.posicionActual = null;
    }
    public boolean estaVivo() {
        return vida.sigueVivo();
    }

    public void recibirDanio(int danio){
        this.vida.quitarVida(danio);

        if (!vida.sigueVivo()) {
            this.morir();
        }
    }

    public void actualizarPosicionActual(Pasarela pasarelaActual) {
        this.posicionActual = pasarelaActual;
        this.posicionActual.actualizarUbicacionAlEnemigo(this);
    }

    public void actualizarUbicacion(Coordenada ubicacionNueva){
        this.ubicacion = ubicacionNueva;
    }

    public void daniarJugador() {
        this.emisor.notificarSubscriptores("log",this.representacionString() + " causo daño al jugador");
        Jugador.getInstance().recibirDanio(this.poderAtaque);
        this.vida = new Vida(0);
    }

    public abstract void morir();

    public abstract String representacionString();

    public void avanzar() throws PasarelaInexistente{

        if (vida.sigueVivo())
            for(int pasos = 0; pasos < this.cantidadMovimientos; pasos++){
                posicionActual.actualizarPosicion(this);
            }
        if(this.posicionActual == null){
                throw new PasarelaInexistente("El enemigo se movio a un lugar invalido");
        }
    }

    public boolean estaEnRango(Coordenada posicion, int distancia){
        return this.posicionActual.estaEnRango(posicion, distancia);
    }

    public String represtacionUbicacion(){
        return this.ubicacion.representacionString();
    }
    
}