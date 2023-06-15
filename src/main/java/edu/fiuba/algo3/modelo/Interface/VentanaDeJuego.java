package edu.fiuba.algo3.modelo.Interface;

import javafx.stage.Stage;

public class VentanaDeJuego {
    private static Stage VentanaDelJuego;

    public static Stage getIntance() {
        return VentanaDelJuego;
    }

    public static void setVentanaDelJuego(Stage stage) {
        VentanaDelJuego = stage;
    }
}
