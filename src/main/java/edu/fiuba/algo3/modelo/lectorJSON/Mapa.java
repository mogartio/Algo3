package edu.fiuba.algo3.modelo.lectorJSON;

import edu.fiuba.algo3.modelo.parcelas.Parcela;

import java.util.ArrayList;

public class Mapa {
    ArrayList<Parcela> mapa;

    public Mapa() {
        mapa = new ArrayList<Parcela>();
    }
    public void agregar(Parcela parcelaNueva) {
        mapa.add(parcelaNueva);
    }
    public void ver() {
        mapa.forEach(parcela -> System.out.println(parcela));
    }
}
