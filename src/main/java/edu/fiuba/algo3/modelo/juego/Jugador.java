package edu.fiuba.algo3.modelo.juego;
import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Observer.Observable;
import edu.fiuba.algo3.modelo.miscelanea.Tienda;
import edu.fiuba.algo3.modelo.miscelanea.Vida;

import java.util.ArrayList;
import java.util.Stack;

public class Jugador extends Observable {

    private static final Jugador INSTANCE = new Jugador();
    private Jugador() {
        super();
        final int VIDA_INICIAL = 20;
        final Credito CREDITOS_INICIALES = new Credito(100);
        Vida vidaNueva = new Vida(VIDA_INICIAL);

        vida = vidaNueva;
        creditos = new Credito(100);;
        contadorHormigasMuertas = 0;
    }
    public static Jugador getInstance() { return INSTANCE; }

    private Vida vida;
    private Credito creditos;
    private int contadorHormigasMuertas;

    public ArrayList<String> verificarConstruccionesPosibles(Tienda proveedor) {
        return proveedor.catalogoDisponible(creditos);
    }
    public int obtenerVida() {
        return vida.obtenerPuntos();
    }

    public void recompensar(int creditosRecibidos, boolean esHormiga){

        emisor.notificarSubscriptores("log", "Recompensan al jugador con " + creditosRecibidos + " créditos");

        int MULTIPLICADOR_HORMIGAS_MUERTAS = 2;
        int REQUISITO_HORMIGAS_MUERTAS = 10;

        if (esHormiga && contadorHormigasMuertas >= REQUISITO_HORMIGAS_MUERTAS)
            creditosRecibidos = creditosRecibidos * MULTIPLICADOR_HORMIGAS_MUERTAS;
        this.creditos.agregar(creditosRecibidos);
    }

    public void recibirDaño(int unDaño) {
        emisor.notificarSubscriptores("log", "Jugador recibe " + unDaño + " puntos de daño");
        vida.quitarVida(unDaño);
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
