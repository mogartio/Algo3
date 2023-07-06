package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import javafx.scene.paint.Color;

public class Rocosa extends Parcela {
    public Rocosa(Coordenada coordenada){

        super(coordenada, new NoDisponible());
        this.color = Color.GREY;
    }

    public boolean equals(Rocosa rocosa){
        return rocosa.verificarPosicion(this.coordenada);
    }

    public boolean ocupada(){
        return false;
    }
}