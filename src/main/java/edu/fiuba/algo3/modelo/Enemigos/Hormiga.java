package edu.fiuba.algo3.modelo.Enemigos;

import edu.fiuba.algo3.modelo.Defensas.TipoDeDefensa;
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

    @Override
    public String verSonido() {
        return sonido;
    }

    public void morir(){
        //emisor.notificarSubscriptores("log", "Hormiga muere y otorga " + CREDITOS_HORMIGA + " créditos al jugador");
        Juego.getInstance().agregarARachaDeHormigas();
        this.sonido = "Morir";
        setChanged();
    }

    @Override
    public boolean esVisiblePara(TipoDeDefensa tipo){
        return true;
    }
}
