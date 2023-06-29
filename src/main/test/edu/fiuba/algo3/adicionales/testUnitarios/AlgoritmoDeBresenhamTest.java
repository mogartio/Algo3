package edu.fiuba.algo3.adicionales.testUnitarios;

import edu.fiuba.algo3.modelo.miscelanea.AlgoritmoDeBresenham;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlgoritmoDeBresenhamTest {
    @Test
    public void test01SeCreaUnCaminoYSeVerificaQueUnaCoordenadaEsteEnElMismo(){
        LinkedList<Coordenada> camino = AlgoritmoDeBresenham.getCamino(new Coordenada(15,11), new Coordenada(2,1));

        boolean resultado = false;

        for (Coordenada coordenada : camino) {

            if (coordenada.equals(new Coordenada(14, 10))) {
                resultado = true;
            }

        }
        assertTrue(resultado);
    }

    @Test
    public void test02SeCreaUnCaminoYSeVerificaQueUnaCoordenadaNoEsteEnElMismo(){
        LinkedList<Coordenada> camino = AlgoritmoDeBresenham.getCamino(new Coordenada(15,11), new Coordenada(2,1));

        boolean resultado = false;

        for (Coordenada coordenada : camino) {
            if (coordenada.equals(new Coordenada(14, 9))) {
                resultado = true;
            }

        }
        assertFalse(resultado);
    }
}
