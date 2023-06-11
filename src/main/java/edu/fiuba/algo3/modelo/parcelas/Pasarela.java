package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.Defensas.Defensa;

public class Pasarela extends Parcela {
    private TipoPasarela tipo;

    private Pasarela siguientePasarela;

    public Pasarela(Coordenada coordenada,Pasarela siguientePasarela, TipoPasarela tipo) {
        super(coordenada, new NoDisponible());
        this.siguientePasarela = siguientePasarela;
        this.tipo = tipo;
    }
    public Pasarela(Coordenada coordenada, TipoPasarela tipo) {
        super(coordenada, new NoDisponible());
        this.siguientePasarela = null;
        this.tipo = tipo;
    }

    public void agregarSiguiente(Pasarela siguientePasarela){
        this.siguientePasarela = siguientePasarela;
    }
    private Pasarela verSiguiente(){return siguientePasarela;}

    public void actualizarPosicion(Enemigo enemigo) {

            tipo.moverEnemigos(enemigo, this.verSiguiente());
    }

    public boolean estaEnRango(Coordenada otraCoordenada, int distancia) {
        return coordenada.estaEnRango(otraCoordenada, distancia);
    }

    public void construirDefensa(Defensa defensa){}

    public boolean equals(Pasarela pasarela){
        return pasarela.verificarPosicion(this.coordenada);
    }

    public boolean ocupada(){
        return false;
    }

    public void actualizarTipo(TipoPasarela tipoPasarela){
        this.tipo = tipoPasarela;
    }
}