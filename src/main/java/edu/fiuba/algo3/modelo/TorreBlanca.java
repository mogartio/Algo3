package edu.fiuba.algo3.modelo;

public class TorreBlanca extends Defensa{

    public TorreBlanca(){
        super();
        this.estadoDeConstruccion = 1;
    }

    public TorreBlanca(Rango rango){
        super(rango, 1);
        estadoDeConstruccion = 1;
    }
}
