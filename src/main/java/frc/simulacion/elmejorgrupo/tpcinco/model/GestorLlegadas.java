package frc.simulacion.elmejorgrupo.tpcinco.model;

import frc.simulacion.elmejorgrupo.tpcinco.generadores.Generador;
import frc.simulacion.elmejorgrupo.tpcinco.generadores.GeneradorNumAleat;
import lombok.*;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;

public class GestorLlegadas implements Serializable {
    private Double rndLlegadaAuto;
    private Double tiempoLlegadaAuto;
    private Double horaLlegadaAuto;
    private Double rndTipoAuto;
    private TiposAuto tipoAuto;
    private Double rndTiempo;
    private Double tiempoEstacionamiento;

    @Override
    public GestorLlegadas clone(){
        return (GestorLlegadas) SerializationUtils.clone(this);
    }

    public void devolverDatosTexto(StringBuilder sb){
        sb.append(rndLlegadaAuto);
        sb.append("|| ");
        sb.append(tiempoLlegadaAuto);
        sb.append("|| ");
        sb.append(horaLlegadaAuto);
        sb.append("|| ");
        sb.append(rndTipoAuto);
        sb.append("|| ");
        sb.append(tipoAuto.name());
        sb.append("|| ");
        sb.append(rndTiempo);
        sb.append("|| ");
        sb.append(tiempoEstacionamiento);
    }

    // llegada
    public void calcularLlegada(Double reloj){
        this.rndLlegadaAuto = GeneradorNumAleat.generarNumeroAleatorio();
        this.tiempoLlegadaAuto = Generador.generarTiempoLlegadaAuto(rndLlegadaAuto);
        this.horaLlegadaAuto = tiempoLlegadaAuto + reloj;

        this.rndTipoAuto = GeneradorNumAleat.generarNumeroAleatorio();
        this.tipoAuto = Generador.generarAuto(rndTipoAuto);

        this.rndTiempo = GeneradorNumAleat.generarNumeroAleatorio();
        this.tiempoEstacionamiento = Generador.generarTiempoEstacionamiento(rndTiempo);
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

    public TiposAuto getTipoAuto() {
        return tipoAuto;
    }

    public void setTipoAuto(TiposAuto tipoAuto) {
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

    public GestorLlegadas() {
    }

    public GestorLlegadas(Double rndLlegadaAuto, Double tiempoLlegadaAuto, Double horaLlegadaAuto, Double rndTipoAuto, TiposAuto tipoAuto, Double rndTiempo, Double tiempoEstacionamiento) {
        this.rndLlegadaAuto = rndLlegadaAuto;
        this.tiempoLlegadaAuto = tiempoLlegadaAuto;
        this.horaLlegadaAuto = horaLlegadaAuto;
        this.rndTipoAuto = rndTipoAuto;
        this.tipoAuto = tipoAuto;
        this.rndTiempo = rndTiempo;
        this.tiempoEstacionamiento = tiempoEstacionamiento;
    }
}
