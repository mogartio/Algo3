package edu.fiuba.algo3.Interface;

import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Map;

public class AudioPlayer {

    private static MediaPlayer cancionFondo;

    private static MediaPlayer efectos;

    private static Map<String, String> diccionarioSonidos;

    static {
        diccionarioSonidos =  Map.of(
                "Morir", "ArchivosAudio/EnemigoDerrotado.mp3",
                "Avanzar", "ArchivosAudio/EnemigoAvanza.mp3",
                "Atacar", "ArchivosAudio/EnemigoGolpeado.mp3",
                "Victoria", "ArchivosAudio/Victoria.mp3",
                "Derrota", "ArchivosAudio/Derrota.mp3",
                "Construir" , "ArchivosAudio/DefensaConstruida.mp3",
                "Ganado", "ArchivosAudio/Victoria.mp3",
                "Perdido", "ArchivosAudio/Derrota.mp3",
                "construccion", "ArchivosAudio/construccion.mp3");
    }
    public static void playBGMusic() {
        String musicFile = "ArchivosAudio/BGMusic.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        cancionFondo = new MediaPlayer(sound);
        cancionFondo.play();
        cancionFondo.setCycleCount(10000);
    }

    public static void playEfectoSonido(String unEfecto) {
        try {
            String soundFile = diccionarioSonidos.get(unEfecto);
            Media sound = new Media(new File(soundFile).toURI().toString());
            efectos = new MediaPlayer(sound);
            efectos.play();
        } catch (Exception e){

        }
    }
}
