package edu.fiuba.algo3.modelo.lectorJSON;

import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Parcela;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Mapa {
    Dictionary<Coordenada, Parcela> mapa;
    public Mapa() {
        mapa = new Hashtable<>();
    }
    public void agregar(Coordenada coordenadaNueva, Parcela parcelaNueva) { mapa.put(coordenadaNueva, parcelaNueva);}

    public Parcela ver(Coordenada coordenada) {
        return (mapa.get(coordenada));
    }
}
