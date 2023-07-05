package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.Excepciones.NoHayCamino;
import edu.fiuba.algo3.modelo.Excepciones.NoHayInicial;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Meta;
import edu.fiuba.algo3.modelo.parcelas.Normal;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;

import java.util.ArrayList;

public class Camino {
   private final ArrayList<Pasarela> camino;
   private Pasarela pasarelaInicial = null;
   private Pasarela pasarelaFinal = null;
   private boolean inicialDeterminada;
   private final int maximoMapa;

   public Camino(int maximoMapa) {
      this.maximoMapa = maximoMapa;
      camino = new ArrayList<>();
   }

   public Pasarela inicial() throws NoHayCamino, NoHayInicial {
      if (camino.size() == 0)
         throw new NoHayCamino();
      //No es un else if porque puede haber una pasarela pero si no esta en el borde no puede ser inicial
      if(pasarelaInicial == null)
         throw new NoHayInicial();

      return pasarelaInicial;
   }

   public void armar() {
      int cantidadConectadas = 1;
      Pasarela actualDeterminada = pasarelaInicial;
      Pasarela excluida = null;
      Pasarela anterior;

      while(cantidadConectadas < camino.size()){
         anterior = actualDeterminada;
         //Condicion necesaria para que no se determine que la anterior puede ser la siguiente
         actualDeterminada = actualDeterminada.obtenerSiguienteExcluyendo(camino, excluida);
         excluida = anterior;
         anterior.agregarSiguiente(actualDeterminada);
         cantidadConectadas++;
      }

      pasarelaFinal = actualDeterminada;
      actualDeterminada.actualizarTipo(new Meta());
   }

   public void agregar(Coordenada coordenadaNueva) {
      Pasarela nuevaPasarela = new Pasarela(coordenadaNueva, new Normal());
      camino.add(nuevaPasarela);

      if(!inicialDeterminada && coordenadaNueva.esBorde(maximoMapa)){
         inicialDeterminada = true;
         pasarelaInicial = nuevaPasarela;
      }
   }

   public Pasarela pasarelaFinal(){
      return pasarelaFinal;
   }
}
