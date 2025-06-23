package frc.simulacion.elmejorgrupo.tpcinco.model;


public class Auto {
    private static Long proximaId = 1L;

    private Long id;
    private WrapperEstadoAuto estadoAuto;

    public static Long generarId(){
        return proximaId++;
    }

    private boolean estoyEnSectorTal(Long idSector){
        return estadoAuto.estoyEnSectorTal(idSector);
    }

}


