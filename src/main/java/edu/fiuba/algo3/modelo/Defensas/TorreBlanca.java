package edu.fiuba.algo3.modelo.Defensas;

import edu.fiuba.algo3.modelo.miscelanea.Coordenada;

public class TorreBlanca extends Defensa{

    public TorreBlanca(){
        super( 1, 1, 3);
    }

    public TorreBlanca(Coordenada posicion){
        super(posicion, 1, 1, 3);
    }

    @Override
    public String representationString(){
        return "TorreBlanca";
    }
}
