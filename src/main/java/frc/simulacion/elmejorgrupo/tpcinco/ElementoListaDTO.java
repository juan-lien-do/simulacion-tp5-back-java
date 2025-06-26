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
    private List<float[]> matriz;

    private List<SectorDTO> sectorDTOS;
    private List<AutoDTO> autoDTOS;


    private Long autosEnCola;
    private Boolean estaLibre;
    private Float valorRungeKutta;
    private Float finCobroAuto;
    private Float dineroACobrar;

    private Long contadorAutosNoAtendidos;
    private Float acumuladorGanancia;
    private Float acumuladorTiempoEstacionamiento;

    public Long getNroIteracion() {
        return nroIteracion;
    }

    public void setNroIteracion(Long nroIteracion) {
        this.nroIteracion = nroIteracion;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public Float getReloj() {
        return reloj;
    }

    public void setReloj(Float reloj) {
        this.reloj = reloj;
    }

    public Boolean getEsLibre() {
        return esLibre;
    }

    public void setEsLibre(Boolean esLibre) {
        this.esLibre = esLibre;
    }

    public Float getRndLlegadaAuto() {
        return rndLlegadaAuto;
    }

    public void setRndLlegadaAuto(Float rndLlegadaAuto) {
        this.rndLlegadaAuto = rndLlegadaAuto;
    }

    public Float getTiempoLlegadaAuto() {
        return tiempoLlegadaAuto;
    }

    public void setTiempoLlegadaAuto(Float tiempoLlegadaAuto) {
        this.tiempoLlegadaAuto = tiempoLlegadaAuto;
    }

    public Float getHoraLlegadaAuto() {
        return horaLlegadaAuto;
    }

    public void setHoraLlegadaAuto(Float horaLlegadaAuto) {
        this.horaLlegadaAuto = horaLlegadaAuto;
    }

    public Float getRndTipoAuto() {
        return rndTipoAuto;
    }

    public void setRndTipoAuto(Float rndTipoAuto) {
        this.rndTipoAuto = rndTipoAuto;
    }

    public String getTipoAuto() {
        return tipoAuto;
    }

    public void setTipoAuto(String tipoAuto) {
        this.tipoAuto = tipoAuto;
    }

    public Float getRndTiempo() {
        return rndTiempo;
    }

    public void setRndTiempo(Float rndTiempo) {
        this.rndTiempo = rndTiempo;
    }

    public Float getTiempoEstacionamiento() {
        return tiempoEstacionamiento;
    }

    public void setTiempoEstacionamiento(Float tiempoEstacionamiento) {
        this.tiempoEstacionamiento = tiempoEstacionamiento;
    }

    public List<float[]> getMatriz() {
        return matriz;
    }

    public void setMatriz(List<float[]> matriz) {
        this.matriz = matriz;
    }

    public List<SectorDTO> getSectorDTOS() {
        return sectorDTOS;
    }

    public void setSectorDTOS(List<SectorDTO> sectorDTOS) {
        this.sectorDTOS = sectorDTOS;
    }

    public List<AutoDTO> getAutoDTOS() {
        return autoDTOS;
    }

    public void setAutoDTOS(List<AutoDTO> autoDTOS) {
        this.autoDTOS = autoDTOS;
    }

    public Long getAutosEnCola() {
        return autosEnCola;
    }

    public void setAutosEnCola(Long autosEnCola) {
        this.autosEnCola = autosEnCola;
    }

    public Boolean getEstaLibre() {
        return estaLibre;
    }

    public void setEstaLibre(Boolean estaLibre) {
        this.estaLibre = estaLibre;
    }

    public Float getValorRungeKutta() {
        return valorRungeKutta;
    }

    public void setValorRungeKutta(Float valorRungeKutta) {
        this.valorRungeKutta = valorRungeKutta;
    }

    public Float getFinCobroAuto() {
        return finCobroAuto;
    }

    public void setFinCobroAuto(Float finCobroAuto) {
        this.finCobroAuto = finCobroAuto;
    }

    public Long getContadorAutosNoAtendidos() {
        return contadorAutosNoAtendidos;
    }

    public void setContadorAutosNoAtendidos(Long contadorAutosNoAtendidos) {
        this.contadorAutosNoAtendidos = contadorAutosNoAtendidos;
    }

    public Float getAcumuladorGanancia() {
        return acumuladorGanancia;
    }

    public void setAcumuladorGanancia(Float acumuladorGanancia) {
        this.acumuladorGanancia = acumuladorGanancia;
    }

    public Float getAcumuladorTiempoEstacionamiento() {
        return acumuladorTiempoEstacionamiento;
    }

    public void setAcumuladorTiempoEstacionamiento(Float acumuladorTiempoEstacionamiento) {
        this.acumuladorTiempoEstacionamiento = acumuladorTiempoEstacionamiento;
    }

    public Float getDineroACobrar() {
        return dineroACobrar;
    }

    public void setDineroACobrar(Float dineroACobrar) {
        this.dineroACobrar = dineroACobrar;
    }
}
