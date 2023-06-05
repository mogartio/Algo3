package edu.fiuba.algo3.modelo;

public class Enemigo {
    private Vida vida = new Vida(1);
    public void recibirDaño(int daño){
        this.vida.quitarVida(daño);
    }

    public boolean muerto(){
        return (this.vida.obtenerPuntos() == 0);
    }
}