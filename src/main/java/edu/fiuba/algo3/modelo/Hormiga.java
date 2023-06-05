package edu.fiuba.algo3.modelo;

public class Hormiga extends Enemigo{
    private final int CREDITOS = 1;
    public Hormiga(Pasarela pasarelaInicial) {
        this.cantidadMovimientos = 1;
        this.posicionActual = pasarelaInicial;
    }
    public void morir(){
        int CREDITOS_HORMIGA = 1;
        Jugador.getInstance().registrarHormigaMuerta();
        Jugador.getInstance().recompensar(CREDITOS_HORMIGA, true);
    }
}
