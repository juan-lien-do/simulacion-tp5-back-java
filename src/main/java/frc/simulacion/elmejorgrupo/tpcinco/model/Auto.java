package frc.simulacion.elmejorgrupo.tpcinco.model;

import java.io.Serializable;

/*
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter*/
public class Auto implements Serializable {
    private static Long proximaId = 1L;


    private Long id;
    private TiposAuto tipoAuto;
    private WrapperEstadoAuto estadoAuto;
    private Double horaLlegada;
    private Double horaFinEstado;

    public static Long generarId(){
        return proximaId++;
    }

    public Auto(){
        this.id = generarId();
    }

    public static void reiniciarIdIncremental(){
        proximaId = 1L;
    }

    public boolean estoyEnSectorTal(Long idSector){
        return estadoAuto.estoyEnSectorTal(idSector);
    }

    // para generar nuevo evento
    public boolean sePuedeTenerEnCuenta(Double rel){
        return (estadoAuto.estoyEnSector() || estadoAuto.estoyEnCobro()) && this.getHoraFinEstado() > rel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WrapperEstadoAuto getEstadoAuto() {
        return estadoAuto;
    }

    public void setEstadoAuto(WrapperEstadoAuto estadoAuto) {
        this.estadoAuto = estadoAuto;
    }

    public Double getHoraFinEstado() {
        return horaFinEstado;
    }

    public void setHoraFinEstado(Double horaFinEstado) {
        this.horaFinEstado = horaFinEstado;
    }

    public TiposAuto getTipoAuto() {
        return tipoAuto;
    }

    public void setTipoAuto(TiposAuto tipoAuto) {
        this.tipoAuto = tipoAuto;
    }

    public Double getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(Double horaLlegada) {
        this.horaLlegada = horaLlegada;
    }
}


