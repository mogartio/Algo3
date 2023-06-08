package edu.fiuba.algo3.modelo.miscelanea;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.TorreBlanca;
import edu.fiuba.algo3.modelo.Defensas.TorrePlateada;

import java.util.Stack;

public class Tienda {

    public Stack<Defensa> catalogoDisponible(int cantidadRecusos){
        Stack<Defensa> defensasDisponibles = new Stack<Defensa>();

        if (cantidadRecusos >= 10){
            defensasDisponibles.push(new TorreBlanca());
        }
        if (cantidadRecusos >= 20){
            defensasDisponibles.push(new TorrePlateada());
        }
        return defensasDisponibles;
    }
}
