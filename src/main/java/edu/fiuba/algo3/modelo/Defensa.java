package edu.fiuba.algo3.modelo;

public abstract class Defensa {
    protected int estadoDeConstruccion; // empieza en un numero y a medida que pasan los turnos se decrementa
    //si es = 0 es porque la torre esta construida (bastante rancio)

    public void pasarTurno(){
        if (estadoDeConstruccion == 0) {
        }else {
            estadoDeConstruccion = estadoDeConstruccion - 1;
        }
    }
    public boolean estaConstruida(){
        return estadoDeConstruccion == 0;
    }
}
