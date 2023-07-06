package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.miscelanea.Coordenada;

interface Construible {
    void construir(Coordenada posicionParcela,  Parcela parcela);

    boolean puedeConstruir(String unaDefensa);

    boolean ocupada();
}