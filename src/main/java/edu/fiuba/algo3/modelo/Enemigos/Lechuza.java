package edu.fiuba.algo3.modelo.Enemigos;

public class Lechuza extends Enemigo{

    public Lechuza(){
        super(5, 0, 5);
        this.tipoMovimiento = new MovimientoCateto(this);
    }

    @Override
    public void recibirDanio(int danio) {
        super.recibirDanio(danio);
        if (vida.obtenerPorcentajeDadoInicial(5) <= 50 ){
            this.tipoMovimiento = new MovimientoHipotenusa(this);
        }
    }

    @Override
    public void daniarJugador() {
        super.daniarJugador();
        //se debe  agregar la destruccion de la torre
    }

    public void morir(){
    }

    public String representacionString() {
        return "Lechuza";
    }

}