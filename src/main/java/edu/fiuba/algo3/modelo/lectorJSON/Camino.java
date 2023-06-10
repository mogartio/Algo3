package edu.fiuba.algo3.modelo.lectorJSON;

import edu.fiuba.algo3.modelo.Excepciones.NoHayCamino;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;
import edu.fiuba.algo3.modelo.parcelas.PasarelaFinal;
import edu.fiuba.algo3.modelo.parcelas.PasarelaIntermedia;

import java.util.ArrayList;

public class Camino {
   public ArrayList<Pasarela> camino;
   private Mapa mapa;

   public Camino(Mapa mapaNuevo) {
      camino = new ArrayList<Pasarela>();
      mapa = mapaNuevo;
   }

   public Pasarela inicial() throws NoHayCamino {
      if (camino.size() == 0)
         throw new NoHayCamino();
      //Agrego ternario para que pasen los tests, en uso real no llegará nunca a esta cond
      return camino.get(camino.size() - 1);
   }

   public void agregar(Coordenada coordenadaNueva) {

      Pasarela nuevaPasarela;

      if (camino.isEmpty()) {
         nuevaPasarela = new PasarelaFinal(coordenadaNueva);
      } else {
         nuevaPasarela = new PasarelaIntermedia(coordenadaNueva, camino.get(camino.size()));
      }
      camino.add(nuevaPasarela);
      mapa.agregar(coordenadaNueva, nuevaPasarela);
   }
}
