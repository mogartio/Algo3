package edu.fiuba.algo3.adicionales.testUnitarios;

import edu.fiuba.algo3.modelo.Excepciones.NoHayCamino;
import edu.fiuba.algo3.modelo.Excepciones.NoHayInicial;
import edu.fiuba.algo3.modelo.lectorJSON.Camino;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Normal;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class CaminoTest {
    @Test
    public void test01CrearUnCaminoSinAgregarAlgunaPasarelaYPedirElInicialLanzaUnaExcepcionNoHayCamino(){
        Camino caminoVacio = new Camino(10);

        try {
            Pasarela aux = caminoVacio.inicial();
            fail();
        } catch (NoHayCamino | NoHayInicial excepcion) {
            assertTrue(excepcion instanceof NoHayCamino);
        }
    }

    //Este test está a modo de verificación que el objeto camino se crea en un estado que no causa errores, no es un caso de uso
    @Test
    public void test02CrearUnCaminoConUnaPasarelaNoBordeYPedirLaInicialLanzaExcepcionNoHayInicial(){
        Camino camino = new Camino(10);
        Coordenada coordenada = new Coordenada(3, 1);

        camino.agregar(coordenada);

        try {
            camino.inicial();
            fail();
        } catch (NoHayCamino | NoHayInicial excepcion) {
            assertTrue(excepcion instanceof NoHayInicial);
        }
    }

    @Test
    public void test03CrearUnCaminoConUnaPasarelaBordeYPedirLaInicialDevuelveLaPasarela(){
        Camino camino = new Camino(10);
        Coordenada coordenadaBorde = new Coordenada(3, 0);
        Pasarela pasarelaBorde = new Pasarela(coordenadaBorde, new Normal());

        camino.agregar(coordenadaBorde);

        try {
            Pasarela inicialObtenida = camino.inicial();
            assert(inicialObtenida.esIgual(pasarelaBorde));
        } catch (NoHayCamino | NoHayInicial excepcion) {
            fail();
        }
    }

    @Test
    public void test04CrearUnCaminoConMuchasPasarelasYArmarloDevuelveLaPasarelasConectadasCorrectamente(){
        Camino camino = new Camino(10);
        Coordenada coordenadaPrimera = new Coordenada(1, 0);
        Coordenada coordenadaSegunda = new Coordenada(1, 1);
        Coordenada coordenadaTercera = new Coordenada(2, 1);
        Coordenada coordenadaCuarta = new Coordenada(3, 1);
        Coordenada coordenadaQuinta = new Coordenada(4, 1);

        Pasarela pasarelaPrimera = new Pasarela(coordenadaPrimera, new Normal());
        Pasarela pasarelaSegunda = new Pasarela(coordenadaSegunda, new Normal());
        Pasarela pasarelaTercera = new Pasarela(coordenadaTercera, new Normal());
        Pasarela pasarelaCuarta = new Pasarela(coordenadaCuarta, new Normal());
        Pasarela pasarelaQuinta = new Pasarela(coordenadaQuinta, new Normal());


        camino.agregar(coordenadaPrimera);
        camino.agregar(coordenadaSegunda);
        camino.agregar(coordenadaTercera);
        camino.agregar(coordenadaCuarta);
        camino.agregar(coordenadaQuinta);

        camino.armar();

        Pasarela inicialObtenida = null;

        try {
            inicialObtenida = camino.inicial();
            assert(inicialObtenida.esIgual(pasarelaPrimera));
        } catch (NoHayCamino | NoHayInicial excepcion) {
            fail();
        }

        Pasarela siguiente = inicialObtenida.verSiguiente();
        assert(siguiente.esIgual(pasarelaSegunda));
        siguiente = siguiente.verSiguiente();
        assert(siguiente.esIgual(pasarelaTercera));
        siguiente = siguiente.verSiguiente();
        assert(siguiente.esIgual(pasarelaCuarta));
        siguiente = siguiente.verSiguiente();
        assert(siguiente.esIgual(pasarelaQuinta));
    }

    //SUPUESTO: Un camino sólo puede tener entradas y salidas en los bordes, el resto del camino no puede serlo
    @Test
    public void test05CrearUnCaminoConMuchasPasarelasYArmarloDevuelveLaPasarelasConectadasCorrectamenteSinImportarElOrdenDeCarga(){
        Camino camino = new Camino(10);
        Coordenada coordenadaPrimera = new Coordenada(1, 0);
        Coordenada coordenadaSegunda = new Coordenada(1, 1);
        Coordenada coordenadaTercera = new Coordenada(2, 1);
        Coordenada coordenadaCuarta = new Coordenada(3, 1);
        Coordenada coordenadaQuinta = new Coordenada(4, 1);

        Pasarela pasarelaPrimera = new Pasarela(coordenadaPrimera, new Normal());
        Pasarela pasarelaSegunda = new Pasarela(coordenadaSegunda, new Normal());
        Pasarela pasarelaTercera = new Pasarela(coordenadaTercera, new Normal());
        Pasarela pasarelaCuarta = new Pasarela(coordenadaCuarta, new Normal());
        Pasarela pasarelaQuinta = new Pasarela(coordenadaQuinta, new Normal());

        //Las agrego al camino en cualquier orden
        camino.agregar(coordenadaCuarta);
        camino.agregar(coordenadaTercera);
        camino.agregar(coordenadaPrimera);
        camino.agregar(coordenadaQuinta);
        camino.agregar(coordenadaSegunda);

        camino.armar();

        Pasarela inicialObtenida = null;

        try {
            inicialObtenida = camino.inicial();
            assert(inicialObtenida.esIgual(pasarelaPrimera));
        } catch (NoHayCamino | NoHayInicial excepcion) {
            fail();
        }

        Pasarela siguiente = inicialObtenida.verSiguiente();
        assert(siguiente.esIgual(pasarelaSegunda));
        siguiente = siguiente.verSiguiente();
        assert(siguiente.esIgual(pasarelaTercera));
        siguiente = siguiente.verSiguiente();
        assert(siguiente.esIgual(pasarelaCuarta));
        siguiente = siguiente.verSiguiente();
        assert(siguiente.esIgual(pasarelaQuinta));
    }
}
