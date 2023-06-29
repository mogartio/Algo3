package edu.fiuba.algo3.modelo.Interface;

import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.DisponibleDefensa;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;
import edu.fiuba.algo3.vista.VistaSprays;

public class ControladorCompra {
    private String defensaSeleccionada;

    private static final ControladorCompra INSTANCE = new ControladorCompra();

    private static VisualizadorDeMapa visualizadorMapa;

    private ControladorCompra() {
        super();
    }

    public void seleccionarDefensa(String unaDefensa) { this.defensaSeleccionada = unaDefensa; }

    public void setVisualizadorMapa(VisualizadorDeMapa visualizadorMapa) { this.visualizadorMapa = visualizadorMapa; }
    public void cancelarSeleccionDefensa() { this.defensaSeleccionada = null; }
    public static ControladorCompra getInstance() { return INSTANCE; }

    public void ponerDefensaEn(int coordX, int coordY) {
        Coordenada coord = new Coordenada(coordX, coordY);
        if (!esCompraValida(coord)) {
            return;
        }
        Juego.getInstance().comprarDefensa(defensaSeleccionada, coord);
        Juego.getInstance().notificar("Defensa");
        actualizarPanelTienda();
        cancelarSeleccionDefensa();
        }

    public void actualizarPanelTienda() {
        try {
            visualizadorMapa.actualizarPanelTienda();
        } catch (NullPointerException e) {

        }
    }
    private boolean esCompraValida(Coordenada coordenada){
        if (defensaSeleccionada == null) {
            return false;
        }
        Parcela parcela = Juego.getInstance().verParcelaEn(coordenada);
        if (!parcela.puedeConstruir(defensaSeleccionada)) {
            return false;
        }
        if (Juego.getInstance().esInicioOFinal(Juego.getInstance().verParcelaEn(coordenada))) {
            return false;
        }
        return true;
    }
}