package frc.simulacion.elmejorgrupo.tpcinco.generadores;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase de producción propia que implementa el método numérico de
 * Runge-Kutta de cuarto orden (RK4).
 * ESTA VERSIÓN ESTÁ MODIFICADA PARA MOSTRAR CADA PASO Y DETENERSE
 * CUANDO LA VARIABLE DEPENDIENTE ALCANZA UN VALOR META.
 */
public class SolucionadorRK4 {

    public List<float[]> resolverHastaValor(EcuacionDiferencial ecuacion, float x_inicial, float y_inicial, float y_meta, float h) {
        List<float[]> matriz = new ArrayList<>();



        float y_actual = y_inicial;
        float x_actual = x_inicial;
        int paso = 0;
        final int MAX_PASOS = 10000;

        while (y_actual < y_meta && paso < MAX_PASOS) {

            float k1 = ecuacion.evaluar(x_actual, y_actual);
            float k2 = ecuacion.evaluar((float) (x_actual + 0.5 * h), (float) (y_actual + 0.5 * h * k1));
            float k3 = ecuacion.evaluar((float) (x_actual + 0.5 * h), (float) (y_actual + 0.5 * h * k2));
            float k4 = ecuacion.evaluar(x_actual + h, y_actual + h * k3);

            float incremento_y = (float) ((h / 6.0) * (k1 + 2.0 * k2 + 2.0 * k3 + k4));
            float y_siguiente = y_actual + incremento_y;

            float[] arrayFila = new float[9];
            arrayFila[0] = paso;
            arrayFila[1] = x_actual;
            arrayFila[2] = y_actual;
            arrayFila[3] = k1;
            arrayFila[4] = k2;
            arrayFila[5] = k3;
            arrayFila[6] = k4;
            arrayFila[7] = x_actual + h;
            arrayFila[8] = y_siguiente;

            matriz.add(arrayFila);
            y_actual = y_siguiente;
            x_actual += h;
            paso++;
        }


        if(paso >= MAX_PASOS) {
            System.out.println("\nADVERTENCIA: Se alcanzó el número máximo de pasos.");
        }


        return matriz;
    }
}