package edu.fiuba.algo3.adicionales;

import edu.fiuba.algo3.modelo.Creador.CreadorDeJuego;
import edu.fiuba.algo3.modelo.Creador.CreadorDeMapa;
import edu.fiuba.algo3.modelo.Creador.CreadorEnemigos;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.NoHayCamino;
import edu.fiuba.algo3.modelo.Excepciones.NoHayInicial;
import edu.fiuba.algo3.modelo.Interface.VisualizadorDeMapa;
import edu.fiuba.algo3.modelo.Turnero;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.lectorJSON.Mapa;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CasosDeUso3Test {
     //agregar verificacion de turno impar
    @Test
    public void test21aCuatroToposLlegandoAMetaEnTurnoImparMatanAJugador() throws NoHayCamino, NoHayInicial {
        VisualizadorDeMapa visualizadorDeMapa = new VisualizadorDeMapa(4);
        CreadorDeMapa creadorDeMapa = new CreadorDeMapa("ArchivosJson/tests/Test21/mapaTest21",4, visualizadorDeMapa);
        Mapa mapa = creadorDeMapa.crearMapa();

        CreadorEnemigos creadorEnemigos = new CreadorEnemigos();
        LinkedList<ArrayList<Enemigo>> enemigos = creadorEnemigos.crearEnemigosDeNivel("ArchivosJson/tests/Test21/enemigosTest21");

        Juego juego = Juego.getInstance();
        juego.reestablecerJuego();
        juego.setMapa(mapa);
        juego.setOleadasDelNivel(enemigos);

        Turnero turnero = new Turnero();

        for ( int i = 0 ; i <= 7 ; i++ ){
            turnero.jugarTurnoMaquina();
        }

        assertFalse(juego.jugadorVivo());
    }
    @Test
    public void test21bNueveToposLlegandoAMetaEnTurnoParNoMatanAJugador() throws NoHayCamino, NoHayInicial {
        VisualizadorDeMapa visualizadorDeMapa = new VisualizadorDeMapa(15);
        CreadorDeMapa creadorDeMapa = new CreadorDeMapa("ArchivosJson/tests/Test21/mapaTest21",4, visualizadorDeMapa);
        Mapa mapa = creadorDeMapa.crearMapa();

        CreadorEnemigos creadorEnemigos = new CreadorEnemigos();
        LinkedList<ArrayList<Enemigo>> enemigos = creadorEnemigos.crearEnemigosDeNivel("ArchivosJson/tests/Test21/enemigosTest21b");

        Juego juego = Juego.getInstance();
        juego.reestablecerJuego();
        juego.setMapa(mapa);
        juego.setOleadasDelNivel(enemigos);

        Turnero turnero = new Turnero();


        for ( int i = 0 ; i <= 8 ; i++ ){
            turnero.jugarTurnoMaquina();
        }

        assertTrue(juego.jugadorVivo());
    }
    @Test
    public void test21cDiezToposLlegandoAMetaEnTurnoParMatanAJugador() throws NoHayCamino, NoHayInicial {
        VisualizadorDeMapa visualizadorDeMapa = new VisualizadorDeMapa(4);
        CreadorDeMapa creadorDeMapa = new CreadorDeMapa("ArchivosJson/tests/Test21/mapaTest21",4, visualizadorDeMapa);
        Mapa mapa = creadorDeMapa.crearMapa();

        CreadorEnemigos creadorEnemigos = new CreadorEnemigos();
        LinkedList<ArrayList<Enemigo>> enemigos = creadorEnemigos.crearEnemigosDeNivel("ArchivosJson/tests/Test21/enemigosTest21c");

        Juego juego = Juego.getInstance();
        juego.reestablecerJuego();
        juego.setMapa(mapa);
        juego.setOleadasDelNivel(enemigos);

        Turnero turnero = new Turnero();

        for ( int i = 0 ; i <= 8 ; i++ ){
            turnero.jugarTurnoMaquina();
        }

        assertFalse(juego.jugadorVivo());
    }

    @Test
    public void test22LosToposNoSonAtacados() throws NoHayCamino, NoHayInicial {
        //json de enemigos con 2 hormigas y 10 topos

        VisualizadorDeMapa visualizadorDeMapa = new VisualizadorDeMapa(4);
        CreadorDeMapa creadorDeMapa = new CreadorDeMapa("ArchivosJson/tests/Test21/mapaTest21",4, visualizadorDeMapa);
        Mapa mapa = creadorDeMapa.crearMapa();

        CreadorEnemigos creadorEnemigos = new CreadorEnemigos();
        LinkedList<ArrayList<Enemigo>> enemigos = creadorEnemigos.crearEnemigosDeNivel("ArchivosJson/tests/Test22/enemigosTest22");

        Juego juego = Juego.getInstance();
        juego.reestablecerJuego();
        juego.setMapa(mapa);
        juego.setOleadasDelNivel(enemigos);

        Turnero turnero = new Turnero();

        juego.comprarDefensa("TorrePlateada", new Coordenada(1,2));
        juego.comprarDefensa("TorrePlateada", new Coordenada(3,3));
        juego.comprarDefensa("TorrePlateada", new Coordenada(2,1));

        for ( int i = 0 ; i <= 8 ; i++ ){
            turnero.jugarTurnoMaquina();
        }

        assertFalse(juego.jugadorVivo());
    }


    @Test
    public void test23LechuzaDestruyeLaPrimerTorreConstruidaAlLlegarALaMetaY20HormigasMatanAlJugador() throws NoHayCamino, NoHayInicial {
        // en el primer turno aparece la lechuza q destruira la primer torre
        // despues apareceran las 20 hormigas qur le quitaran la vida al jugador
        //(si la torre fue destruida correctamente entonces deberian llegar todas las hormigas)

        VisualizadorDeMapa visualizadorDeMapa = new VisualizadorDeMapa(6);
        CreadorDeMapa creadorDeMapa = new CreadorDeMapa("ArchivosJson/tests/Test23/mapaTest23",6, visualizadorDeMapa);
        Mapa mapa = creadorDeMapa.crearMapa();

        CreadorEnemigos creadorEnemigos = new CreadorEnemigos();
        LinkedList<ArrayList<Enemigo>> enemigos = creadorEnemigos.crearEnemigosDeNivel("ArchivosJson/tests/Test23/enemigosTest23a");

        Juego juego = Juego.getInstance();
        juego.reestablecerJuego();
        juego.setMapa(mapa);
        juego.setOleadasDelNivel(enemigos);

        Turnero turnero = new Turnero();

        juego.comprarDefensa("TorreBlanca", new Coordenada(1, 2));

        for (int i = 0; i <= 10; i++) {
            //es la cantidad de turnos necesarios para q las hormigas lleguen a la meta

            turnero.jugarTurnoMaquina();
        }

        assertFalse(juego.jugadorVivo());
    }
}
