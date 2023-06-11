package edu.fiuba.algo3.modelo.lectorJSON;

import edu.fiuba.algo3.modelo.Excepciones.NoHayCamino;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Meta;
import edu.fiuba.algo3.modelo.parcelas.Normal;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;

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
      camino.get(camino.size() - 1 ).actualizarTipo(new Meta());
      return camino.get(0);
   }

   public void agregar(Coordenada coordenadaNueva) {

      Pasarela nuevaPasarela = new Pasarela(coordenadaNueva, new Normal());

      if (camino.isEmpty()) {
         camino.add(nuevaPasarela);
      }else{
         camino.get(camino.size() - 1).agregarSiguiente(nuevaPasarela);
         camino.add(nuevaPasarela);
      }
   }
}
