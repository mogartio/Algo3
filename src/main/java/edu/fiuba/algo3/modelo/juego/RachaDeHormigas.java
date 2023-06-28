package edu.fiuba.algo3.modelo.juego;

public class RachaDeHormigas {
    int contador;

    public RachaDeHormigas(){
        contador = 0;
    }

    public void agregarALaRacha(){
        contador = contador + 1;
        int multiplicador = 1;

        if (contador > 10) {
            multiplicador = 2;
        }

        Juego.getInstance().recompensarJugador(1*multiplicador);
    }
}
