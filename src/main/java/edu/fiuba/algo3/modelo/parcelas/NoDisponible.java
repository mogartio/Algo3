package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;

public class NoDisponible implements Construible{

    @Override
    public void construir(Coordenada coordenadaParcela, Parcela parcela){}

    @Override
    public boolean puedeConstruir(String unaDefensa){ return false; }

    @Override
    public boolean ocupada() {
        return true;
    }
}