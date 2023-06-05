package edu.fiuba.algo3.modelo;

public abstract class Enemigo {
    private Vida vida = new Vida(1);

    public int cantidadMovimientos;
    public int creditosRecompensa;

    public Pasarela posicionActual;

    public void recibirDaño(int daño){
        this.vida.quitarVida(daño);
        if (vida.obtenerPuntos() == 0) {
            this.morir();
        }
    }
    public abstract void morir();

    public void avanzar() {
        for (int i = 0; i < cantidadMovimientos; i++) {
            this.posicionActual = posicionActual.verSiguiente();
        }
    }

    public Pasarela verPosicion() { return posicionActual; }

    public boolean muerto(){
        return (this.vida.obtenerPuntos() == 0);
    }
}