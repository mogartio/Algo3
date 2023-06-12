package edu.fiuba.algo3.mocks;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.TorreBlanca;
import edu.fiuba.algo3.modelo.juego.Credito;
import edu.fiuba.algo3.modelo.miscelanea.Vendedor;

import java.util.ArrayList;

public class VendedorMock implements Vendedor {

    //Devuelve los créditos de la persona como String al pedir el catálogo
    public ArrayList<String> catalogoDisponible(Credito cantidadRecusos) {
        String creditosComoString = String.valueOf(cantidadRecusos.obtenerCreditos());
        ArrayList<String> arrayString = new ArrayList<>();
        arrayString.add(creditosComoString);
        return arrayString;
    }

    public Defensa vendeme(String unaDefensa) {
        return new TorreBlanca();
    }
}
