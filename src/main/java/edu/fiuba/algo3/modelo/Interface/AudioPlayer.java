package edu.fiuba.algo3.modelo.Interface;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Map;

public class AudioPlayer {

    private static MediaPlayer mediaPlayer;

    private static Map<String, String> diccionarioSonidos;

    static {
        diccionarioSonidos =  Map.of(
                "EnemigoMuerto", "ArchivosAudio/EnemigoDerrotado.mp3",
                "EnemigoAvanza", "ArchivosAudio/EnemigoAvanza.mp3",
                "EnemigoGolpeado", "ArchivosAudio/EnemigoGolpeado.mp3",
                "Victoria", "ArchivosAudio/Victoria.mp3",
                "Derrota", "ArchivosAudio/Derrota.mp3",
                "DefensaConstruida" , "ArchivosAudio/DefensaConstruida.mp3");
    }
    public static void playBGMusic() {
        String musicFile = "ArchivosAudio/BGMusic.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        //mediaPlayer.play();
        mediaPlayer.setCycleCount(10000);
    }

    public static void playEfectoSonido(String unEfecto) {
        String soundFile = diccionarioSonidos.get(unEfecto);
        Media sound = new Media(new File(soundFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
}
