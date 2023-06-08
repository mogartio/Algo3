package edu.fiuba.algo3.modelo.lectorJSON;

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

   public void agregar(Coordenada coordenadaNueva) {
      Pasarela nuevaPasarela;
      if (camino.isEmpty()) {
         nuevaPasarela = new PasarelaFinal(coordenadaNueva, null);
      } else {
         nuevaPasarela = new PasarelaIntermedia(coordenadaNueva, camino.get(camino.size()));
      }
      mapa.agregar(nuevaPasarela);
   }
}
