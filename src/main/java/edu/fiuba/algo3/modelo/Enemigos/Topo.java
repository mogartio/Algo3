package edu.fiuba.algo3.modelo.Enemigos;

import edu.fiuba.algo3.modelo.Defensas.Ataque;
import edu.fiuba.algo3.modelo.Defensas.TipoDeDefensa;
import edu.fiuba.algo3.modelo.Defensas.Trampa;
import edu.fiuba.algo3.modelo.Enemigos.Movimiento.MovimientoPasarela;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Parcela;

public class Topo extends Enemigo{
    private int movimientosRealizados;

    public Topo(int turno){
        super(1, 0, 1);

        if (turno % 2 == 0){
            this.poderAtaque=2;
        } else {
            this.poderAtaque= 5;
        }

        this.representacionString = "Topo";

        this.tipoMovimiento = new MovimientoPasarela(this);
    }

    public void cambiarAtaque(){
        if (poderAtaque == 2){
            this.poderAtaque = 5;
        } else {
            this.poderAtaque = 2;
        }
    }

    @Override
    public String verSonido() {
        return null;
    }

    @Override
    public void actualizarPosicionActual(Parcela parcelaSiguiente) {
        super.actualizarPosicionActual(parcelaSiguiente);
        cambiarAtaque();

        if (movimientosRealizados == 5){
            this.cantidadMovimientos = 2;
        } else if (movimientosRealizados == 10){
            this.cantidadMovimientos = 3;
        }

    }

    @Override
    public boolean esVisiblePara(TipoDeDefensa tipo){
        return true;
    }

    public boolean esVisiblePara(Ataque ataque){
        return false;
    }

    public void morir(){
    }
}
