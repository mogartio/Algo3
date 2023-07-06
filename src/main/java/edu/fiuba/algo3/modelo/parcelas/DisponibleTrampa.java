package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.miscelanea.Coordenada;

import java.util.HashMap;
import java.util.Map;

public class DisponibleTrampa implements Construible{

    private static final Map<String, Boolean> defensasAdmitidas = new HashMap<>();
    static {
        defensasAdmitidas.put("TorreBlanca", false);
        defensasAdmitidas.put("TorrePlateada", false);
        defensasAdmitidas.put("TrampaArenosa", true);
    }


    public void construir(Coordenada coordenadaParcela,  Parcela parcela){
        parcela.setConstruible(new NoDisponible());
    }

    @Override
    public boolean puedeConstruir(String unaDefensa){
        return defensasAdmitidas.get(unaDefensa);
    }

    @Override
    public boolean ocupada() {
        return false;
    }
}
