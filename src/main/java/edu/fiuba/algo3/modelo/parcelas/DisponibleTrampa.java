package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.TrampaArenosa;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;

public class DisponibleTrampa implements Construible{

    @Override
    public void construir(Coordenada coordenadaParcela, Defensa defensaConstruir, Parcela parcela){}
    public void construir(Coordenada coordenadaParcela, TrampaArenosa trampaConstruir, Parcela parcela){
        parcela.setConstruible(new NoDisponible());
        trampaConstruir.asignarPosicion(coordenadaParcela);
    }

    public boolean puedeConstruir(){
        return true;
    }
}
