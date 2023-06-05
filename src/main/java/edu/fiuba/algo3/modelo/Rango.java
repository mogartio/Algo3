package edu.fiuba.algo3.modelo;
import java.util.ArrayList;
public class Rango {
    private ArrayList<Pasarela> pasarelas;

    public Rango(ArrayList<Pasarela> pasarelas){
        this.pasarelas = pasarelas;
    }

    public Pasarela buscarPasarela(){
        Pasarela pasarelaSeleccionada;
        for (int posicion = this.pasarelas.size(); posicion >= 0; posicion-- ){
            if(this.pasarelas.get(posicion).tieneEnemigos()){
                pasarelaSeleccionada = this.pasarelas.get(posicion);
            };
        };
        return pasarelaSeleccionada;
    }

}