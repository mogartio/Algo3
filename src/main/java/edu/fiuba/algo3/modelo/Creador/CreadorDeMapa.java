
package edu.fiuba.algo3.modelo.Creador;


import edu.fiuba.algo3.modelo.Excepciones.NoHayCamino;
import edu.fiuba.algo3.modelo.Excepciones.NoHayInicial;
import edu.fiuba.algo3.modelo.juego.Camino;
import edu.fiuba.algo3.modelo.juego.Lector;
import edu.fiuba.algo3.modelo.juego.Mapa;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Normal;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;
import edu.fiuba.algo3.modelo.parcelas.Rocosa;
import edu.fiuba.algo3.modelo.parcelas.Tierra;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class CreadorDeMapa {
    public Camino camino;
    public Mapa mapa;

    public CreadorDeMapa(){
        mapa = new Mapa();
         //cuidado se parte del supuesto de que el mapa es cuadrado (misma cantidad de celdas tanto en Y como en X)
    }

    public Mapa crearMapa(String path, int tamanioMax) throws NoHayCamino, NoHayInicial {
        JSONArray jsonArray = Lector.leer( path );
        camino = new Camino(tamanioMax);
        crearMapa((JSONObject) jsonArray.get(1));
        return mapa;
    }

    private void crearMapa(JSONObject listaFilas) throws NoHayCamino, NoHayInicial {

        for(int i = 1; i <= listaFilas.size(); i++){
            crearFila(Integer.toString(i), listaFilas);
        }
        camino.armar();
        mapa.setPasarelaInicialFinal(camino.inicial(), camino.pasarelaFinal());
    }

    private void crearFila(Object numeroFila, JSONObject listaFilas) {
        JSONArray fila = (JSONArray) listaFilas.get(numeroFila);

        int coordenadaY = Integer.parseInt((String) numeroFila);

        for(int coordenadaX = 1; coordenadaX <= fila.size(); coordenadaX++){
            crearParcela((String) fila.get(coordenadaX - 1), coordenadaY, coordenadaX);
        }
    }

    private void crearParcela(String tipoDeTerreno, int coordY, int coordX){

        Coordenada nuevaCoordenada = new Coordenada(coordX, coordY);
        switch (tipoDeTerreno) {
            case "Rocoso":
                mapa.agregar(nuevaCoordenada, new Rocosa(nuevaCoordenada));
                break;
            case "Pasarela":
                camino.agregar(nuevaCoordenada);
                mapa.agregar(nuevaCoordenada, new Pasarela(nuevaCoordenada, new Normal()));
                break;
            case "Tierra":
                mapa.agregar(nuevaCoordenada, new Tierra(nuevaCoordenada));
                break;
        }
    }
}