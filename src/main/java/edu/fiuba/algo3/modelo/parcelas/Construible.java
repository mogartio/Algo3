package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;

import java.util.HashMap;
import java.util.Map;

interface Construible {
    void construir(Coordenada posicionParcela,  Parcela parcela);

    boolean puedeConstruir(String unaDefensa);

    boolean ocupada();
}