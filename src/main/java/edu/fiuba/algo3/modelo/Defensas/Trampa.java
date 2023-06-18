package edu.fiuba.algo3.modelo.Defensas;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Relentizado;

public class Trampa implements TipoDeDefensa{
    private double porcentajeRelentizacion;

    public Trampa(double porcentajeRelentizacion){
        this.porcentajeRelentizacion = porcentajeRelentizacion;
    }

    @Override
    public void atacar(Enemigo enemigo){
        enemigo.setEfectoEnemigo(new Relentizado(1, this.porcentajeRelentizacion));
    }
}
