package frc.simulacion.elmejorgrupo.tpcinco.model;

import frc.simulacion.elmejorgrupo.tpcinco.generadores.Generador;
import frc.simulacion.elmejorgrupo.tpcinco.generadores.GeneradorNumAleat;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GestorLlegadas {
    private Float rndLlegadaAuto;
    private Float tiempoLlegadaAuto;
    private Float horaLlegadaAuto;
    private Float rndTipoAuto;
    private TiposAuto tipoAuto;
    private Float rndTiempo;
    private Float tiempoEstacionamiento;

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
}
