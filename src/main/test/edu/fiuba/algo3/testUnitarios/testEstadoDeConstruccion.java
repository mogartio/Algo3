package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Defensas.Construido;
import edu.fiuba.algo3.modelo.Defensas.EnConstruccion;
import edu.fiuba.algo3.modelo.Defensas.EstadoConstruccion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class testEstadoDeConstruccion {
    @Test
    public void test01EnConstruccionDevuelveFalseAlEstoyConstruidaSiSeCreaConUnTurno(){
        EstadoConstruccion estadoBajoPrueba = new EnConstruccion(1);

        assertFalse(estadoBajoPrueba.estoyConstruida());
    }
    @Test
    public void test02ConstruidoDevuelveInstanciaDeConstruidoDespuesDePasarUnTurno(){
        EstadoConstruccion estadoBajoPrueba = new Construido();

        estadoBajoPrueba = estadoBajoPrueba.pasoUnTurno();

        assertTrue(estadoBajoPrueba instanceof Construido);
    }
    @Test
    public void test03EnConstruccionDevuelveInstanciaDeConstruidoDespuesDePasarLosTurnosCorrespondientes(){
        EstadoConstruccion estadoBajoPrueba = new EnConstruccion(10);

        for(int i = 0 ; i < 10 ; i++){
            estadoBajoPrueba = estadoBajoPrueba.pasoUnTurno();
        }

        assertTrue(estadoBajoPrueba instanceof Construido);
    }
}
