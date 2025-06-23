package frc.simulacion.elmejorgrupo.tpcinco.generadores;

import frc.simulacion.elmejorgrupo.tpcinco.model.TiposAuto;

public class Generador {
    public static TiposAuto generarAuto(Float RND){
        if (RND < 0.45f){
            return TiposAuto.PEQUENIO;
        } else if (RND < 0.70f) {
            return TiposAuto.GRANDE;
        } else {
            return TiposAuto.UTILITARIO;
        }
    }

    public static Float generarTiempoLlegadaAuto(Float RND){
        return (float) (-13f * Math.log(1-RND));
    }

    public static float generarTiempoEstacionamiento(Float RND){
        if (RND < 0.5f){
            return 60f;
        } else if (RND < 0.8f) {
            return 120f;
        } else if (RND < 0.95){
            return 180f;
        } else {
            return 240f;
        }
    }
}
