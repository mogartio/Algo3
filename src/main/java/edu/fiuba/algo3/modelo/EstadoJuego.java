package edu.fiuba.algo3.modelo;

public interface EstadoJuego {

    public EstadoJuego introducirEnemigo(Enemigo enemigo);

    public EstadoJuego actualizarConVidaDeJugador(int cantVida);

    void pasarTurno();
}
