package edu.fiuba.algo3.modelo.miscelanea;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.juego.Credito;
import edu.fiuba.algo3.modelo.juego.Jugador;

import java.util.ArrayList;

public interface Vendedor {

    ArrayList<String> catalogoDisponible(Credito cantidadRecusos);

    Defensa vendeme(String unaDefensa);
}
