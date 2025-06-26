package frc.simulacion.elmejorgrupo.tpcinco.DTO;

public class AutoDTO {
    private Long id;
    private String estadoAuto;
    private String tipoAuto;
    private Float horaInicioEstacionamiento;
    private Float horaFinEstacionamiento;
    private Long idSector;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstadoAuto() {
        return estadoAuto;
    }

    public void setEstadoAuto(String estadoAuto) {
        this.estadoAuto = estadoAuto;
    }

    public String getTipoAuto() {
        return tipoAuto;
    }

    public void setTipoAuto(String tipoAuto) {
        this.tipoAuto = tipoAuto;
    }

    public Float getHoraFinEstacionamiento() {
        return horaFinEstacionamiento;
    }

    public Float getHoraInicioEstacionamiento() {
        return horaInicioEstacionamiento;
    }

    public void setHoraInicioEstacionamiento(Float horaInicioEstacionamiento) {
        this.horaInicioEstacionamiento = horaInicioEstacionamiento;
    }

    public void setHoraFinEstacionamiento(Float horaFinEstacionamiento) {
        this.horaFinEstacionamiento = horaFinEstacionamiento;
    }

    public Long getIdSector() {
        return idSector;
    }

    public void setIdSector(Long idSector) {
        this.idSector = idSector;
    }
}
