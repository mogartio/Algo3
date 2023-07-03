package edu.fiuba.algo3.modelo.Defensas;

import edu.fiuba.algo3.modelo.miscelanea.Coordenada;

public class TorreBlanca extends Defensa{

    public TorreBlanca(){
        super(1, 3, new Ataque(1));
        this.representacionString = "TorreBlanca";
    }

    public TorreBlanca(Coordenada posicion){
        super(posicion, 1, 3);
        this.representacionString = "TorreBlanca";
    }
}
