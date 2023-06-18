package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;

interface Construible {
    void construir(Coordenada posicionParcela, Defensa defensaContruir, Parcela parcela);

    boolean puedeConstruir();
}