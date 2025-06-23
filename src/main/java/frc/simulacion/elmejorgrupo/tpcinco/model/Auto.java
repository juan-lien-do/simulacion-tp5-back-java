package frc.simulacion.elmejorgrupo.tpcinco.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Auto {
    private static Long proximaId = 1L;

    private Long id;
    private WrapperEstadoAuto estadoAuto;
    private Float horaFinEstado;

    public static Long generarId(){
        return proximaId++;
    }

    public boolean estoyEnSectorTal(Long idSector){
        return estadoAuto.estoyEnSectorTal(idSector);
    }

    // para generar nuevo evento
    public boolean sePuedeTenerEnCuenta(){
        return estadoAuto.estoyEnSector() || estadoAuto.estoyEnCobro();
    }
}


