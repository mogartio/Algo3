package edu.fiuba.algo3.modelo.Defensas.EstadoDeConstruccion;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.TipoDeDefensa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;

import java.util.ArrayList;

public class Construido implements EstadoConstruccion {
    public String sonido = "Construir";

    public EstadoConstruccion pasoUnTurno(Defensa defensa){
        return this;
    }

    public boolean estoyConstruida(){
        return true;
    }

    @Override
    public void setSonido() {
        this.sonido = "Atacar";
    }

    @Override
    public String versonido() {
        return sonido;
    }

    @Override
    public void atacar(TipoDeDefensa tipoDeDefensa, ArrayList<Enemigo> enemigos, Coordenada coordenada, int rangoAtaque, String nombre){

        tipoDeDefensa.atacar(enemigos, coordenada, rangoAtaque);
        this.setSonido();
    }
}