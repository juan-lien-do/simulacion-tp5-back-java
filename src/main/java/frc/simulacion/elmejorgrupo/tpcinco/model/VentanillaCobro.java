package frc.simulacion.elmejorgrupo.tpcinco.model;


import org.springframework.util.SerializationUtils;

import java.io.Serializable;


public class VentanillaCobro implements Serializable {
    private ColaEsperaCobroAuto colaEsperaCobroAuto;
    private Boolean estaLibre;
    private Double valorRungeKutta;
    private Double finCobroAuto;

    @Override
    public VentanillaCobro clone(){
        return (VentanillaCobro) SerializationUtils.clone(this);
    }

    public Long conseguirAutosEnCola(){
        return colaEsperaCobroAuto.conseguirAutosEnCola();
    }

    // agregar auto
    public void agregarAuto(Auto aut){
        colaEsperaCobroAuto.agregarAuto(aut.getId());
    }


    // getter setter y otros

    public void inicializar(){
        this.colaEsperaCobroAuto = new ColaEsperaCobroAuto();
        colaEsperaCobroAuto.inicializar();

        this.estaLibre = true;
        this.valorRungeKutta = null;
        this.finCobroAuto = null;
    }

    public ColaEsperaCobroAuto getColaEsperaCobroAuto() {
        return colaEsperaCobroAuto;
    }

    public void setColaEsperaCobroAuto(ColaEsperaCobroAuto colaEsperaCobroAuto) {
        this.colaEsperaCobroAuto = colaEsperaCobroAuto;
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

    public Long conseguirAuto() {
        return colaEsperaCobroAuto.conseguirProximoAuto();
    }

    public void devolverDatosTexto(StringBuilder sb) {
        sb.append(this.estaLibre);
        sb.append(" || ");
        sb.append(this.valorRungeKutta);
        sb.append(" || ");
        sb.append(this.finCobroAuto);
    }
}
