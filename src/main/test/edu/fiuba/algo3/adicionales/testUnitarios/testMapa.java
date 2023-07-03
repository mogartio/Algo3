package edu.fiuba.algo3.adicionales.testUnitarios;

import edu.fiuba.algo3.modelo.juego.Mapa;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class testMapa {

    @Test
    public void test01SeGuardaUnaParcelaYalBuscarlaSeObtieneLaParcelaCorrecta(){
        Mapa map = new Mapa();
        Coordenada coord = mock(Coordenada.class);
        Parcela parcela = mock(Parcela.class);
        map.agregar(coord,parcela);

        assertTrue(map.ver(coord) == parcela);
    }
    @Test
    public void test02SeGuardan4PascelasYalBuscarCadaUnaSeObtieneLaParcelaCorrecta(){
        Mapa map = new Mapa();

        Coordenada coord1 = mock(Coordenada.class);
        Coordenada coord2 = mock(Coordenada.class);
        Coordenada coord3 = mock(Coordenada.class);
        Coordenada coord4 = mock(Coordenada.class);

        Parcela parcela1 = mock(Parcela.class);
        Parcela parcela2 = mock(Parcela.class);
        Parcela parcela3 = mock(Parcela.class);
        Parcela parcela4 = mock(Parcela.class);

        map.agregar(coord1,parcela1);
        map.agregar(coord2,parcela2);
        map.agregar(coord3,parcela3);
        map.agregar(coord4,parcela4);

        assertTrue(map.ver(coord1) == parcela1);
        assertTrue(map.ver(coord2) == parcela2);
        assertTrue(map.ver(coord3) == parcela3);
        assertTrue(map.ver(coord4) == parcela4);
    }

}
