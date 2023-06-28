package edu.fiuba.algo3.modelo.Interface;


import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

public class EventHandlerDefensas implements EventHandler<MouseEvent> {

    private final String defensa;

    public EventHandlerDefensas(String unaDefensa) {
        this.defensa = unaDefensa;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        ToggleButton toggleButton = new ToggleButton();
        toggleButton.setSelected(true);
    }
}
