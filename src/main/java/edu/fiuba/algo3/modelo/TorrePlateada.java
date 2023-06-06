package edu.fiuba.algo3.modelo;

public class TorrePlateada extends Defensa{



    public TorrePlateada(){
        super();
        this.estadoDeConstruccion = 2;
    }

    public TorrePlateada(Rango rango){

        super(rango, 2);
        estadoDeConstruccion = 2;
    }
}
