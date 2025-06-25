package frc.simulacion.elmejorgrupo.tpcinco.generadores;

import java.util.Arrays;
import java.util.List;

public class Demo {

    public static void main(String[] args) {

        // --- PARÁMETROS DE ENTRADA ---
        final float constanteC = 1.0f;
        final float constanteT = 100f;
        final float d_inicial = 0.0f;
        final float t_inicial = 0.0f;
        final float paso_h = 0.1f;

        // ¡NUEVO! Definimos el valor de D en el que queremos parar
        final float d_meta = 180.0f;

        // --- FIN DE LA SECCIÓN DE PARÁMETROS ---

        //System.out.println("--- Solucionador RK4 hasta alcanzar un valor meta ---");

        // 1. Definir la EDO
        EcuacionDiferencial miEcuacion = (t, d) -> (float) (constanteC + 0.6f * constanteT + Math.pow(t, 2f));

        // 2. Instanciar nuestro solucionador
        SolucionadorRK4 miSolucionador = new SolucionadorRK4();

        // 4. Llamar al nuevo método del solucionador
        List<float[]> matrizFinal = miSolucionador.resolverHastaValor(miEcuacion, t_inicial, d_inicial, d_meta, paso_h);

        // 5. Presentar el resultado final
        //System.out.printf("  Se alcanzó la meta en el tiempo t ≈ %.4f\n", t_final);
    }
}