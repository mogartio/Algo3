package edu.fiuba.algo3.modelo.lectorJSON;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Observer.*;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Normal;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

public class Mapa extends Observable {
    Hashtable<Coordenada, Parcela> mapa;
    LinkedList< ArrayList<Enemigo> > oleadas;
    Pasarela pasarelaInicial;
    Pasarela pasarelaFinal;
    Logger logger;

    public Mapa() {
        this.mapa = new Hashtable<>();
        this.oleadas = new LinkedList<>();
        this.pasarelaInicial = new Pasarela(new Coordenada(0, 0), new Normal());
        //Para que el mapa quede en estado consistente, en un caso de uso real, la pasarelaInicial quedará determinada

        this.logger = new Logger();
    }

    public void setPasarelaInicialFinal(Pasarela pasarelaInicial, Pasarela pasarelaFinal) {
        this.pasarelaInicial = pasarelaInicial;
        this.pasarelaFinal = pasarelaFinal;
    }

    public void cargarOleadas(LinkedList< ArrayList<Enemigo> > oleadas) {
        this.oleadas = oleadas;
    }

    public void agregarEnemigosDelTurno(ArrayList<Enemigo> enemigosDelJuego) {
        if (!oleadas.isEmpty()){

            ArrayList<Enemigo> enemigosDelTurno = oleadas.pop();

            this.emisor.notificarSubscriptores("log", "Se carga una nueva oleada");

            enemigosDelTurno.forEach(enemigo -> {
                enemigo.agregarSubscriptor(logger);
                this.emisor.notificarSubscriptores("log", "Se agrega al mapa " + enemigo.representacionString());

                enemigo.establecerInicioYMeta(this.pasarelaInicial, this.pasarelaFinal);
                enemigosDelJuego.add(enemigo);
            });

        }
    }

    public void agregar(Coordenada coordenadaNueva, Parcela parcelaNueva) { mapa.put(coordenadaNueva, parcelaNueva);}

    public Parcela ver(Coordenada coordenada) {
        return (mapa.get(coordenada));
    }

}
