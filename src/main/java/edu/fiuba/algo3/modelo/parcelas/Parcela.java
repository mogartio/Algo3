package edu.fiuba.algo3.modelo.parcelas;


import edu.fiuba.algo3.modelo.Enemigos.Movimiento;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.Defensas.Defensa;

public abstract class Parcela {
    protected Coordenada coordenada;
    protected Construible construible;

    public Parcela(Coordenada coordenada, Construible construible){
        this.coordenada = coordenada;
        this.construible = construible;
    }

    public void construirDefensa(Defensa defensa){
        this.construible.construir(this.coordenada, defensa, this);
    }

    public boolean equals(Object pasarela){
        return false;
    }

    public boolean verificarPosicion(Coordenada coordenada){
        return coordenada.equals(this.coordenada);
    }

    public void actualizarUbicacion(Movimiento movimientoEnemigo){
        movimientoEnemigo.actualizarUbicacion(this.coordenada);
    }

    public void actulizarMeta(Movimiento movimientoEnemigo){
        movimientoEnemigo.establecerMeta(this.coordenada);
    }

    public boolean estaEnRango(Coordenada otraCoordenada, int distancia) {
        return coordenada.estaEnRango(otraCoordenada, distancia);
    }

    public void setConstruible(Construible nuevoConstruible){
        this.construible = nuevoConstruible;
    }

    public boolean ocupada(){
        return !(construible.puedeConstruir());
    }

    public void actualizarPosicion(Movimiento tipoMovimiento){}

}