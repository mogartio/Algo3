package edu.fiuba.algo3.modelo.Creador;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.TorreBlanca;
import edu.fiuba.algo3.modelo.Defensas.TorrePlateada;

public class CreadorDeDefensas {
    public Defensa crear(String unaDefensa) {
        // la defensa deberia proveerla un CreadorDeDefensa
        Defensa defensa = null;

        if (unaDefensa == "TorreBlanca"){
            defensa = new TorreBlanca();
        }
        if (unaDefensa == "TorrePlateada"){
            defensa = new TorrePlateada();
        }
        return defensa;
    }
}
