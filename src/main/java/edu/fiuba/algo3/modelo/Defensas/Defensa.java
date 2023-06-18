package edu.fiuba.algo3.modelo.Defensas;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import java.util.ArrayList;

import edu.fiuba.algo3.modelo.Observer.Emisor;
import edu.fiuba.algo3.modelo.Observer.Logger;
import edu.fiuba.algo3.modelo.Observer.Observable;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;


public abstract class Defensa extends Observable {
    protected Coordenada posicion;
    protected EstadoConstruccion estadoDeConstruccion;
    protected int rangoAtaque;
<<<<<<< HEAD
    protected TipoDeDefensa tipoDeDefensa;

=======
>>>>>>> 829fc41bf286f6e0365c850723bf592e74f5011b
    protected Emisor emisor;

    public Defensa(int tiempoDeConstruccion, int rangoAtaque, TipoDeDefensa tipoDeDefensa){
        this.posicion = null;
        this.rangoAtaque = rangoAtaque;
        this.estadoDeConstruccion = new EnConstruccion(tiempoDeConstruccion);
        this.tipoDeDefensa = tipoDeDefensa;

        //CAMBIAR ESTO
        Logger logger = new Logger();
        this.emisor = new Emisor();
        this.emisor.subcribir(logger);

    }

    public Defensa(Coordenada posicion, int tiempoDeConstruccion, int rangoAtaque){
        this.posicion = posicion;
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
        this.estadoDeConstruccion.atacar(tipoDeDefensa, enemigos, this.posicion, this.rangoAtaque, this.representationString(), this.emisor);
    }

    public abstract String representationString();
}
