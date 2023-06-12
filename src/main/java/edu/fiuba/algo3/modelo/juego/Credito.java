package edu.fiuba.algo3.modelo.juego;

public class Credito {
    private int recursos;

    public Credito(int cantidadInicial){
        recursos = cantidadInicial;
    }
    public void agregar(int cantidadAAgregar){
        recursos += cantidadAAgregar;
    }
    public int obtenerCreditos(){
        return recursos;
    }

    // metodos encargados de descontar
    public void descontar(Credito otroCredito){
        //este metodo se le aplica a una intacia de Credito a la cual se le quiera descontar el argumento
        recursos = otroCredito.descontar(recursos);
    }
    public int descontar(int minuendo) {
        //este metodo se encarga de el calculo del descuento sin violar encapsulamiento
        return minuendo - recursos;
    }

    //metodos encargadas del equals
    public boolean equals(int creditoAComparar){
        return recursos == creditoAComparar;
    }
    public boolean equals(Credito otroCredito){
        return otroCredito.equals(recursos);
    }

    //metodos encargadas de la comparacion >=
    public boolean esMayorOIgualQue(Credito otroCredito){
        return otroCredito.esMenorQue(recursos);
    }
    public boolean esMenorQue(int creditoAComparar){
        return recursos <= creditoAComparar;
    }



}
