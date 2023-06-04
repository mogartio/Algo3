package edu.fiuba.algo3.modelo;

import java.util.Stack;

public class Jugador {
    private Vida vida;
    private int creditos;

    public Jugador(Vida vidaInicial,int creditosIniciales){
        vida = vidaInicial;
        creditos = creditosIniciales;
    }

    public int obtenerVida() {
        return vida.obtenerPuntos();
    }

    public int obtenerCreditos() {
        return creditos;
    }

    public Stack<Defensa> verificarConstruccionesPosibles(Tienda proveedor) {
        return proveedor.catalogoDisponible(creditos);
    }
}
