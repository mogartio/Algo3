package edu.fiuba.algo3.modelo.Defensas;

import edu.fiuba.algo3.modelo.miscelanea.Coordenada;

public class TorrePlateada extends Defensa{

    public TorrePlateada(){
        super(2,2,5);
    }
    public TorrePlateada(Coordenada posicion){
        super(posicion, 2, 2, 5);
    }
}
