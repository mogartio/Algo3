package edu.fiuba.algo3.modelo.Defensas;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import java.util.ArrayList;

import edu.fiuba.algo3.modelo.Observer.Emisor;
import edu.fiuba.algo3.modelo.Observer.Logger;
import edu.fiuba.algo3.modelo.Observer.Observable;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;


public abstract class Defensa extends Observable {
    protected int danio;
    protected Coordenada posicion;
    protected EstadoConstruccion estadoDeConstruccion;
    protected int rangoAtaque;

    protected Emisor emisor;

    public Defensa(int danio, int tiempoDeConstruccion, int rangoAtaque){
        this.posicion = null;
        this.danio = danio;
        this.rangoAtaque = rangoAtaque;
        this.estadoDeConstruccion = new EnConstruccion(tiempoDeConstruccion);

        Logger logger = new Logger();
        this.emisor = new Emisor();
        this.emisor.subcribir(logger);

    }

    public Defensa(Coordenada posicion, int danio, int tiempoDeConstruccion, int rangoAtaque){
        this.posicion = posicion;
        this.danio = danio;
        this.rangoAtaque = rangoAtaque;
        this.estadoDeConstruccion = new EnConstruccion(tiempoDeConstruccion);
    }

    public void asignarPosicion(Coordenada nuevaPosicion){
        this.posicion = nuevaPosicion;
    }

    public void pasarTurno(){
        this.estadoDeConstruccion = estadoDeConstruccion.pasoUnTurno(this.emisor, this);
    }

    //borrar esta funcion mas adelante
    public boolean estaConstruida(){
        return estadoDeConstruccion.estoyConstruida();
    }

    public void atacar(ArrayList<Enemigo> enemigos ){


        for(int i = 0; i < enemigos.size(); i++){
            if (enemigos.get(i).estaEnRango(this.posicion, this.rangoAtaque) && enemigos.get(i).estaVivo()){
                enemigos.get(i).recibirDanio(this.danio);
                break;
            }
        }
    }

    public abstract String representationString();
}
