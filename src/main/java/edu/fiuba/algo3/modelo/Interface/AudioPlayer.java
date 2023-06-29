package edu.fiuba.algo3.modelo.Interface;

import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Map;

public class AudioPlayer {

    private static MediaPlayer mediaPlayer;

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
                "Perdido", "ArchivosAudio/Derrota.mp3");
    }
    public static void playBGMusic() {
        String musicFile = "ArchivosAudio/BGMusic.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        //mediaPlayer.play();
        mediaPlayer.setCycleCount(10000);
    }

    public static void playEfectoSonido(String unEfecto) {
        try {
            String soundFile = diccionarioSonidos.get(unEfecto);
            Media sound = new Media(new File(soundFile).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        } catch (NullPointerException e){

        }
    }
}
