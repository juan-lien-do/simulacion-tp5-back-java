package frc.simulacion.elmejorgrupo.tpcinco.generadores;

import java.util.Arrays;
import java.util.List;

public class Demo {

    public static void main(String[] args) {

        // --- PARÁMETROS DE ENTRADA ---
        final Double constanteC = 1.0;
        final Double constanteT = 100.0;
        final Double d_inicial = 0.0;
        final Double t_inicial = 0.0;
        final Double paso_h = 0.1;

        // ¡NUEVO! Definimos el valor de D en el que queremos parar
        final Double d_meta = 180.0;

        // --- FIN DE LA SECCIÓN DE PARÁMETROS ---

        //System.out.println("--- Solucionador RK4 hasta alcanzar un valor meta ---");

        // 1. Definir la EDO
        EcuacionDiferencial miEcuacion = (t, d) -> (Double) (constanteC + 0.6f * constanteT + Math.pow(t, 2f));

        // 2. Instanciar nuestro solucionador
        SolucionadorRK4 miSolucionador = new SolucionadorRK4();

        // 4. Llamar al nuevo método del solucionador
        List<double[]> matrizFinal = miSolucionador.resolverHastaValor(miEcuacion, t_inicial, d_inicial, d_meta, paso_h);

        // 5. Presentar el resultado final
        //System.out.printf("  Se alcanzó la meta en el tiempo t ≈ %.4f\n", t_final);
    }
}