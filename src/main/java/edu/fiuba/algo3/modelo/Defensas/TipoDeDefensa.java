package edu.fiuba.algo3.modelo.Defensas;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;

import java.util.ArrayList;


public interface TipoDeDefensa {
    void atacar(ArrayList<Enemigo> enemigos, Coordenada coordenada, int rangoAtaque);
}
