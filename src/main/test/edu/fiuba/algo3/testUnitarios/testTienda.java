package edu.fiuba.algo3.testUnitarios;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.fiuba.algo3.modelo.juego.Credito;
import edu.fiuba.algo3.modelo.miscelanea.Tienda;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/*
tienda instancia internamente un new Credito(n) por lo tanto, el mock creado en el test nunca va a poder
responder como se espera al metodo "esMayorOIgualQue()"*/

public class testTienda {
    @Test
    public void test01TiendaNoOfreceNingunaDefensaAlRecibirMenosDe10Creditos(){
        Tienda tienda = new Tienda();
        Credito creditoMockeado = mock(Credito.class);

        when(creditoMockeado.esMayorOIgualQue(new Credito(10))).thenReturn(true);
        when(creditoMockeado.esMayorOIgualQue(new Credito(20))).thenReturn(false);

        ArrayList<String> catalogoObtenido = tienda.catalogoDisponible(creditoMockeado);

        assertTrue(catalogoObtenido.size() == 0);
    }
    @Test
    public void test02TiendaNoOfreceNingunaDefensaAlRecibirEntre10Y20Creditos(){
        Tienda tienda = new Tienda();
        Credito creditoMockeado = mock(Credito.class);

        when(creditoMockeado.esMayorOIgualQue(new Credito(10))).thenReturn(true);
        when(creditoMockeado.esMayorOIgualQue(new Credito(20))).thenReturn(false);

        ArrayList<String> catalogoObtenido = tienda.catalogoDisponible(creditoMockeado);

        assertTrue(catalogoObtenido.size() == 0);
    }
    /*@Test
    public void test03TiendaNoOfreceNingunaDefensaAlRecibirMasDe20Creditos(){
        Tienda tienda = new Tienda();
        Credito creditoMockeado = mock(Credito.class);

        when(creditoMockeado.esMayorOIgualQue(new Credito(10))).thenReturn(true);
        when(creditoMockeado.esMayorOIgualQue(new Credito(20))).thenReturn(true);

        ArrayList<String> catalogoObtenido = tienda.catalogoDisponible(creditoMockeado);

        assertTrue(catalogoObtenido.size() == 2);
    }*/

}
