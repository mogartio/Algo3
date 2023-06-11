package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Creador.CreadorDeMapa;
import edu.fiuba.algo3.modelo.Excepciones.NoHayCamino;
import edu.fiuba.algo3.modelo.lectorJSON.Mapa;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Normal;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreadorDeMapaTest {
    @Test
    public void test01SeLeeUnJsonYSeGeneraUnMapa() throws NoHayCamino {
        CreadorDeMapa cm = new CreadorDeMapa("ArchivosJson/mapa.json");

        Mapa m = cm.crearMapa();
    }
}
