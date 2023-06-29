package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.Defensas.Defensa;

import java.util.Map;

public class Rocosa extends Parcela {
    public Rocosa(Coordenada coordenada){

        super(coordenada, new NoDisponible());
    }

    public boolean equals(Rocosa rocosa){
        return rocosa.verificarPosicion(this.coordenada);
    }

    public boolean ocupada(){
        return false;
    }
}