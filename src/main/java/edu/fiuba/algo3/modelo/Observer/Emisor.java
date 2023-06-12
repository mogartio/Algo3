package edu.fiuba.algo3.modelo.Observer;

import java.util.ArrayList;
import java.util.List;

public class Emisor {

    private final List<Subscriptor> subscriptores;

    /*public Emisor(String... codigosOperacion) {
        for (String codigoOperacion : codigosOperacion) {
            this.subscriptores.put(codigoOperacion, new ArrayList<>());
        }
    }*/
    public Emisor() {
        subscriptores = new ArrayList<>();
    }

    public void subcribir(Subscriptor subscriptor) {
        if(!subscriptores.contains(subscriptor)){
            subscriptores.add(subscriptor);
        }
    }

    public void desSubscribir(Subscriptor subscriptor) {
        subscriptores.remove(subscriptor);
    }

    public void notificarSubscriptores(String tipoEvento, String mensaje) {
        subscriptores.forEach(subscriptor -> subscriptor.notificar(tipoEvento, mensaje));
    }
}
