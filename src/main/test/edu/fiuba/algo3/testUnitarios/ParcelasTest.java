package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.TorreBlanca;
import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.miscelanea.*;
import edu.fiuba.algo3.modelo.parcelas.*;

import static org.junit.jupiter.api.Assertions.*;
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

        assertNotEquals(rocosa.equals(tierra), true);
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
        Pasarela mockedPasarela = mock(Pasarela.class);

        Pasarela pasarela1 = new Pasarela(mockedCoordenada, mockedPasarela, new Normal());
        Pasarela pasarela2= new Pasarela(mockedCoordenada, mockedPasarela, new Normal());

        assertTrue(pasarela1.equals(pasarela2));
    }

    @Test
    public void test05SeCreaUnaTierraYUnaPasarelaConCoordenadasIgualesYSonDiferentes(){
        Coordenada mockedCoordenada = mock(Coordenada.class);
        Pasarela mockedPasarela = mock(Pasarela.class);

        Tierra tierra = new Tierra(mockedCoordenada);
        Pasarela pasarela = new Pasarela(mockedCoordenada, mockedPasarela, new Normal());

        assertNotEquals(tierra.equals(pasarela), true);
    }

    @Test
    public void test06SeCreaUnaRocosaYUnaPasarelaConCoordenadasIgualesYSonDiferentes(){
        Coordenada mockedCoordenada = mock(Coordenada.class);
        Pasarela mockedPasarela = mock(Pasarela.class);

        Rocosa rocosa = new Rocosa(mockedCoordenada);
        Pasarela pasarela = new Pasarela(mockedCoordenada, mockedPasarela, new Normal());

        assertNotEquals(rocosa.equals(pasarela), true);
    }

    @Test
    public void test07SeInicializaUnaTierraYPorDefaultEstaDesocupada(){
        Coordenada mockedCoordenada = mock(Coordenada.class);
        Tierra tierra = new Tierra(mockedCoordenada);

        assertFalse(tierra.ocupada());
    }

    @Test
    public void test08SeConstruyeUnaDefensaEnUnaTierraYLuegoLaTierraEstaOcupada(){
        Coordenada mockedCoordenada = mock(Coordenada.class);
        Defensa mockedTorre = mock(TorreBlanca.class);

        Tierra tierra = new Tierra(mockedCoordenada);
        tierra.construirDefensa(mockedTorre);

        assertTrue(tierra.ocupada());
    }

    @Test
    public void test09SeInicializaUnaParcelaRocosaYPorDefaultEstaOcupada(){
        Coordenada mockedCoordenada = mock(Coordenada.class);
        Rocosa rocosa = new Rocosa(mockedCoordenada);

        assertTrue(rocosa.ocupada());
    }

    @Test
    public void test10SeInicializaUnaPasarelaNormalYPorDefaultEstaOcupadaParaConstruirDefensas(){
        Coordenada mockedCoordenada = mock(Coordenada.class);
        Pasarela pasarela = new Pasarela(mockedCoordenada, null, new Normal());

        assertTrue(pasarela.ocupada());
    }
}
