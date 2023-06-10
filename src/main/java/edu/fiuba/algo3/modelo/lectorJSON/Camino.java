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

   public Pasarela inicial(){
      return camino.get(camino.size() - 1);
   }

   public void agregar(Coordenada coordenadaNueva) {

      Pasarela nuevaPasarela;
      TipoPasarela tipoPasarela = new Final();
      Pasarela proximaPasarela = null;

      if (!camino.isEmpty()) {
         tipoPasarela = new Intermedia();
         proximaPasarela = camino.get(camino.size());
      }

      nuevaPasarela = new Pasarela(coordenadaNueva, proximaPasarela, tipoPasarela);

      mapa.agregar(coordenadaNueva, nuevaPasarela);
   }
}
