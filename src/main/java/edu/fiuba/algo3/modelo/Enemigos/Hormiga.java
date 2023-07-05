package edu.fiuba.algo3.modelo.Enemigos;

import edu.fiuba.algo3.modelo.Defensas.Ataque;
import edu.fiuba.algo3.modelo.Defensas.TipoDeDefensa;
import edu.fiuba.algo3.modelo.Defensas.Trampa;
import edu.fiuba.algo3.modelo.Enemigos.Movimiento.Movimiento;
import edu.fiuba.algo3.modelo.Enemigos.Movimiento.MovimientoPasarela;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;

public class Hormiga extends Enemigo{
    private final int CREDITOS_HORMIGA = 1;
    public Hormiga() {
        super(1, 1, 1);
        this.tipoMovimiento = new MovimientoPasarela(this);
        this.representacionString = "Hormiga";
        this.sonido = "Avanzar";
    }

    public Hormiga(Pasarela posicionActual) { //Constructor para test
        super(1, 1, 1);
        this.tipoMovimiento = new MovimientoPasarela(this);
        this.tipoMovimiento.actualizarPosicion(posicionActual);
    }


    public void morir(){
        //emisor.notificarSubscriptores("log", "Hormiga muere y otorga " + CREDITOS_HORMIGA + " créditos al jugador");
        Juego.getInstance().agregarARachaDeHormigas();
        this.sonido = "Morir";
        setChanged();
    }

    public boolean esVisiblePara(Trampa tipo){
        return true;
    }

    public boolean esVisiblePara(Ataque ataque){
        return true;
    }
}
