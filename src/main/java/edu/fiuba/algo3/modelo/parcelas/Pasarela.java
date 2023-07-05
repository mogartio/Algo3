package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.Enemigos.Movimiento.Movimiento;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Pasarela extends Parcela {
    private TipoPasarela tipo;
    private Pasarela siguientePasarela;

    public Pasarela(Coordenada coordenada,Pasarela siguientePasarela, TipoPasarela tipo)  {
        super(coordenada, tipo.getConstruible());
        this.color = tipo.getColor();
        this.siguientePasarela = siguientePasarela;
        this.tipo = tipo;
    }
    public Pasarela(Coordenada coordenada, TipoPasarela tipo) {
        super(coordenada, tipo.getConstruible());
        this.siguientePasarela = null;
        this.tipo = tipo;
    }

    public Color getColor() { return tipo.getColor(); }

    public void agregarSiguiente(Pasarela siguientePasarela){
        this.siguientePasarela = siguientePasarela;
    }
    public Pasarela verSiguiente(){return siguientePasarela;}

    public void actualizarPosicion(Movimiento tipoMovimiento) {
        tipo.moverEnemigos(tipoMovimiento, this.verSiguiente());
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

    public boolean equals(Pasarela pasarela){
        return pasarela.verificarPosicion(this.coordenada);
    }

    public void actualizarTipo(TipoPasarela tipoPasarela){
        this.tipo = tipoPasarela;
    }

    public boolean ocupada(){
        return false;
    }
}