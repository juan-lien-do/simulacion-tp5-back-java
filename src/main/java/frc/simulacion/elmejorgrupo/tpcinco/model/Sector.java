package frc.simulacion.elmejorgrupo.tpcinco.model;


import java.io.Serializable;

public class Sector implements Serializable {
    private Long idSector;
    private Boolean esLibre;


    public Sector(Long idSector, Boolean esLibre) {
        this.idSector = idSector;
        this.esLibre = esLibre;
    }
}
