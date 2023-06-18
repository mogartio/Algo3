package edu.fiuba.algo3.modelo.miscelanea;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.concurrent.ThreadLocalRandom.current;

public class RandomGenerator {
    private int minimo;
    private int maximo;

    public RandomGenerator(int min,int max){
        minimo = min;
        maximo = max;
    }
    public int obtenerUnNumero(){
        return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
    }

}
