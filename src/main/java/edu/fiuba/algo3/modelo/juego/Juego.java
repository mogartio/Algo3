package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.vista.Loggeable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observer;

public class Juego extends Loggeable {
    private static final Juego INSTANCE = new Juego();
    Jugador jugador;
    public EstadoJuego estadoJuego;
    Mapa mapa;
    ArrayList<Observer> observersParaDefensas;

    private Juego() {
        super();
        this.jugador = new Jugador();
        this.mapa = new Mapa();
        estadoJuego = new Jugando();
        this.observersParaDefensas = new ArrayList<>();
    }

    public static Juego getInstance() {
        return INSTANCE;
    }

    public void notificar(String tipo) {
        notifyObservers(tipo);
        jugador.notifyObservers();
        this.estadoJuego.notificar();
    }

    public void cargarObserverParaDefensas(Observer observer){
        this.observersParaDefensas.add(observer);
    }

    public void cargarObserverParaJugador(Observer observer){
        jugador.addObserver(observer);
    }
    public void daniarAlJugador(int unNumero){
        jugador.recibirDanio(unNumero);
    }

    public void comprarDefensa(String unaDefensa, Coordenada coordenada) {
        Defensa nuevaDefensa = jugador.comprar(unaDefensa);
        nuevaDefensa.asignarPosicion(coordenada);
        if (observersParaDefensas.size() != 0) {
            for(Observer observer : observersParaDefensas){
            nuevaDefensa.addObserver(observer);
            }
        }
        estadoJuego.introducirDefensa(nuevaDefensa);
        this.aniadirEvento("Se posiciona una defensa " + unaDefensa + " en la posicion: " + coordenada.representacionString());
        mapa.ver(coordenada).construirDefensa();
    }

    public boolean esInicioOFinal(Parcela pasarela) {return mapa.esInicioOFinal(pasarela); }

    public void nuevoEnemigo(Enemigo nuevoEnemigo) {
        estadoJuego = this.estadoJuego.introducirEnemigo(nuevoEnemigo);
        this.aniadirEvento("Se introduce un nuevo enemigo " + nuevoEnemigo);
    }

    public boolean finalizado() {
        return this.estadoJuego.finalizado();
    }

    public void jugarTurno(int numeroTurno) {
        this.estadoJuego = estadoJuego.jugarTurno(jugador.estaVivo(), numeroTurno);
        this.aniadirEvento("Se juega el turno n " + numeroTurno);
    }

    public void recompensarJugador(int unNumero) {
        jugador.recompensar(unNumero);
    }

    public void descontarCreditosAlJugador(Credito cantidadACobrar) {
        jugador.descontarCreditos(cantidadACobrar);
    }

    public ArrayList<String> verificarConstruccionesPosibles() {
        return jugador.verificarConstruccionesPosibles();
    }

    public void setNombreDelJugador(String unString) {
        jugador.setNombre(unString);
    }

    public void agregarARachaDeHormigas() {
        jugador.agregarARachaDeHormigas();
    }

    public boolean jugadorVivo() {
        return jugador.estaVivo();
    }

    public void comprar(String nombreDeLaDefensa) {
        jugador.comprar(nombreDeLaDefensa);
    }

    public void reestablecerJuego(){
        jugador = new Jugador();
        estadoJuego = new Jugando();
    }

    public void descontarCreditos(Credito creditoADescontar) {
        jugador.descontarCreditos(creditoADescontar);
    }

    public void setMapa(Mapa mapa){
        this.mapa = mapa;
        estadoJuego = new Jugando(mapa);
    }

    public void setOleadasDelNivel(LinkedList<ArrayList<Enemigo>> enemigos){
        mapa.cargarOleadas(enemigos);
    }

    public void destruirDefensaMasAntigua() {
        estadoJuego.destruirDefensaMasAntigua();
    }

    public Parcela verParcelaEn(Coordenada coordenada) { return mapa.ver(coordenada); }

    public void destruirTrampa(Defensa unaTrampa){
        estadoJuego.quitarTrampa(unaTrampa);
        this.aniadirEvento("Se destruye la trampa " + unaTrampa.representacionString());
    }
}