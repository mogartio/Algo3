package edu.fiuba.algo3.modelo.Enemigos;
import edu.fiuba.algo3.modelo.Defensas.Ataque;
import edu.fiuba.algo3.modelo.Defensas.TipoDeDefensa;
import edu.fiuba.algo3.modelo.Defensas.Trampa;
import edu.fiuba.algo3.modelo.Enemigos.Efecto.Efecto;
import edu.fiuba.algo3.modelo.Enemigos.Efecto.Ninguno;
import edu.fiuba.algo3.modelo.Enemigos.Movimiento.Movimiento;
import edu.fiuba.algo3.modelo.Excepciones.PasarelaInexistente;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.Mapa;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.miscelanea.Vida;
import edu.fiuba.algo3.vista.Sprayable;

public abstract class Enemigo extends Sprayable implements Visitor{
    protected Vida vida;
    protected int cantidadMovimientos;
    protected int poderAtaque;
    protected Efecto efectoEnemigo;
    protected Movimiento tipoMovimiento;
    protected String representacionString;
    protected String sonido;

    public Enemigo( int puntosVida, int ataque, int cantidadMovimientos){
        super();
        this.vida = new Vida(puntosVida);
        this.poderAtaque = ataque;
        this.efectoEnemigo = new Ninguno();
        this.cantidadMovimientos = cantidadMovimientos;
    }
    public boolean estaVivo() {
        return vida.sigueVivo();
    }

    public void establecerInicioYMeta(Parcela inicial, Parcela pFinal){
        this.tipoMovimiento.establecerInicioYMeta(inicial, pFinal);
    }

    public void recibirDanio(int danio){
        this.vida.quitarVida(danio);

        if (!vida.sigueVivo()) {
            this.morir();
            this.aniadirEvento(this.representacionString() + " en posicion " + this.tipoMovimiento.representarUbicacion() + " muere");
        }
    }

    public void actualizarPosicionActual(Parcela parcelaSiguiente) {
        this.tipoMovimiento.actualizarPosicion(parcelaSiguiente);
    }

    public void daniarJugador() {
        //this.emisor.notificarSubscriptores("log",this.representacionString() + " causo daño al jugador");

        if (this.estaVivo()){
            Juego.getInstance().daniarAlJugador(this.poderAtaque);
        }

        this.vida = new Vida(0);
    }

    public abstract void morir();

    public String sprayID() { return representacionString; }
    public String representacionString() { return representacionString; }

    public void avanzar(Mapa mapa) throws PasarelaInexistente{
        this.efectoEnemigo = this.efectoEnemigo.avanzar(this.cantidadMovimientos, this.tipoMovimiento, mapa);
        this.aniadirEvento(this.representacionString() + " avanza a posicion " + this.tipoMovimiento.representarUbicacion());
    }

    public boolean estaEnRango(Coordenada posicion, int distancia, TipoDeDefensa tipo ){
        return (this.tipoMovimiento.estaEnRango(posicion, distancia) && tipo.accept(this));
    }

    public void setEfectoEnemigo(Efecto nuevoEfecto){
        this.efectoEnemigo = nuevoEfecto;
    }


    public boolean esVisiblePara(Trampa tipo){
        return true;
    }

    public boolean esVisiblePara(Ataque ataque){
        return true;
    }
}