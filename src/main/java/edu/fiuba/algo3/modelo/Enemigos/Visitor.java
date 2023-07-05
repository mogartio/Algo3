package edu.fiuba.algo3.modelo.Enemigos;

import edu.fiuba.algo3.modelo.Defensas.Ataque;
import edu.fiuba.algo3.modelo.Defensas.Trampa;

public interface Visitor {
    boolean esVisiblePara(Trampa trampa);
    boolean esVisiblePara(Ataque ataque);
}
