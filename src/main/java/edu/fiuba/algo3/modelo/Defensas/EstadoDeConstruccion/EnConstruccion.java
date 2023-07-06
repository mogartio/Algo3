package edu.fiuba.algo3.modelo.Defensas.EstadoDeConstruccion;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.TipoDeDefensa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.vista.Sprayable;

import java.util.ArrayList;

public class EnConstruccion implements EstadoConstruccion{
    public String sonido = "enConstruccion";
    private final int turnosHastaTerminar;
    public EnConstruccion(int turnosHastaTerminar) { this.turnosHastaTerminar = turnosHastaTerminar; }

    public EstadoConstruccion pasoUnTurno(Defensa defensa){
        if (turnosHastaTerminar - 1 <= 0){
            return new Construido();
        }

        return new EnConstruccion(turnosHastaTerminar - 1);
    }
    public boolean estoyConstruida(){
        return false;
    }

    @Override
    public void atacar(TipoDeDefensa tipoDeDefensa, ArrayList<Enemigo> enemigos, Coordenada coordenada, int rangoAtaque, String nombre){}

    public String sprayID(Sprayable sprayable) {
        return "enConstruccion";
    }

    @Override
    public boolean destruir( Defensa defensa) {
        defensa.actualizarEstado(new Destruida());
        return true;
    }
}