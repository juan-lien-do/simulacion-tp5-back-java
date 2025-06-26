package frc.simulacion.elmejorgrupo.tpcinco.generadores;

import frc.simulacion.elmejorgrupo.tpcinco.model.TiposAuto;

public class Generador {
    public static TiposAuto generarAuto(Double RND){
        if (RND < 0.45f){
            return TiposAuto.PEQUENIO;
        } else if (RND < 0.70f) {
            return TiposAuto.GRANDE;
        } else {
            return TiposAuto.UTILITARIO;
        }
    }

    public static Double generarTiempoLlegadaAuto(Double RND){
        //RND/=10; esto fuerza que aparezcan autos sin atender
        return (Double) (-13f * Math.log(1-RND));
    }

    public static Double generarTiempoEstacionamiento(Double RND){
        if (RND < 0.5f){
            return (double) 60f;
        } else if (RND < 0.8f) {
            return (double) 120f;
        } else if (RND < 0.95){
            return (double) 180f;
        } else {
            return (double) 240f;
        }
    }
}
