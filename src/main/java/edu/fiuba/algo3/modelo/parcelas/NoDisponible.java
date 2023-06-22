package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;

public class NoDisponible implements Construible{
    public void construir(Coordenada coordenadaParcela, Defensa defensaConstruir, Parcela parcela){}

    public boolean puedeConstruir(){
        return false;
    }
}