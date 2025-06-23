package frc.simulacion.elmejorgrupo.tpcinco.model;

import java.util.Objects;

public class WrapperEstadoAuto {
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
        return this.estadoAuto == EstadoAuto.ABORTADO;
    }

    public boolean estoyEnSectorTal(Long idSector){
        if (estoyEnSector()){
            return Objects.equals(this.idSector, idSector);
        } else return false;
    }
}
