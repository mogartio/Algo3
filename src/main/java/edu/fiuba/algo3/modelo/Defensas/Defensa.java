package edu.fiuba.algo3.modelo.Defensas;
import edu.fiuba.algo3.modelo.Observer.Observable;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;
import edu.fiuba.algo3.modelo.miscelanea.Rango;


public abstract class Defensa {
    protected int daño;
    protected EstadoConstruccion estadoDeConstruccion;
    protected Rango pasarelas;
    protected int TiempoDeConstruccion;

    public Defensa(){
        this.pasarelas = null;
        this.estadoDeConstruccion = new EnConstruccion(TiempoDeConstruccion);
        this.daño = 0;
    }
    public Defensa(Rango rango, int daño){
        this.pasarelas = rango;
        this.daño = daño;
    }
    public Defensa(EstadoConstruccion estadoConstruccion){
        this.estadoDeConstruccion = estadoConstruccion;
    }

    public void pasarTurno(){
        estadoDeConstruccion.pasoUnTurno();
    }
    public boolean estaConstruida(){
        return estadoDeConstruccion.estoyConstruida();
    }

    public void atacar(){
        Pasarela pasarelaSeleccionada = this.pasarelas.buscarPasarelaConEnemigo();
        pasarelaSeleccionada.dañarEnemigo(this.daño);
    }
}
