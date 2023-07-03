package edu.fiuba.algo3.modelo.Defensas;

import edu.fiuba.algo3.modelo.Defensas.EstadoDeConstruccion.EstadoConstruccion;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;

import java.util.ArrayList;

public class Destruida implements EstadoConstruccion {

    @Override
    public EstadoConstruccion pasoUnTurno(Defensa defensa) {
        return null;
    }

    @Override
    public boolean estoyConstruida() {
        return false;
    }

    @Override
    public void setSonido() {

    }

    @Override
    public String versonido() {
        return null;
    }

    @Override
    public void atacar(TipoDeDefensa tipoDeDefensa, ArrayList<Enemigo> enemigos, Coordenada coordenada, int rangoAtaque, String nombre) {}

}
