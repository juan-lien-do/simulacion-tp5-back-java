package frc.simulacion.elmejorgrupo.tpcinco;

import frc.simulacion.elmejorgrupo.tpcinco.DTO.AutoDTO;
import frc.simulacion.elmejorgrupo.tpcinco.model.TiposAuto;
import frc.simulacion.elmejorgrupo.tpcinco.util.SectorDTO;

import java.util.List;

public class ElementoListaDTO {
    private Long nroIteracion;
    private String evento;
    private Float reloj;
    private Boolean esLibre;
    private Float rndLlegadaAuto;
    private Float tiempoLlegadaAuto;
    private Float horaLlegadaAuto;
    private Float rndTipoAuto;
    private String tipoAuto;
    private Float rndTiempo;
    private Float tiempoEstacionamiento;
    private List<SectorDTO> sectorDTOS;
    private List<AutoDTO> autoDTOS;

    private Long autosEnCola;
    private Boolean estaLibre;
    private Float valorRungeKutta;
    private Float finCobroAuto;

    private Long contadorAutosNoAtendidos;
    private Float acumuladorGanancia;
    private Float acumuladorTiempoEstacionamiento;
}
