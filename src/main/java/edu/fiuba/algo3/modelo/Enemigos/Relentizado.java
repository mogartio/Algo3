package edu.fiuba.algo3.modelo.Enemigos;

import edu.fiuba.algo3.modelo.Excepciones.PasarelaInexistente;
import edu.fiuba.algo3.modelo.lectorJSON.Mapa;

public class Relentizado implements Efecto{

    int durabilidad;
    double porcentajeDeRelentizacion;

    public Relentizado(int durabilidad, double porcentajeDeRelentizacion){
        this.durabilidad = durabilidad;
        this.porcentajeDeRelentizacion = porcentajeDeRelentizacion;
    }

    public Efecto avanzar(int cantPasos, Movimiento movimientoEnemigo, Mapa mapa) throws PasarelaInexistente {
        int pasosModificados = ((int) (cantPasos * porcentajeDeRelentizacion));

        movimientoEnemigo.avanzar(pasosModificados, mapa);
        this.durabilidad --;

        if (this.durabilidad == 0){return new Ninguno();}
        else {return this;}
    }
}
