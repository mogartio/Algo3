package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.Defensas.Defensa;

import java.util.ArrayList;

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
    public Pasarela verSiguiente(){return siguientePasarela;}

    public void actualizarPosicion(Enemigo enemigo) {

        tipo.moverEnemigos(enemigo, this.verSiguiente());
    }

    public boolean estaEnRango(Coordenada otraCoordenada, int distancia) {
        return coordenada.estaEnRango(otraCoordenada, distancia);
    }

    public Pasarela obtenerSiguienteExcluyendo(ArrayList<Pasarela> posibles, Pasarela unaExcluida) {
        Pasarela siguiente = null;

        for (Pasarela posible : posibles) {
            if (posible.estaEnRango(this.coordenada, 1) && (unaExcluida == null || !posible.esIgual(unaExcluida)) && !posible.esIgual(this)) {
                siguiente = posible;
                break;
            }
        }

        return siguiente;
    }

    public boolean esIgual(Pasarela otraPasarela) {
        return otraPasarela.estaEnRango(this.coordenada, 0);
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