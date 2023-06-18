package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.TrampaArenosa;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;

public class DisponibleDefensa implements Construible{

    @Override
    public void construir(Coordenada coordenadaParcela, Defensa defensaConstruir, Parcela parcela){
        parcela.setConstruible(new NoDisponible());
        defensaConstruir.asignarPosicion(coordenadaParcela);
    }

    public void construir(Coordenada coordenadaParcela, TrampaArenosa defensaConstruir, Parcela parcela){}

    public boolean puedeConstruir(){
        return true;
    }

}
