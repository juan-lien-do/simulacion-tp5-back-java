package frc.simulacion.elmejorgrupo.tpcinco.model;

import java.util.List;

public class GestorSectores {
    public List<Sector> sectores;

    public void inicializar(){
        for(int i = 0; i < 10; i++){
            Sector sector = new Sector((long) i+1, true);
            sectores.add(sector);
        }
    }

}
