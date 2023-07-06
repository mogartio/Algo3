package edu.fiuba.algo3.modelo.Enemigos.Movimiento;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.juego.Mapa;
import edu.fiuba.algo3.modelo.miscelanea.AlgoritmoDeBresenham;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Parcela;

import java.util.LinkedList;

public class MovimientoHipotenusa implements Movimiento {

    private LinkedList<Coordenada> camino;

    private Coordenada meta;

    public Coordenada posicionActual;

    private Parcela parcelaActual;

    private Enemigo enemigo;

    public MovimientoHipotenusa(Enemigo enemigo){
        this.enemigo = enemigo;
    }

    public void reasignarPosiciones(Coordenada coordInicial, Coordenada coordFinal){
        this.posicionActual = coordInicial;
        this.meta = coordFinal;
        this.camino = AlgoritmoDeBresenham.getCamino(this.posicionActual, this.meta);
    }
    @Override
    public void establecerInicioYMeta(Parcela parcelaInicial, Parcela parcelaFinal){
        this.parcelaActual = parcelaInicial;
        parcelaInicial.actualizarUbicacion(this);
        parcelaFinal.actulizarMeta(this);

        this.camino = AlgoritmoDeBresenham.getCamino(this.posicionActual, this.meta);
    }

    @Override
    public void actualizarPosicion(Parcela parcelaSiguiente){
        this.parcelaActual = parcelaSiguiente;
        parcelaSiguiente.actualizarUbicacion(this);
    }

    @Override
    public void actualizarCoordenadaActual(Coordenada posicionNueva){
        this.posicionActual = posicionNueva;
    }

    @Override
    public void establecerMeta(Coordenada coordenada){
        this.meta = coordenada;
    }

    @Override
    public void avanzar(int cantidadPasos, Mapa mapa){

        for(int posicion = 0; posicion < cantidadPasos; posicion++){

            if ( !( camino.peek() == null ) ) {
                this.posicionActual = this.camino.poll();
                this.parcelaActual = mapa.ver(this.posicionActual);
            } else {
                enemigo.daniarJugador();
                break;
            }

        }
    }

    @Override
    public void daniarJugador(){
        this.enemigo.daniarJugador();
    }

    @Override
    public boolean estaEnRango(Coordenada coordenada, int distancia){
        return this.posicionActual.estaEnRango(coordenada, distancia);
    }

    @Override
    public String representarUbicacion(){
        return this.posicionActual.representacionString();
    }

    @Override
    public Movimiento setMovimiento(Movimiento otroMovimiento){
        otroMovimiento.reasignarPosiciones(this.posicionActual, this.meta);

        return otroMovimiento;
    }

}
