package edu.fiuba.algo3.modelo.Defensas;
import edu.fiuba.algo3.modelo.Defensas.EstadoDeConstruccion.EnConstruccion;
import edu.fiuba.algo3.modelo.Defensas.EstadoDeConstruccion.EstadoConstruccion;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import java.util.ArrayList;

import edu.fiuba.algo3.modelo.Enemigos.Sprayable;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;


public abstract class Defensa extends Sprayable {
    private  String sonido;
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
        this.sonido = this.estadoDeConstruccion.verSonido();
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
        this.estadoDeConstruccion = estadoDeConstruccion.pasoUnTurno(this);
    }

    //borrar esta funcion mas adelante
    public boolean estaConstruida(){
        return estadoDeConstruccion.estoyConstruida();
    }

    public void atacar(ArrayList<Enemigo> enemigos ){
        this.estadoDeConstruccion.atacar(tipoDeDefensa, enemigos, this.posicion, this.rangoAtaque, this.representationString());
        setChanged();
    }

    public String representationString() { return this.representacionString; };

    public ArrayList<String> ObtenerSprayIDYPosicion(){
        ArrayList<String> datos = new ArrayList<>();

        if(this.estadoDeConstruccion.estoyConstruida()){
            datos.add(this.representationString());
            datos.add(this.posicion.representacionString());
            datos.add(this.estadoDeConstruccion.verSonido());
        }
        return datos;
    }

}
