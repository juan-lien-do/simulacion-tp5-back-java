package frc.simulacion.elmejorgrupo.tpcinco.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VentanillaCobro {
    private ColaEsperaCobroAuto colaEsperaCobroAuto;
    private Boolean estaLibre;
    private Float valorRungeKutta;
    private Float finCobroAuto;

    public void inicializar(){
        this.colaEsperaCobroAuto = new ColaEsperaCobroAuto();
        colaEsperaCobroAuto.inicializar();

        this.estaLibre = true;
        this.valorRungeKutta = null;
        this.finCobroAuto = null;
    }
}
