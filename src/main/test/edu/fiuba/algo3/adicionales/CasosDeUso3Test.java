package edu.fiuba.algo3.adicionales;

import edu.fiuba.algo3.modelo.Creador.CreadorDeJuego;
import edu.fiuba.algo3.modelo.Defensas.*;
import edu.fiuba.algo3.modelo.Enemigos.Arania;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Hormiga;
import edu.fiuba.algo3.modelo.Excepciones.NoHayCamino;
import edu.fiuba.algo3.modelo.Excepciones.NoHayInicial;
import edu.fiuba.algo3.modelo.Excepciones.PasarelaInexistente;
import edu.fiuba.algo3.modelo.Observer.Logger;
import edu.fiuba.algo3.modelo.Turnero;
import edu.fiuba.algo3.modelo.juego.Credito;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.lectorJSON.Mapa;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.miscelanea.RandomGenerator;
import edu.fiuba.algo3.modelo.miscelanea.Tienda;
import edu.fiuba.algo3.modelo.parcelas.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CasosDeUso3Test {
    @Test
    public void test21aCuatroToposLlegandoAMetaEnTurnoImparMatanAJugador() throws NoHayCamino, NoHayInicial {
        Juego juego = CreadorDeJuego.crearJuego("ArchivosJson/tests/Test21/enemigosTest21", "ArchivosJson/tests/Test21/mapaTest21",4);

        Turnero turnero = new Turnero(juego);

        Jugador jugador = Jugador.getInstance();
        jugador.reestablecerEstadoInicial();

        for ( int i = 0 ; i <= 7 ; i++ ){
            turnero.jugarTurnoMaquina();
        }

        assertFalse(jugador.estaVivo());
    }
    @Test
    public void test21bNueveToposLlegandoAMetaEnTurnoParNoMatanAJugador() throws NoHayCamino, NoHayInicial {
        Juego juego = CreadorDeJuego.crearJuego("ArchivosJson/tests/Test21/enemigosTest21b", "ArchivosJson/tests/Test21/mapaTest21",4);

        Turnero turnero = new Turnero(juego);

        Jugador jugador = Jugador.getInstance();
        jugador.reestablecerEstadoInicial();

        for ( int i = 0 ; i <= 8 ; i++ ){
            turnero.jugarTurnoMaquina();
        }

        assertTrue(jugador.estaVivo());
    }
    @Test
    public void test21cDiezToposLlegandoAMetaEnTurnoParMatanAJugador() throws NoHayCamino, NoHayInicial {
        Juego juego = CreadorDeJuego.crearJuego("ArchivosJson/tests/Test21/enemigosTest21c", "ArchivosJson/tests/Test21/mapaTest21",4);

        Turnero turnero = new Turnero(juego);

        Jugador jugador = Jugador.getInstance();
        jugador.reestablecerEstadoInicial();

        for ( int i = 0 ; i <= 8 ; i++ ){
            turnero.jugarTurnoMaquina();
        }

        assertFalse(jugador.estaVivo());
    }

    @Test
    public void test22LosToposNoSonAtacados() throws NoHayCamino, NoHayInicial {
        //json de enemigos con 2 hormigas y 10 topos
        Juego juego = CreadorDeJuego.crearJuego("ArchivosJson/tests/Test22/enemigosTest22", "ArchivosJson/tests/Test21/mapaTest21",4);

        Turnero turnero = new Turnero(juego);

        Jugador jugador = Jugador.getInstance();
        jugador.reestablecerEstadoInicial();

        juego.comprarDefensa("TorrePlateada", new Coordenada(1,2));
        juego.comprarDefensa("TorrePlateada", new Coordenada(3,3));
        juego.comprarDefensa("TorrePlateada", new Coordenada(2,1));

        for ( int i = 0 ; i <= 8 ; i++ ){
            turnero.jugarTurnoMaquina();
        }

        assertFalse(jugador.estaVivo());
    }

    /*
    @Test
    public void test23LechuzaDestruyeLaPrimerTorreConstruidaAlLlegarALaMetaY20HormigasMatanAlJugador() throws NoHayCamino, NoHayInicial {
        // en el primer turno aparece la lechuza q destruira la primer torre
        // despues apareceran las 20 hormigas qur le quitaran la vida al jugador
        //(si la torre fue destruida correctamente entonces deberian llegar todas las hormigas)

        Juego juego = CreadorDeJuego.crearJuego("ArchivosJson/tests/Test22/enemigosTest22", "ArchivosJson/tests/Test21/mapaTest21",4);

        juego.comprarDefensa("TorrePlateada", new Coordenada(1,2));

        Turnero turnero = new Turnero(juego);

        Jugador jugador = Jugador.getInstance();
        jugador.reestablecerEstadoInicial();

        for ( int i = 0 ; i <= 13 ; i++ ){
        //es la cantidad de turnos necesarios para q las hormigas lleguen a la meta

            turnero.jugarTurnoMaquina();
        }

        assertFalse(jugador.estaVivo());
    }*/
}
