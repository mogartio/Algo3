package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Normal;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;
import edu.fiuba.algo3.modelo.parcelas.Rocosa;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class PasarelaTest {

    @Test
    public void test01UnaPasarelaEsIgualASiMisma(){

        Coordenada coordenada = new Coordenada(0, 0);
        Pasarela pasarela = new Pasarela(coordenada, new Normal());

        assert(pasarela.esIgual(pasarela));
    }

    @Test
    public void test02UnaPasarelaEsIgualAOtraConLasMismasCoordenadas(){

        Coordenada coordenada = new Coordenada(0, 0);
        Pasarela pasarela = new Pasarela(coordenada, new Normal());
        Pasarela otraPasarela = new Pasarela(coordenada, new Normal());

        assert(pasarela.esIgual(otraPasarela));
    }

    @Test
    public void test03UnaPasarelaEsDistintaAOtraConDistintasCoordenadas(){

        Coordenada coordenada = new Coordenada(0, 0);
        Coordenada otraCoordenada = new Coordenada(1, 2);
        Pasarela pasarela = new Pasarela(coordenada, new Normal());
        Pasarela otraPasarela = new Pasarela(otraCoordenada, new Normal());

        assertFalse(pasarela.esIgual(otraPasarela));
    }

    @Test
    public void test04ObtenerSiguienteSinExcluirDevuelveLaPasarelaCorrecta(){

        Coordenada coordenadaBusqueda = new Coordenada(0, 0);
        Coordenada otraCoordenada = new Coordenada(2, 0);
        Coordenada otraCoordenada2 = new Coordenada(1, 1);
        Coordenada coordenadaSiguiente = new Coordenada(1, 0);

        Pasarela pasarelaBusqueda = new Pasarela(coordenadaBusqueda, new Normal());
        Pasarela otraPasarela = new Pasarela(otraCoordenada, new Normal());
        Pasarela otraPasarela2 = new Pasarela(otraCoordenada2, new Normal());
        Pasarela pasarelaSiguiente = new Pasarela(coordenadaSiguiente, new Normal());

        ArrayList<Pasarela> posiblesSiguientes = new ArrayList<>();
        posiblesSiguientes.add(otraPasarela);
        posiblesSiguientes.add(otraPasarela2);
        posiblesSiguientes.add(pasarelaSiguiente);

        Pasarela pasarelaObtenida = pasarelaBusqueda.obtenerSiguienteExcluyendo(posiblesSiguientes, null);

        assert(pasarelaObtenida.esIgual(pasarelaSiguiente));
    }

    @Test
    public void test05ObtenerSiguienteNoDependeDelOrdenDeCarga(){

        Coordenada coordenadaBusqueda = new Coordenada(0, 0);
        Coordenada otraCoordenada = new Coordenada(2, 0);
        Coordenada otraCoordenada2 = new Coordenada(1, 1);
        Coordenada coordenadaSiguiente = new Coordenada(1, 0);

        Pasarela pasarelaBusqueda = new Pasarela(coordenadaBusqueda, new Normal());
        Pasarela otraPasarela = new Pasarela(otraCoordenada, new Normal());
        Pasarela otraPasarela2 = new Pasarela(otraCoordenada2, new Normal());
        Pasarela pasarelaSiguiente = new Pasarela(coordenadaSiguiente, new Normal());

        ArrayList<Pasarela> posiblesSiguientes = new ArrayList<>();
        posiblesSiguientes.add(otraPasarela2);
        posiblesSiguientes.add(pasarelaSiguiente);
        posiblesSiguientes.add(otraPasarela);

        Pasarela pasarelaObtenida = pasarelaBusqueda.obtenerSiguienteExcluyendo(posiblesSiguientes, null);

        assert(pasarelaObtenida.esIgual(pasarelaSiguiente));
    }

    @Test
    public void test06ObtenerSiguienteExcluyendoDevuelveLaPasarelaCorrecta(){

        Coordenada coordenadaExcluida = new Coordenada(0, 0);
        Coordenada unaCoordenadaNoAledania = new Coordenada(3, 0);
        Coordenada otraCoordenadaNoAledania = new Coordenada(2, 1);
        Coordenada coordenadaSiguiente = new Coordenada(1, 1);
        Coordenada coordenadaBusqueda = new Coordenada(1, 0);

        Pasarela pasarelaExcluida = new Pasarela(coordenadaExcluida, new Normal());
        Pasarela unaPasarelaNoAledania = new Pasarela(unaCoordenadaNoAledania, new Normal());
        Pasarela otraPasarelaNoAledania = new Pasarela(otraCoordenadaNoAledania, new Normal());
        Pasarela pasarelaSiguiente = new Pasarela(coordenadaSiguiente, new Normal());
        Pasarela pasarelaBusqueda = new Pasarela(coordenadaBusqueda, new Normal());

        ArrayList<Pasarela> posiblesSiguientes = new ArrayList<>();
        posiblesSiguientes.add(pasarelaExcluida);
        posiblesSiguientes.add(unaPasarelaNoAledania);
        posiblesSiguientes.add(otraPasarelaNoAledania);
        posiblesSiguientes.add(pasarelaSiguiente);

        Pasarela pasarelaObtenida = pasarelaBusqueda.obtenerSiguienteExcluyendo(posiblesSiguientes, pasarelaExcluida);

        assert(pasarelaObtenida.esIgual(pasarelaSiguiente));
    }

    @Test
    public void test07ObtenerSiguienteExcluyendoNoDependeDelOrden(){

        Coordenada coordenadaExcluida = new Coordenada(0, 0);
        Coordenada unaCoordenadaNoAledania = new Coordenada(3, 0);
        Coordenada otraCoordenadaNoAledania = new Coordenada(2, 1);
        Coordenada coordenadaSiguiente = new Coordenada(1, 1);
        Coordenada coordenadaBusqueda = new Coordenada(1, 0);

        Pasarela pasarelaExcluida = new Pasarela(coordenadaExcluida, new Normal());
        Pasarela unaPasarelaNoAledania = new Pasarela(unaCoordenadaNoAledania, new Normal());
        Pasarela otraPasarelaNoAledania = new Pasarela(otraCoordenadaNoAledania, new Normal());
        Pasarela pasarelaSiguiente = new Pasarela(coordenadaSiguiente, new Normal());
        Pasarela pasarelaBusqueda = new Pasarela(coordenadaBusqueda, new Normal());

        ArrayList<Pasarela> posiblesSiguientes = new ArrayList<>();
        posiblesSiguientes.add(otraPasarelaNoAledania);
        posiblesSiguientes.add(pasarelaExcluida);
        posiblesSiguientes.add(pasarelaSiguiente);
        posiblesSiguientes.add(unaPasarelaNoAledania);

        Pasarela pasarelaObtenida = pasarelaBusqueda.obtenerSiguienteExcluyendo(posiblesSiguientes, pasarelaExcluida);

        assert(pasarelaObtenida.esIgual(pasarelaSiguiente));
    }

    @Test
    public void test08ObtenerSiguienteExcluyendoTeniendoVariasPosiblesDevuelveLaPrimeraHallada(){

        Coordenada coordenadaExcluida = new Coordenada(3, 2);
        Coordenada unaPosibleCoordenadaSiguiente = new Coordenada(2, 3);
        Coordenada otraPosibleCoordenadaSiguiente = new Coordenada(3, 4);
        Coordenada coordenadaNoAledania = new Coordenada(5, 5);
        Coordenada coordenadaBusqueda = new Coordenada(3, 3);

        Pasarela pasarelaExcluida = new Pasarela(coordenadaExcluida, new Normal());
        Pasarela primeraPosiblePasarelaAledania = new Pasarela(unaPosibleCoordenadaSiguiente, new Normal());
        Pasarela otraPosiblePasarelaAledania = new Pasarela(otraPosibleCoordenadaSiguiente, new Normal());
        Pasarela unaPasarelaNoAledania = new Pasarela(coordenadaNoAledania, new Normal());
        Pasarela pasarelaBusqueda = new Pasarela(coordenadaBusqueda, new Normal());

        ArrayList<Pasarela> posiblesSiguientes = new ArrayList<>();
        posiblesSiguientes.add(pasarelaExcluida);
        posiblesSiguientes.add(primeraPosiblePasarelaAledania);
        posiblesSiguientes.add(unaPasarelaNoAledania);
        posiblesSiguientes.add(otraPosiblePasarelaAledania);

        Pasarela pasarelaObtenida = pasarelaBusqueda.obtenerSiguienteExcluyendo(posiblesSiguientes, pasarelaExcluida);

        assert(pasarelaObtenida.esIgual(primeraPosiblePasarelaAledania));
    }

    @Test
    public void test09ObtenerSiguienteExcluyendoTeniendoVariasPosiblesDependeDelOrden(){

        Coordenada coordenadaExcluida = new Coordenada(3, 2);
        Coordenada unaPosibleCoordenadaSiguiente = new Coordenada(2, 3);
        Coordenada otraPosibleCoordenadaSiguiente = new Coordenada(3, 4);
        Coordenada coordenadaNoAledania = new Coordenada(5, 5);
        Coordenada coordenadaBusqueda = new Coordenada(3, 3);

        Pasarela pasarelaExcluida = new Pasarela(coordenadaExcluida, new Normal());
        Pasarela primeraPosiblePasarelaAledania = new Pasarela(unaPosibleCoordenadaSiguiente, new Normal());
        Pasarela otraPosiblePasarelaAledania = new Pasarela(otraPosibleCoordenadaSiguiente, new Normal());
        Pasarela unaPasarelaNoAledania = new Pasarela(coordenadaNoAledania, new Normal());
        Pasarela pasarelaBusqueda = new Pasarela(coordenadaBusqueda, new Normal());

        ArrayList<Pasarela> posiblesSiguientes = new ArrayList<>();
        posiblesSiguientes.add(pasarelaExcluida);
        posiblesSiguientes.add(unaPasarelaNoAledania);
        posiblesSiguientes.add(otraPosiblePasarelaAledania);
        posiblesSiguientes.add(primeraPosiblePasarelaAledania);

        Pasarela pasarelaObtenida = pasarelaBusqueda.obtenerSiguienteExcluyendo(posiblesSiguientes, pasarelaExcluida);

        assert(pasarelaObtenida.esIgual(otraPosiblePasarelaAledania));
    }
}
