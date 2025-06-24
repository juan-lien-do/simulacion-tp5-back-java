package frc.simulacion.elmejorgrupo.tpcinco.model;

import java.io.Serializable;
import java.util.Objects;


public class WrapperEstadoAuto implements Serializable {
    private EstadoAuto estadoAuto;
    private Long idSector;

    public boolean estoyEnSector(){
        return this.estadoAuto == EstadoAuto.EN_SECTOR;
    }
    public boolean estoyEnCobro(){
        return this.estadoAuto == EstadoAuto.EN_COBRO;
    }
    public boolean estoyEnColaCobro(){
        return this.estadoAuto == EstadoAuto.EN_COLA_COBRO;
    }
    public boolean estoyFinalizado(){
        return this.estadoAuto == EstadoAuto.FINALIZADO;
    }
    public boolean estoyAbortado(){
        return this.estadoAuto == EstadoAuto.NO_ATENDIDO;
    }

    public boolean estoyEnSectorTal(Long idSector){
        if (estoyEnSector()){
            return Objects.equals(this.idSector, idSector);
        } else return false;
    }

    public WrapperEstadoAuto(EstadoAuto estadoAuto) {
        this.estadoAuto = estadoAuto;
    }

    public WrapperEstadoAuto(EstadoAuto estadoAuto, Long idSector) {
        this.estadoAuto = estadoAuto;
        this.idSector = idSector;
    }

    public EstadoAuto getEstadoAuto() {
        return estadoAuto;
    }

    public void setEstadoAuto(EstadoAuto estadoAuto) {
        this.estadoAuto = estadoAuto;
    }

    public Long getIdSector() {
        return idSector;
    }

    public void setIdSector(Long idSector) {
        this.idSector = idSector;
    }
}
