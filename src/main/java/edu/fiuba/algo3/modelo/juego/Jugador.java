package edu.fiuba.algo3.modelo.juego;
import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.ObserverPropio.ObservablePropio;
import edu.fiuba.algo3.modelo.miscelanea.Tienda;
import edu.fiuba.algo3.modelo.miscelanea.Vida;

import java.util.ArrayList;

public class Jugador extends ObservablePropio {

    private static final Jugador INSTANCE = new Jugador();
    private Vida vida;
    private Credito creditos;
    private RachaDeHormigas rachaDeHormigas;
    private String nombre;
    private Tienda tienda;


    private Jugador() {
        super();
        vida = new Vida(20);
        creditos = new Credito(100);
        rachaDeHormigas = new RachaDeHormigas();
        tienda = new Tienda();
    }

    public static Jugador getInstance() { return INSTANCE; }

    public ArrayList<String> verificarConstruccionesPosibles() {
        return tienda.catalogoDisponible(creditos);
    }

    public void reestablecerEstadoInicial() {
        vida = new Vida(20);
        creditos = new Credito(100);
        RachaDeHormigas rachaDeHormigas = new RachaDeHormigas();
    }

    public void recompensar(int creditosRecibidos){
        emisor.notificarSubscriptores("log", "Recompensan al jugador con " + creditosRecibidos + " créditos");
        this.creditos.agregar(creditosRecibidos);
    }

    public void recibirDanio(int unDanio) {
        this.emisor.notificarSubscriptores("log", "Jugador recibe " + unDanio + " puntos de daño");
        vida.quitarVida(unDanio);
    }

    public boolean estaVivo(){
        return vida.sigueVivo();
    }

    public Defensa comprar(String tipoDefensa){
        return tienda.vendeme(tipoDefensa);
    }

    public void descontarCreditos(Credito creditos) { this.creditos.descontar(creditos); }

    public void agregarARachaDeHormigas(){
        rachaDeHormigas.agregarALaRacha();
    }

    public void setNombre(String nuevoNombre) {
        nombre = nuevoNombre;
    }

    public String getNombre() { return nombre; }

    public int getVida() { return vida.getPuntos(); }

    public int getCreditos() {return creditos.obtenerCreditos(); }
}
