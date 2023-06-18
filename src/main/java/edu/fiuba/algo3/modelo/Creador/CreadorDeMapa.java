
package edu.fiuba.algo3.modelo.Creador;


import edu.fiuba.algo3.modelo.Excepciones.NoHayCamino;
import edu.fiuba.algo3.modelo.Excepciones.NoHayInicial;
import edu.fiuba.algo3.modelo.Interface.VisualizadorDeMapa;
import edu.fiuba.algo3.modelo.lectorJSON.Camino;
import edu.fiuba.algo3.modelo.lectorJSON.Lector;
import edu.fiuba.algo3.modelo.lectorJSON.Mapa;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Rocosa;
import edu.fiuba.algo3.modelo.parcelas.Tierra;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class CreadorDeMapa {
    public Camino camino;
    public Mapa mapa;
    String path;
    private VisualizadorDeMapa visualizador;

    public CreadorDeMapa(String path,int tamanioMax){
        mapa = new Mapa();
        camino = new Camino(tamanioMax); //cuidado se parte del supuesto de que el mapa es cuadrado (misma cantidad de celdas tanto en Y como en X)
        this.path = path;
        this.visualizador = new VisualizadorDeMapa(tamanioMax);
    }

    public Mapa crearMapa() throws NoHayCamino, NoHayInicial {
        JSONArray jsonArray = Lector.leer(this.path);
        crearMapa((JSONObject) jsonArray.get(1));
        visualizador.mostrar();
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
                mapa.agregar(nuevaCoordenada, new Pasarela(nuevaCoordenada));
                break;
            case "Tierra":
                mapa.agregar(nuevaCoordenada, new Tierra(nuevaCoordenada));
                break;
        }

        visualizador.agregar(tipoDeTerreno);
    }
}