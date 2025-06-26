package frc.simulacion.elmejorgrupo.tpcinco;

import frc.simulacion.elmejorgrupo.tpcinco.DTO.AutoDTO;
import frc.simulacion.elmejorgrupo.tpcinco.model.TiposAuto;
import frc.simulacion.elmejorgrupo.tpcinco.util.SectorDTO;

import java.util.List;

public class ElementoListaDTO {
    private Long nroIteracion;
    private String evento;
    private Double reloj;
    private Boolean esLibre;
    private Double rndLlegadaAuto;
    private Double tiempoLlegadaAuto;
    private Double horaLlegadaAuto;
    private Double rndTipoAuto;
    private String tipoAuto;
    private Double rndTiempo;
    private Double tiempoEstacionamiento;
    private List<double[]> matriz;

    private List<SectorDTO> sectorDTOS;
    private List<AutoDTO> autoDTOS;


    private Long autosEnCola;
    private Boolean estaLibre;
    private Double valorRungeKutta;
    private Double finCobroAuto;
    private Double dineroACobrar;

    private Long contadorAutosNoAtendidos;
    private Double acumuladorGanancia;
    private Double acumuladorTiempoEstacionamiento;

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

    public Double getReloj() {
        return reloj;
    }

    public void setReloj(Double reloj) {
        this.reloj = reloj;
    }

    public Boolean getEsLibre() {
        return esLibre;
    }

    public void setEsLibre(Boolean esLibre) {
        this.esLibre = esLibre;
    }

    public Double getRndLlegadaAuto() {
        return rndLlegadaAuto;
    }

    public void setRndLlegadaAuto(Double rndLlegadaAuto) {
        this.rndLlegadaAuto = rndLlegadaAuto;
    }

    public Double getTiempoLlegadaAuto() {
        return tiempoLlegadaAuto;
    }

    public void setTiempoLlegadaAuto(Double tiempoLlegadaAuto) {
        this.tiempoLlegadaAuto = tiempoLlegadaAuto;
    }

    public Double getHoraLlegadaAuto() {
        return horaLlegadaAuto;
    }

    public void setHoraLlegadaAuto(Double horaLlegadaAuto) {
        this.horaLlegadaAuto = horaLlegadaAuto;
    }

    public Double getRndTipoAuto() {
        return rndTipoAuto;
    }

    public void setRndTipoAuto(Double rndTipoAuto) {
        this.rndTipoAuto = rndTipoAuto;
    }

    public String getTipoAuto() {
        return tipoAuto;
    }

    public void setTipoAuto(String tipoAuto) {
        this.tipoAuto = tipoAuto;
    }

    public Double getRndTiempo() {
        return rndTiempo;
    }

    public void setRndTiempo(Double rndTiempo) {
        this.rndTiempo = rndTiempo;
    }

    public Double getTiempoEstacionamiento() {
        return tiempoEstacionamiento;
    }

    public void setTiempoEstacionamiento(Double tiempoEstacionamiento) {
        this.tiempoEstacionamiento = tiempoEstacionamiento;
    }

    public List<double[]> getMatriz() {
        return matriz;
    }

    public void setMatriz(List<double[]> matriz) {
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

    public Double getValorRungeKutta() {
        return valorRungeKutta;
    }

    public void setValorRungeKutta(Double valorRungeKutta) {
        this.valorRungeKutta = valorRungeKutta;
    }

    public Double getFinCobroAuto() {
        return finCobroAuto;
    }

    public void setFinCobroAuto(Double finCobroAuto) {
        this.finCobroAuto = finCobroAuto;
    }

    public Long getContadorAutosNoAtendidos() {
        return contadorAutosNoAtendidos;
    }

    public void setContadorAutosNoAtendidos(Long contadorAutosNoAtendidos) {
        this.contadorAutosNoAtendidos = contadorAutosNoAtendidos;
    }

    public Double getAcumuladorGanancia() {
        return acumuladorGanancia;
    }

    public void setAcumuladorGanancia(Double acumuladorGanancia) {
        this.acumuladorGanancia = acumuladorGanancia;
    }

    public Double getAcumuladorTiempoEstacionamiento() {
        return acumuladorTiempoEstacionamiento;
    }

    public void setAcumuladorTiempoEstacionamiento(Double acumuladorTiempoEstacionamiento) {
        this.acumuladorTiempoEstacionamiento = acumuladorTiempoEstacionamiento;
    }

    public Double getDineroACobrar() {
        return dineroACobrar;
    }

    public void setDineroACobrar(Double dineroACobrar) {
        this.dineroACobrar = dineroACobrar;
    }

}
