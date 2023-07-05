package edu.fiuba.algo3.modelo.Defensas.EstadoDeConstruccion;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.TipoDeDefensa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;

import java.util.ArrayList;

public class EnConstruccion implements EstadoConstruccion{
    private int turnosHastaTerminar;
    public String sonido = null;
    public EnConstruccion(int turnosHastaTerminar) { this.turnosHastaTerminar = turnosHastaTerminar; }

    public EstadoConstruccion pasoUnTurno(Defensa defensa){
        if (turnosHastaTerminar - 1 <= 0){
            //emisor.notificarSubscriptores("log", "La defensa " + defensa.representationString() + "se termino de construir");
            return new Construido();
        }

        return new EnConstruccion(turnosHastaTerminar - 1);
    }
    public boolean estoyConstruida(){
        return false;
    }

    @Override
    public void setSonido() {}

    @Override
    public String verSonido() {
        return null;
    }

    @Override
    public void atacar(TipoDeDefensa tipoDeDefensa, ArrayList<Enemigo> enemigos, Coordenada coordenada, int rangoAtaque, String nombre){}
}