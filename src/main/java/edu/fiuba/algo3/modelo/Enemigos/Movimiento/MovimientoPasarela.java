package edu.fiuba.algo3.modelo.Enemigos.Movimiento;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.PasarelaInexistente;
import edu.fiuba.algo3.modelo.juego.Mapa;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Parcela;

public class MovimientoPasarela implements Movimiento {

    private Coordenada meta;
    public Coordenada posicionActual;

    private Parcela parcelaActual;

    private Enemigo enemigo;

    public MovimientoPasarela(Enemigo enemigo){
        this.enemigo = enemigo;
        this.posicionActual = new Coordenada(0, 0);
    }

    @Override
    public void reasignarPosiciones(Coordenada coordInicial, Coordenada coordFinal){
        this.posicionActual = coordInicial;
        this.meta = coordFinal;
    }

    @Override
    public void establecerInicioYMeta(Parcela parcelaInicial, Parcela parcelaFinal){
        this.parcelaActual = parcelaInicial;
        parcelaInicial.actualizarUbicacion(this);
        parcelaFinal.actulizarMeta(this);
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
    public void avanzar(int cantidadPasos, Mapa mapa) throws PasarelaInexistente {
        for(int pasos = 0; pasos < cantidadPasos; pasos++){
            this.parcelaActual.actualizarPosicion(this);
        }
        if(this.parcelaActual == null){
            throw new PasarelaInexistente("El enemigo se movio a un lugar invalido");
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
