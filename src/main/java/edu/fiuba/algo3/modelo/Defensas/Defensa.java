package edu.fiuba.algo3.modelo.Defensas;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import java.util.ArrayList;
import edu.fiuba.algo3.modelo.Observer.Observable;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;


public abstract class Defensa {
    protected int danio;
    protected Coordenada posicion;
    protected EstadoConstruccion estadoDeConstruccion;
    protected int tiempoDeConstruccion;
    protected int rangoAtaque;

    public Defensa(int danio, int tiempoDeConstruccion, int rangoAtaque){
        this.posicion = null;
        this.danio = danio;
        this.rangoAtaque = rangoAtaque;
        this.estadoDeConstruccion = new EnConstruccion(tiempoDeConstruccion);
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
        this.estadoDeConstruccion = estadoDeConstruccion.pasoUnTurno();
    }

    //borrar esta funcion mas adelante
    public boolean estaConstruida(){
        return estadoDeConstruccion.estoyConstruida();
    }

    public void atacar(ArrayList<Enemigo> enemigos ){


        for(int i = 0; i < enemigos.size(); i++){
            if (enemigos.get(i).estaEnRango(this.posicion, this.rangoAtaque)){
                enemigos.get(i).recibirDaño(this.danio);
                break;
            }
        }

    }
}
