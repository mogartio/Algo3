package edu.fiuba.algo3.modelo.Defensas;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
<<<<<<< HEAD
import edu.fiuba.algo3.modelo.Enemigos.Relentizado;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;

import java.util.ArrayList;
=======
import edu.fiuba.algo3.modelo.Enemigos.Efecto.Relentizado;
>>>>>>> b02712a9c6565ad44076871bf15b5d3ed2dca330

public class Trampa implements TipoDeDefensa{
    private double porcentajeRelentizacion;

    public Trampa(double porcentajeRelentizacion){
        this.porcentajeRelentizacion = porcentajeRelentizacion;
    }

    @Override
    public void atacar(ArrayList<Enemigo> enemigos, Coordenada coordenada, int rangoAtaque){

        for (Enemigo enemigo : enemigos) {
            if ( enemigo.estaEnRango(coordenada, rangoAtaque) ){

                //emisor.notificarSubscriptores("log", nombre + " ataca a " + enemigo.representacionString() + " en " + enemigo.represtacionUbicacion());
                enemigo.setEfectoEnemigo(new Relentizado(1, this.porcentajeRelentizacion));
            }
        }
    }
}
