package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.TrampaArenosa;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;

import java.util.HashMap;
import java.util.Map;

public class DisponibleDefensa implements Construible{

    private static Map<String, Boolean> defensasAdmitidas = new HashMap<>();
    static {
       defensasAdmitidas.put("TorreBlanca", true);
       defensasAdmitidas.put("TorrePlateada", true);
       defensasAdmitidas.put("TrampaArenosa", false);
    }

    @Override
    public void construir(Coordenada coordenadaParcela,  Parcela parcela){
        parcela.setConstruible(new NoDisponible());
    }

    @Override
    public boolean puedeConstruir(String unaDefensa) {
        return defensasAdmitidas.get(unaDefensa);
    }

    @Override
    public boolean ocupada() {
        return false;
    }
}
