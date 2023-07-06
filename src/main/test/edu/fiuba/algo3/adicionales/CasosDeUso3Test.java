package edu.fiuba.algo3.adicionales;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Creador.CreadorDeMapa;
import edu.fiuba.algo3.modelo.Creador.CreadorEnemigos;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.NoHayCamino;
import edu.fiuba.algo3.modelo.Excepciones.NoHayInicial;
import edu.fiuba.algo3.modelo.Interface.ControladorCompra;
import edu.fiuba.algo3.modelo.Interface.VisualizadorDeMapa;
import edu.fiuba.algo3.modelo.Turnero;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.Mapa;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.vista.VistaSprays;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observer;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CasosDeUso3Test extends App {
     //agregar verificacion de turno impar
    @Test
    public void test21aCuatroToposLlegandoAMetaEnTurnoImparMatanAJugador() throws NoHayCamino, NoHayInicial {
        VisualizadorDeMapa visualizadorDeMapa = new VisualizadorDeMapa();
        CreadorDeMapa creadorDeMapa = new CreadorDeMapa();
        Mapa mapa = creadorDeMapa.crearMapa("ArchivosJson/tests/Test21/mapaTest21",4);

        CreadorEnemigos creadorEnemigos = new CreadorEnemigos();
        Observer mockObserver = mock(Observer.class);
        LinkedList<ArrayList<Enemigo>> enemigos = creadorEnemigos.crearEnemigosDeNivel("ArchivosJson/tests/Test21/enemigosTest21", mockObserver);

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
        VisualizadorDeMapa visualizadorDeMapa = new VisualizadorDeMapa();
        CreadorDeMapa creadorDeMapa = new CreadorDeMapa();
        Mapa mapa = creadorDeMapa.crearMapa("ArchivosJson/tests/Test21/mapaTest21",4);

        CreadorEnemigos creadorEnemigos = new CreadorEnemigos();
        Observer mockObserver = mock(Observer.class);
        LinkedList<ArrayList<Enemigo>> enemigos = creadorEnemigos.crearEnemigosDeNivel("ArchivosJson/tests/Test21/enemigosTest21b", mockObserver);

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
        VisualizadorDeMapa visualizadorDeMapa = new VisualizadorDeMapa();
        CreadorDeMapa creadorDeMapa = new CreadorDeMapa();
        Mapa mapa = creadorDeMapa.crearMapa("ArchivosJson/tests/Test21/mapaTest21",4);

        CreadorEnemigos creadorEnemigos = new CreadorEnemigos();

        Observer mockObserver = mock(Observer.class);
        LinkedList<ArrayList<Enemigo>> enemigos = creadorEnemigos.crearEnemigosDeNivel("ArchivosJson/tests/Test21/enemigosTest21c", mockObserver);

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

        VisualizadorDeMapa visualizadorDeMapa = new VisualizadorDeMapa();
        CreadorDeMapa creadorDeMapa = new CreadorDeMapa();
        Mapa mapa = creadorDeMapa.crearMapa("ArchivosJson/tests/Test21/mapaTest21",4);
        VistaSprays vistaSprays = new VistaSprays(visualizadorDeMapa);

        CreadorEnemigos creadorEnemigos = new CreadorEnemigos();

        Observer mockObserver = mock(Observer.class);
        LinkedList<ArrayList<Enemigo>> enemigos = creadorEnemigos.crearEnemigosDeNivel("ArchivosJson/tests/Test22/enemigosTest22", mockObserver);

        Juego juego = Juego.getInstance();
        juego.reestablecerJuego();
        juego.setMapa(mapa);
        juego.cargarObserverParaDefensas(vistaSprays);
        juego.setOleadasDelNivel(enemigos);

        Turnero turnero = new Turnero();
        ControladorCompra mockControl = mock(ControladorCompra.class);

        mockControl.seleccionarDefensa("TorrePlateada");
        mockControl.ponerDefensaEn(1, 2);
        mockControl.seleccionarDefensa("TorrePlateada");
        mockControl.ponerDefensaEn(3, 3);
        mockControl.seleccionarDefensa("TorrePlateada");
        mockControl.ponerDefensaEn(2,1);


        for ( int i = 0 ; i <= 8 ; i++ ){
            turnero.jugarTurnoMaquina();
        }

        assertFalse(juego.jugadorVivo());
    }


    @Test
    public void test23LechuzaDestruyeLaPrimerTorreConstruidaAlLlegarALaMetaY20HormigasMatanAlJugador() throws NoHayCamino, NoHayInicial{
        // en el primer turno aparece la lechuza q destruira la primer torre
        // despues apareceran las 20 hormigas qur le quitaran la vida al jugador
        //(si la torre fue destruida correctamente entonces deberian llegar todas las hormigas)


        VisualizadorDeMapa visualizadorDeMapa = new VisualizadorDeMapa();
        CreadorDeMapa creadorDeMapa = new CreadorDeMapa();
        Mapa mapa = creadorDeMapa.crearMapa("ArchivosJson/tests/Test23/mapaTest23",6);
        VistaSprays vistaSprays = new VistaSprays(visualizadorDeMapa);

        CreadorEnemigos creadorEnemigos = new CreadorEnemigos();

        Observer mockObserver = mock(Observer.class);
        LinkedList<ArrayList<Enemigo>> enemigos = creadorEnemigos.crearEnemigosDeNivel("ArchivosJson/tests/Test23/enemigosTest23a", mockObserver);

        Juego juego = Juego.getInstance();
        juego.reestablecerJuego();
        juego.setMapa(mapa);
        juego.cargarObserverParaDefensas(vistaSprays);
        juego.setOleadasDelNivel(enemigos);

        Turnero turnero = new Turnero();
        juego.comprarDefensa("TorreBlanca", new Coordenada(1, 2));

        for (int i = 0; i <= 10; i++) {
            //es la cantidad de turnos necesarios para q las hormigas lleguen a la meta

            turnero.jugarTurnoMaquina();
        }
        assertFalse(juego.jugadorVivo());
    }


    @Test
    public void test24LechuzaDestruyeLaPrimerTorreConstruidaAlLlegarALaMetaY20HormigasMatanAlJugador() throws NoHayCamino, NoHayInicial {
        // al 5to turno la lechuza destruira la primer torre
        // despues apareceran las 20 hormigas qur le quitaran la vida al jugador
        //(si la primer torre fue destruida correctamente en el turno que se estima la llegada de la lechuza
        // a la meta dado el movimiento hipotenuza entonces deberian llegar todas las hormigas)

        VisualizadorDeMapa visualizadorDeMapa = new VisualizadorDeMapa();
        CreadorDeMapa creadorDeMapa = new CreadorDeMapa();
        Mapa mapa = creadorDeMapa.crearMapa("ArchivosJson/mapa.json",15);
        VistaSprays vistaSprays = new VistaSprays(visualizadorDeMapa);

        CreadorEnemigos creadorEnemigos = new CreadorEnemigos();

        Observer mockObserver = mock(Observer.class);
        LinkedList<ArrayList<Enemigo>> enemigos = creadorEnemigos.crearEnemigosDeNivel("ArchivosJson/tests/Test24/enemigosTest24", mockObserver);

        Juego juego = Juego.getInstance();
        juego.reestablecerJuego();
        juego.setMapa(mapa);
        juego.cargarObserverParaDefensas(vistaSprays);
        juego.setOleadasDelNivel(enemigos);

        Turnero turnero = new Turnero();
        ControladorCompra mockControl = mock(ControladorCompra.class);
        mockControl.seleccionarDefensa("TorrePlateada");
        mockControl.ponerDefensaEn(10, 12);
        mockControl.seleccionarDefensa("TorrePlateada");
        mockControl.ponerDefensaEn(6, 12);

        for (int i = 0; i <= 29; i++) {
            //es la cantidad de turnos necesarios para q las hormigas lleguen a la meta

            turnero.jugarTurnoMaquina();
        }

        assertFalse(juego.jugadorVivo());
    }

    @Test
    public void test25aLaTrampaDeArenaRetiene20Hormigas3TurnosProlongandoLaVidaDelJugadorEnDichaCantidad() throws NoHayCamino, NoHayInicial {
        VisualizadorDeMapa visualizadorDeMapa = new VisualizadorDeMapa();
        CreadorDeMapa creadorDeMapa = new CreadorDeMapa();
        Mapa mapa = creadorDeMapa.crearMapa("ArchivosJson/mapa.json",15);
        VistaSprays vistaSprays = new VistaSprays(visualizadorDeMapa);

        CreadorEnemigos creadorEnemigos = new CreadorEnemigos();

        Observer mockObserver = mock(Observer.class);
        LinkedList<ArrayList<Enemigo>> enemigos = creadorEnemigos.crearEnemigosDeNivel("ArchivosJson/tests/Test25/enemigosTest25a", mockObserver);

        Juego juego = Juego.getInstance();
        juego.reestablecerJuego();
        juego.setMapa(mapa);
        juego.cargarObserverParaDefensas(vistaSprays);
        juego.setOleadasDelNivel(enemigos);

        Turnero turnero = new Turnero();

        //Se tiene en cuenta que las hormigas, en este mapa, tardan 24 turnos en dañar al jugador

        // se deja pasar un turno para poder ubicar la trampa en la posicion de los enemigos
        turnero.jugarTurnoMaquina();

        juego.comprarDefensa("TrampaArenosa", new Coordenada(14,11 )); // se coloca la trampa en la posicion
        //continua a la salida

        for (int i = 0; i <= 24; i++) {
            turnero.jugarTurnoMaquina();
            assertTrue(juego.jugadorVivo());
        }

        turnero.jugarTurnoMaquina();
        assertFalse(juego.jugadorVivo());
    }

    @Test
    public void test25bLaTrampaDeArenaRetieneTopos3TurnosProlongandoLaVidaDelJugadorEnDichaCantidad() throws NoHayCamino, NoHayInicial {
        VisualizadorDeMapa visualizadorDeMapa = new VisualizadorDeMapa();
        CreadorDeMapa creadorDeMapa = new CreadorDeMapa();
        Mapa mapa = creadorDeMapa.crearMapa("ArchivosJson/mapa.json", 15);
        VistaSprays vistaSprays = new VistaSprays(visualizadorDeMapa);

        CreadorEnemigos creadorEnemigos = new CreadorEnemigos();

        Observer mockObserver = mock(Observer.class);
        LinkedList<ArrayList<Enemigo>> enemigos = creadorEnemigos.crearEnemigosDeNivel("ArchivosJson/tests/Test25/enemigosTest25b", mockObserver);

        Juego juego = Juego.getInstance();
        juego.reestablecerJuego();
        juego.setMapa(mapa);
        juego.cargarObserverParaDefensas(vistaSprays);
        juego.setOleadasDelNivel(enemigos);

        Turnero turnero = new Turnero();

        //Se tiene en cuenta que los topos, en este mapa, tardan 24 turnos en dañar al jugador

        // se deja pasar un turno para poder ubicar la trampa en la posicion de los enemigos
        turnero.jugarTurnoMaquina();

        juego.comprarDefensa("TrampaArenosa", new Coordenada(14, 11)); // se coloca la trampa en la posicion
        //continua a la salida

        for (int i = 0; i <= 24; i++) {
            turnero.jugarTurnoMaquina();
            assertTrue(juego.jugadorVivo());
        }

        turnero.jugarTurnoMaquina();
        assertFalse(juego.jugadorVivo());
    }
    @Test
    public void test25cLaTrampaDeArenaLePermiteSubrevivirUnTurnoMasAlJugador() throws NoHayCamino, NoHayInicial {
        VisualizadorDeMapa visualizadorDeMapa = new VisualizadorDeMapa();
        CreadorDeMapa creadorDeMapa = new CreadorDeMapa();
        Mapa mapa = creadorDeMapa.crearMapa("ArchivosJson/mapa.json", 15);
        VistaSprays vistaSprays = new VistaSprays(visualizadorDeMapa);

        CreadorEnemigos creadorEnemigos = new CreadorEnemigos();

        Observer mockObserver = mock(Observer.class);
        LinkedList<ArrayList<Enemigo>> enemigos = creadorEnemigos.crearEnemigosDeNivel("ArchivosJson/tests/Test25/enemigosTest25c", mockObserver);

        Juego juego = Juego.getInstance();
        juego.reestablecerJuego();
        juego.setMapa(mapa);
        juego.cargarObserverParaDefensas(vistaSprays);
        juego.setOleadasDelNivel(enemigos);

        Turnero turnero = new Turnero();

        //Se tiene en cuenta que las arañas, en este mapa, tardan 12 turnos en dañar al jugador


        turnero.jugarTurnoMaquina();

        juego.comprarDefensa("TrampaArenosa", new Coordenada(13, 11)); // se coloca la trampa
        //2 posiciones despues de la salida
        juego.comprarDefensa("TrampaArenosa", new Coordenada(12, 11)); // se coloca la trampa en la posicion

        for (int i = 0; i <= 11; i++) {
            turnero.jugarTurnoMaquina();
            assertTrue(juego.jugadorVivo());
        }

        turnero.jugarTurnoMaquina();
        assertFalse(juego.jugadorVivo());
    }
    @Test
    public void test26LaTrampaDeArenaNoAfectaALaLechuzaDestruyendoATiempoDefensasQueMatarianHormigas() throws NoHayCamino, NoHayInicial {
        VisualizadorDeMapa visualizadorDeMapa = new VisualizadorDeMapa();
        CreadorDeMapa creadorDeMapa = new CreadorDeMapa();
        Mapa mapa = creadorDeMapa.crearMapa("ArchivosJson/mapa.json", 15);
        VistaSprays vistaSprays = new VistaSprays(visualizadorDeMapa);

        CreadorEnemigos creadorEnemigos = new CreadorEnemigos();

        Observer mockObserver = mock(Observer.class);
        LinkedList<ArrayList<Enemigo>> enemigos = creadorEnemigos.crearEnemigosDeNivel("ArchivosJson/tests/Test26/enemigosTest26", mockObserver);

        Juego juego = Juego.getInstance();
        juego.reestablecerJuego();
        juego.setMapa(mapa);
        juego.cargarObserverParaDefensas(vistaSprays);
        juego.setOleadasDelNivel(enemigos);

        Turnero turnero = new Turnero();

        //Se tiene en cuenta que las lechuzas, en este mapa, tardan 5 turnos en destruir una torre
        //las hormigas seran cargadas en el mapa en el turno 6

        juego.comprarDefensa("TorreBlanca", new Coordenada(13, 10));
        juego.comprarDefensa("TorreBlanca", new Coordenada(12, 10));
        juego.comprarDefensa("TorreBlanca", new Coordenada(11, 10));

        juego.comprarDefensa("TrampaArenosa", new Coordenada(10, 11));
        juego.comprarDefensa("TrampaArenosa", new Coordenada(9, 11));




        for (int i = 0; i <= 28; i++) {
            turnero.jugarTurnoMaquina();
            assertTrue(juego.jugadorVivo());
        }

        turnero.jugarTurnoMaquina();
        assertFalse(juego.jugadorVivo());
    }

}
