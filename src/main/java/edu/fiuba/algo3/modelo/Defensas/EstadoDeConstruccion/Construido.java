package edu.fiuba.algo3.modelo.Defensas.EstadoDeConstruccion;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.TipoDeDefensa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.vista.Sprayable;

import java.util.ArrayList;

public class Construido implements EstadoConstruccion {
    public String sonido = "Atacar";

    public EstadoConstruccion pasoUnTurno(Defensa defensa){
        return this;
    }

    public boolean estoyConstruida(){
        return true;
    }

    @Override
    public void atacar(TipoDeDefensa tipoDeDefensa, ArrayList<Enemigo> enemigos, Coordenada coordenada, int rangoAtaque, String nombre){

        tipoDeDefensa.atacar(enemigos, coordenada, rangoAtaque);
    }
    @Override
    public String sprayID(Sprayable sprayable) {
        return sprayable.sprayID();
    }

}