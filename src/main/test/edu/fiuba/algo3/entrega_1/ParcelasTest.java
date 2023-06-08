package edu.fiuba.algo3.entrega_1;

import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.miscelanea.*;
import edu.fiuba.algo3.modelo.parcelas.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
public class ParcelasTest {
    @Test
    public void test01SeCreanDosRocosasConCoordenadasIgualesYSonIguales(){

        Coordenada mockedCoordenada = mock(Coordenada.class);
        when(mockedCoordenada.equals(mockedCoordenada)).thenReturn(true);

        Rocosa rocosa1 = new Rocosa(mockedCoordenada);
        Rocosa rocosa2 = new Rocosa(mockedCoordenada);

        assertTrue(rocosa1.equals(rocosa2));
    }

    @Test
    public void test02SeCreaUnaTierraYUnaRocosaConCoordenadasIgualesYSonDiferentes(){

        Coordenada mockedCoordenada = mock(Coordenada.class);

        Rocosa rocosa = new Rocosa(mockedCoordenada);
        Tierra tierra = new Tierra(mockedCoordenada);

        assertFalse(rocosa.equals(tierra));
    }

    @Test
    public void test03SeCreanDosTierrasConCoordenadasIgualesYSonIguales(){

        Coordenada mockedCoordenada = mock(Coordenada.class);
        when(mockedCoordenada.equals(mockedCoordenada)).thenReturn(true);

        Tierra tierra1 = new Tierra(mockedCoordenada);
        Tierra tierra2 = new Tierra(mockedCoordenada);

        assertTrue(tierra1.equals(tierra2));
    }

    @Test
    public void test04SeCreanDosPasarelasConCoordenadasIgualesYSonIguales(){

        Coordenada mockedCoordenada = mock(Coordenada.class);
        when(mockedCoordenada.equals(mockedCoordenada)).thenReturn(true);
        Pasarela mockedPasarela = mock(PasarelaIntermedia.class);

        Pasarela pasarela1 = new PasarelaIntermedia(mockedCoordenada, mockedPasarela);
        Pasarela pasarela2= new PasarelaIntermedia(mockedCoordenada, mockedPasarela);

        assertTrue(pasarela1.equals(pasarela2));
    }

    @Test
    public void test05SeCreaUnaTierraYUnaPasarelaConCoordenadasIgualesYSonDiferentes(){
        Coordenada mockedCoordenada = mock(Coordenada.class);
        Pasarela mockedPasarela = mock(PasarelaIntermedia.class);

        Tierra tierra = new Tierra(mockedCoordenada);
        Pasarela pasarela = new PasarelaIntermedia(mockedCoordenada, mockedPasarela);

        assertFalse(tierra.equals(pasarela));
    }

    @Test
    public void test06SeCreaUnaRocosaYUnaPasarelaConCoordenadasIgualesYSonDiferentes(){
        Coordenada mockedCoordenada = mock(Coordenada.class);
        Pasarela mockedPasarela = mock(PasarelaIntermedia.class);

        Rocosa rocosa = new Rocosa(mockedCoordenada);
        PasarelaIntermedia pasarela = new PasarelaIntermedia(mockedCoordenada, mockedPasarela);

        assertFalse(rocosa.equals(pasarela));
    }
}
