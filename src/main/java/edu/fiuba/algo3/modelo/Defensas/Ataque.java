package edu.fiuba.algo3.modelo.Defensas;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;

import java.util.ArrayList;


public class Ataque  implements TipoDeDefensa{
    private int danio;

    public Ataque(int danio){
        this.danio = danio;
    }
    @Override
    public void atacar(ArrayList<Enemigo> enemigos, Coordenada coordenada, int rangoAtaque){

        for (Enemigo enemigo : enemigos) {
            if ( enemigo.estaEnRango(coordenada, rangoAtaque) ){

                //emisor.notificarSubscriptores("log", nombre + " ataca a " + enemigo.representacionString() + " en " + enemigo.represtacionUbicacion());
                enemigo.recibirDanio(this.danio);
                break;
            }
        }
    }

}
