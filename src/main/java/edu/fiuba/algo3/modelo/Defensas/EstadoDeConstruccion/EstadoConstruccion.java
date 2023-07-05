package edu.fiuba.algo3.modelo.Defensas.EstadoDeConstruccion;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.TipoDeDefensa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;

import java.util.ArrayList;

public interface EstadoConstruccion {
    EstadoConstruccion pasoUnTurno(Defensa defensa);
    boolean estoyConstruida();
    void setSonido();
    String versonido();
    void atacar(TipoDeDefensa tipoDeDefensa, ArrayList<Enemigo> enemigos, Coordenada coordenada, int rangoAtaque, String nombre);
}