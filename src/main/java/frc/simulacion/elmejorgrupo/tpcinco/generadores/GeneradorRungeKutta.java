package frc.simulacion.elmejorgrupo.tpcinco.generadores;

import java.util.ArrayList;
import java.util.List;

public class GeneradorRungeKutta {
    public static Float paramT;
    public static Float saltoH;

    public static final Float Xo = 0f;
    public static final Float Yo = 0f;

    public static void configurarTyH(Float parametroT, Float saltoH){
        GeneradorRungeKutta.saltoH = saltoH;
        GeneradorRungeKutta.paramT = parametroT;
    }

    // siento que estoy cometiendo crimenes contra la programacion
    public static List<float[]> calcularYDevolverMatriz(float paramD, float paramC){
        EcuacionDiferencial miEcuacion = (t, d) -> (float) (paramC + 0.6f * paramT + Math.pow(t, 2f));

        SolucionadorRK4 miSolucionador = new SolucionadorRK4();

        return miSolucionador.resolverHastaValor(miEcuacion, Xo, Yo, paramD, saltoH);
    }
}
