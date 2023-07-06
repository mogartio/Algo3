package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.Defensas.Defensa;
import javafx.scene.paint.Color;

public class Tierra extends Parcela{

    public Tierra(Coordenada coordenada){
        super(coordenada, new DisponibleDefensa());
        this.color = Color.GREEN;
    }

    public void construirDefensa(Defensa defensa){
        setConstruible(new NoDisponible());
        defensa.asignarPosicion(this.coordenada);
    }

    public boolean equals(Tierra tierra){
        return tierra.verificarPosicion(this.coordenada);
    }

    public boolean ocupada(){
        return false;
    }
}