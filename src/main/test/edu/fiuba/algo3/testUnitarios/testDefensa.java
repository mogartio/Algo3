package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.TorrePlateada;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

public class testDefensa {

    @Test
    public void test01DefensaDaniaAEnemigoEnRangoUnaUnicaVez(){
        Coordenada coordenadaMockeada = mock(Coordenada.class);
        Defensa defensa = new TorrePlateada(coordenadaMockeada);
        ArrayList<Enemigo> listaDeEnemigos = new ArrayList<Enemigo>();

        Enemigo enemigoMockeado = mock(Enemigo.class);

        listaDeEnemigos.add(enemigoMockeado);

        when(enemigoMockeado.estaEnRango(coordenadaMockeada,5)).thenReturn(true);
        defensa.atacar(listaDeEnemigos);

        verify(enemigoMockeado , times(1)).recibirDanio(2);
        }

    @Test
    public void test02DefensaDaniaAUnSoloEnemigoDeCuatroEnRango(){
        Coordenada coordenadaMockeada = mock(Coordenada.class);
        Defensa defensa = new TorrePlateada(coordenadaMockeada);
        ArrayList<Enemigo> listaDeEnemigos = new ArrayList<Enemigo>();

        Enemigo enemigoMockeado1 = mock(Enemigo.class);
        Enemigo enemigoMockeado2 = mock(Enemigo.class);
        Enemigo enemigoMockeado3 = mock(Enemigo.class);
        Enemigo enemigoMockeado4 = mock(Enemigo.class);

        when(enemigoMockeado1.estaEnRango(coordenadaMockeada,5)).thenReturn(true);
        when(enemigoMockeado2.estaEnRango(coordenadaMockeada,5)).thenReturn(true);
        when(enemigoMockeado3.estaEnRango(coordenadaMockeada,5)).thenReturn(true);
        when(enemigoMockeado4.estaEnRango(coordenadaMockeada,5)).thenReturn(true);

        listaDeEnemigos.add(enemigoMockeado1);
        listaDeEnemigos.add(enemigoMockeado2);
        listaDeEnemigos.add(enemigoMockeado3);
        listaDeEnemigos.add(enemigoMockeado4);

        defensa.atacar(listaDeEnemigos);

        verify(enemigoMockeado1 , times(1)).recibirDanio(2);
        verify(enemigoMockeado2 , times(0)).recibirDanio(2);
        verify(enemigoMockeado3 , times(0)).recibirDanio(2);
        verify(enemigoMockeado4 , times(0)).recibirDanio(2);
    }

    @Test
    public void test03DefensaNoDaniaANingunEnemigoDeCuatroFueraDeRango(){
        Coordenada coordenadaMockeada = mock(Coordenada.class);
        Defensa defensa = new TorrePlateada(coordenadaMockeada);
        ArrayList<Enemigo> listaDeEnemigos = new ArrayList<Enemigo>();

        Enemigo enemigoMockeado1 = mock(Enemigo.class);
        Enemigo enemigoMockeado2 = mock(Enemigo.class);
        Enemigo enemigoMockeado3 = mock(Enemigo.class);

        when(enemigoMockeado1.estaEnRango(coordenadaMockeada,5)).thenReturn(false);
        when(enemigoMockeado2.estaEnRango(coordenadaMockeada,5)).thenReturn(false);
        when(enemigoMockeado3.estaEnRango(coordenadaMockeada,5)).thenReturn(false);

        listaDeEnemigos.add(enemigoMockeado1);
        listaDeEnemigos.add(enemigoMockeado2);
        listaDeEnemigos.add(enemigoMockeado3);

        defensa.atacar(listaDeEnemigos);

        verify(enemigoMockeado1 , times(0)).recibirDanio(2);
        verify(enemigoMockeado2 , times(0)).recibirDanio(2);
        verify(enemigoMockeado3 , times(0)).recibirDanio(2);
    }

}
