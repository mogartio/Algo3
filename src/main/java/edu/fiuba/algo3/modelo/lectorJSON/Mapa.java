package edu.fiuba.algo3.modelo.lectorJSON;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.LinkedList;

public class Mapa {
    Hashtable<Coordenada, Parcela> mapa;
    LinkedList< ArrayList<Enemigo> > oleadas;
    Pasarela pasarelaInicial;

    public Mapa() {
        mapa = new Hashtable<Coordenada, Parcela>();
    }

    public void setPasarelaInicial(Pasarela pasarelaInicial) {
        this.pasarelaInicial = pasarelaInicial;
    }

    public void cargarOleadas(LinkedList< ArrayList<Enemigo> > oleadas) {
        this.oleadas = oleadas;
    }

    public void agregarEnemigosDelTurno(ArrayList<Enemigo> enemigosDelJuego) {
        if (!oleadas.isEmpty()){

            ArrayList<Enemigo> enemigosDelTurno = oleadas.pop();

            enemigosDelTurno.forEach(enemigo -> {
                enemigo.actualizarPosicionActual(pasarelaInicial);
                enemigosDelJuego.add(enemigo);
            });

        }
    }
    public void agregar(Coordenada coordenadaNueva, Parcela parcelaNueva) { mapa.put(coordenadaNueva, parcelaNueva);}

    public Parcela ver(Coordenada coordenada) {
        return (mapa.get(coordenada));
    }

}
