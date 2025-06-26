package frc.simulacion.elmejorgrupo.tpcinco.generadores;

import java.util.ArrayList;
import java.util.List;

public class GeneradorRungeKutta {
    public static Double paramT;
    public static Double saltoH;

    public static final Double Xo = 0.0;
    public static final Double Yo = 0.0;

    public static void configurarTyH(Double parametroT, Double saltoH){
        GeneradorRungeKutta.saltoH = saltoH;
        GeneradorRungeKutta.paramT = parametroT;
    }

    // siento que estoy cometiendo crimenes contra la programacion
    public static List<double[]> calcularYDevolverMatriz(Double paramD, Double paramC){
        EcuacionDiferencial miEcuacion = (t, d) -> (Double) (paramC + 0.6f * paramT + Math.pow(t, 2f));

        SolucionadorRK4 miSolucionador = new SolucionadorRK4();

        return miSolucionador.resolverHastaValor(miEcuacion, Xo, Yo, paramD, saltoH);
    }
}
