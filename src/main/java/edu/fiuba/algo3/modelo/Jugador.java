package edu.fiuba.algo3.modelo;

import java.util.Stack;

public class Jugador {
    private static final Jugador INSTANCE = new Jugador();
    private Jugador() {}
    public static Jugador getInstance() { return INSTANCE; }
    private Vida vida;
    private int creditos;
    private int contadorHormigasMuertas;
    public Jugador(Vida vidaInicial, int creditosIniciales){
        vida = vidaInicial;
        creditos = creditosIniciales;
        contadorHormigasMuertas = 0;
    }
    public int obtenerCreditos() { return creditos; }

    public Stack<Defensa> verificarConstruccionesPosibles(Tienda proveedor) {
        return proveedor.catalogoDisponible(creditos);
    }
    public int obtenerVida() {
        return vida.obtenerPuntos();
    }
    public void recompensar(int creditosRecibidos, boolean esHormiga){
        int MULTIPLICADOR_HORMIGAS_MUERTAS = 2;
        int REQUISITO_HORMIGAS_MUERTAS = 10;

        if (esHormiga && contadorHormigasMuertas >= REQUISITO_HORMIGAS_MUERTAS)
            creditosRecibidos = creditosRecibidos * MULTIPLICADOR_HORMIGAS_MUERTAS;
        this.creditos += creditosRecibidos;
    }

    public void recibirDaño(int unDaño) {
        vida.quitarVida(unDaño);
    }

    public boolean estaVivo(){
        return vida.sigueVivo();
    }

    public void registrarHormigaMuerta() {
        this.contadorHormigasMuertas ++;
    }
}
