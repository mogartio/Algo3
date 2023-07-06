package edu.fiuba.algo3.modelo.Defensas;
import edu.fiuba.algo3.modelo.Defensas.EstadoDeConstruccion.Construido;
import edu.fiuba.algo3.modelo.Defensas.EstadoDeConstruccion.EnConstruccion;
import edu.fiuba.algo3.modelo.Defensas.EstadoDeConstruccion.EstadoConstruccion;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import java.util.ArrayList;

import edu.fiuba.algo3.vista.Sprayable;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;


public abstract class Defensa extends Sprayable {
    protected Coordenada posicion;
    protected EstadoConstruccion estadoDeConstruccion;
    protected int rangoAtaque;
    protected TipoDeDefensa tipoDeDefensa;
    protected String representacionString;

    public Defensa(int tiempoDeConstruccion, int rangoAtaque, TipoDeDefensa tipoDeDefensa){
        this.posicion = null;
        this.rangoAtaque = rangoAtaque;
        this.estadoDeConstruccion = new EnConstruccion(tiempoDeConstruccion);
        this.tipoDeDefensa = tipoDeDefensa;
        if (tiempoDeConstruccion == 0){
            this.estadoDeConstruccion  = new Construido();
        }

    }

    public Defensa(Coordenada posicion, int tiempoDeConstruccion, int rangoAtaque){
        this.posicion = posicion;
        this.rangoAtaque = rangoAtaque;
        this.estadoDeConstruccion = new EnConstruccion(tiempoDeConstruccion);
        if (tiempoDeConstruccion == 0){
            this.estadoDeConstruccion  = new Construido();
        }
    }

    public void actualizarEstado(EstadoConstruccion nuevoEstado) {
        this.estadoDeConstruccion = nuevoEstado;
        this.aniadirEvento(this.sprayID() + " en posicion " + this.posicion.representacionString() + " esta ahora como " + nuevoEstado);
    }

    public void asignarPosicion(Coordenada nuevaPosicion){
        this.posicion = nuevaPosicion;
    }

    public void pasarTurno(){
        this.estadoDeConstruccion = estadoDeConstruccion.pasoUnTurno(this);
    }

    //borrar esta funcion mas adelante
    public boolean estaConstruida(){
        return estadoDeConstruccion.estoyConstruida();
    }

    public void atacar(ArrayList<Enemigo> enemigos ){
        this.aniadirEvento("torre en estado " + this.representacionString() + " en posicion " + this.posicion.representacionString() + " ataca");
        this.estadoDeConstruccion.atacar(tipoDeDefensa, enemigos, this.posicion, this.rangoAtaque, this.representacionString());
    }

    public boolean destruir() {
        return this.estadoDeConstruccion.destruir(this);
    }

    public String representacionString() { return this.estadoDeConstruccion.sprayID(this); }

    public String sprayID() { return this.sprayID; }

}
