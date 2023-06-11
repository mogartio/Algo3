package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Creador.CreadorEnemigos;
import org.junit.jupiter.api.Test;

public class probandoAndo {

    @Test
    public void lala(){
        CreadorEnemigos creador = new CreadorEnemigos();

        System.out.println(creador.crearEnemigosDeNivel("ArchivosJson/enemigosDePrueba"));

    }
}
