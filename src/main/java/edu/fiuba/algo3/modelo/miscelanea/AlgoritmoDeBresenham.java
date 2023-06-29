package edu.fiuba.algo3.modelo.miscelanea;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class AlgoritmoDeBresenham {

    public static LinkedList<Coordenada> getCamino(Coordenada puntoInicial, Coordenada puntoFinal) {

        LinkedList<Coordenada> camino = new LinkedList<Coordenada>();

        int x1 = puntoInicial.getAbscisa();
        int y1 = puntoInicial.getOrdenada();
        int x2 = puntoFinal.getAbscisa();
        int y2 = puntoFinal.getOrdenada();

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int abs = dx - dy;

        int posX, posY;

        posX = (x1 < x2) ? 1 : -1;
        posY = (y1 < y2) ? 1 : -1;

        while ((x1 != x2) || (y1 != y2)) {
            int p = 2 * abs;

            if (p > -dy) {
                abs = abs - dy;
                x1 = x1 + posX;
            }

            if (p < dx) {
                abs = abs + dx;
                y1 = y1 + posY;
            }

            camino.add(new Coordenada(x1, y1));
        }

        return camino;
    }
}

