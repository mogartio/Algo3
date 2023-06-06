package edu.fiuba.algo3.modelo;

public abstract class Defensa {
    protected int daño;
    protected int estadoDeConstruccion; // empieza en un numero y a medida que pasan los turnos se decrementa
    //si es = 0 es porque la torre esta construida (bastante rancio)

    protected Rango pasarelas;

    public Defensa(){
        this.pasarelas = null;
        this.daño = 0;
    }
    public Defensa(Rango rango, int daño){
        this.pasarelas = rango;
        this.daño = daño;
    }

    public void pasarTurno(){
        if (estadoDeConstruccion == 0) {
        } else {
            estadoDeConstruccion = estadoDeConstruccion - 1;
        }
    }
    public boolean estaConstruida(){
        return estadoDeConstruccion == 0;
    }

    public void atacar(){
        Pasarela pasarelaSeleccionada = this.pasarelas.buscarPasarelaConEnemigo();
        pasarelaSeleccionada.dañarEnemigo(this.daño);
    }
}
