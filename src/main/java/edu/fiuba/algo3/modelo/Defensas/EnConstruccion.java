package edu.fiuba.algo3.modelo.Defensas;

public class EnConstruccion implements EstadoConstruccion{
    private int turnosHastaTerminar;
    public EnConstruccion(int turnosHastaTerminar) { this.turnosHastaTerminar = turnosHastaTerminar; }

    public EstadoConstruccion pasoUnTurno(){
        if (turnosHastaTerminar - 1 == 0){
            System.out.println("paso");
            return new Construido();
        }

        return new EnConstruccion(turnosHastaTerminar - 1);

    }
    public boolean estoyConstruida(){
        return false;
    }
}