package edu.fiuba.algo3.modelo.Defensas;

public class Construido implements EstadoConstruccion {
    public EstadoConstruccion pasoUnTurno(){
        return new Construido();
    }
    public boolean estoyConstruida(){

        return true;
    }
}