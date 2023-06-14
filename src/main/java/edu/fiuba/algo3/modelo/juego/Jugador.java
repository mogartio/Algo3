package edu.fiuba.algo3.modelo.juego;
import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Observer.Observable;
import edu.fiuba.algo3.modelo.miscelanea.Tienda;
import edu.fiuba.algo3.modelo.miscelanea.Vendedor;
import edu.fiuba.algo3.modelo.miscelanea.Vida;

import java.util.ArrayList;

public class Jugador extends Observable {

    private static final Jugador INSTANCE = new Jugador();

    private Vida vida;
    private Credito creditos;
    private int contadorHormigasMuertas;

    private Jugador() {
        super();

        vida = new Vida(20);
        creditos = new Credito(100);;
        contadorHormigasMuertas = 0;
    }
    public static Jugador getInstance() { return INSTANCE; }

    public ArrayList<String> verificarConstruccionesPosibles(Vendedor vendedor) {
        return vendedor.catalogoDisponible(creditos);
    }

    public void reestablecerEstadoInicial() {
        vida = new Vida(20);
        creditos = new Credito(100);;
        contadorHormigasMuertas = 0;
    }
    public void recompensar(int creditosRecibidos, boolean esHormiga){

        emisor.notificarSubscriptores("log", "Recompensan al jugador con " + creditosRecibidos + " créditos");

        int MULTIPLICADOR_HORMIGAS_MUERTAS = 2;
        int REQUISITO_HORMIGAS_MUERTAS = 10;

        if (esHormiga && contadorHormigasMuertas >= REQUISITO_HORMIGAS_MUERTAS)
            creditosRecibidos = creditosRecibidos * MULTIPLICADOR_HORMIGAS_MUERTAS;
        this.creditos.agregar(creditosRecibidos);
    }

    public void recibirDanio(int unDanio) {
        this.emisor.notificarSubscriptores("log", "Jugador recibe " + unDanio + " puntos de daño");
        vida.quitarVida(unDanio);
    }

    public boolean estaVivo(){
        return vida.sigueVivo();
    }

    public void registrarHormigaMuerta() {
        this.contadorHormigasMuertas ++;
    }

    public Defensa comprar(Tienda tienda, String tipoDefensa){
        return tienda.vendeme(tipoDefensa);
    }

    public void descontarCreditos(Credito creditos) { this.creditos.descontar(creditos); }


}
