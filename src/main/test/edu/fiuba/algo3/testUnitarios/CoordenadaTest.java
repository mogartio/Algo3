package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CoordenadaTest {
    @Test
    public void SeComparanDosCordenadasDiferentesYNoSonIguales(){
        Coordenada coord1 = new Coordenada(0,0);
        Coordenada coord2 = new Coordenada(1,0);

        assertFalse(coord1.equals(coord2));
    }

    @Test
    public void SeComparanDosCoordenadasIgualesYSonIguales(){
        Coordenada coord1 = new Coordenada(1,1);
        Coordenada coord2 = new Coordenada(1,1);

        assertTrue(coord1.equals(coord2));
    }

}
