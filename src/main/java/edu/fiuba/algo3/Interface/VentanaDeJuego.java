package edu.fiuba.algo3.Interface;

import javafx.stage.Stage;

public class VentanaDeJuego {
    private static Stage VentanaDelJuego;

    public static Stage getInstance() {
        return VentanaDelJuego;
    }

    public static void setVentanaDelJuego(Stage stage) {
        VentanaDelJuego = stage;
    }
}
