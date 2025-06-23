package frc.simulacion.elmejorgrupo.tpcinco.generadores;

public class Generador {
    public static Object generarAuto(){
        float RND = (float) Math.random();
        if (RND < 0.45f){
            return "Auto chico";
        } else if (RND < 0.70f) {
            return "Auto grande";
        } else {
            return "Utilitario";
        }
    }

    public static Float generarTiempoLlegadaAuto(){
        float RND = (float) Math.random();
        return (float) (-13f * Math.log(1-RND));
    }

    public static float generarTiempoEstacionamiento(){
        float RND = (float) Math.random();
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
