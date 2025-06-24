package frc.simulacion.elmejorgrupo.tpcinco.model;

import frc.simulacion.elmejorgrupo.tpcinco.generadores.Generador;
import frc.simulacion.elmejorgrupo.tpcinco.generadores.GeneradorNumAleat;
import lombok.*;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;

public class GestorLlegadas implements Serializable {
    private Float rndLlegadaAuto;
    private Float tiempoLlegadaAuto;
    private Float horaLlegadaAuto;
    private Float rndTipoAuto;
    private TiposAuto tipoAuto;
    private Float rndTiempo;
    private Float tiempoEstacionamiento;

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
    public void calcularLlegada(Float reloj){
        this.rndLlegadaAuto = GeneradorNumAleat.generarNumeroAleatorio();
        this.tiempoLlegadaAuto = Generador.generarTiempoLlegadaAuto(rndLlegadaAuto);
        this.horaLlegadaAuto = tiempoLlegadaAuto + reloj;

        this.rndTipoAuto = GeneradorNumAleat.generarNumeroAleatorio();
        this.tipoAuto = Generador.generarAuto(rndTipoAuto);

        this.rndTiempo = GeneradorNumAleat.generarNumeroAleatorio();
        this.tiempoEstacionamiento = Generador.generarTiempoEstacionamiento(rndTiempo);
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

    public TiposAuto getTipoAuto() {
        return tipoAuto;
    }

    public void setTipoAuto(TiposAuto tipoAuto) {
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

    public GestorLlegadas() {
    }

    public GestorLlegadas(Float rndLlegadaAuto, Float tiempoLlegadaAuto, Float horaLlegadaAuto, Float rndTipoAuto, TiposAuto tipoAuto, Float rndTiempo, Float tiempoEstacionamiento) {
        this.rndLlegadaAuto = rndLlegadaAuto;
        this.tiempoLlegadaAuto = tiempoLlegadaAuto;
        this.horaLlegadaAuto = horaLlegadaAuto;
        this.rndTipoAuto = rndTipoAuto;
        this.tipoAuto = tipoAuto;
        this.rndTiempo = rndTiempo;
        this.tiempoEstacionamiento = tiempoEstacionamiento;
    }
}
