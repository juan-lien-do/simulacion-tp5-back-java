package frc.simulacion.elmejorgrupo.tpcinco.controller;

import frc.simulacion.elmejorgrupo.tpcinco.generadores.GeneradorNumAleat;
import frc.simulacion.elmejorgrupo.tpcinco.generadores.GeneradorRungeKutta;
import frc.simulacion.elmejorgrupo.tpcinco.model.Auto;
import frc.simulacion.elmejorgrupo.tpcinco.model.VectorEstado;

import java.util.LinkedList;
import java.util.List;

public class SimulationManager {
    public static void main(String[] args){
        //pruebaSerialization();
        iniciarSimulacion(50L, 100L, 0.1f);
    }



    public static List<VectorEstado> iniciarSimulacion(Long cantidadIteraciones, Long parametroT, Float saltoH){
        List<VectorEstado> vectores = new LinkedList<>();
        /*
        CONFIGURACION DE LA RK
                 */
        GeneradorRungeKutta.configurarTyH((float) parametroT, saltoH);

        // primera iteracion
        VectorEstado primerVector = VectorEstado.obtenerVectorInicial();
        vectores.add(primerVector);
        //System.out.println(primerVector.generarTextoPrueba());
        // siguientes iteraciones
        for (long i = 0; i < cantidadIteraciones-1; i++){
            //System.out.println();
            VectorEstado nuevoVector = VectorEstado.predecirProximoVector(vectores.get(vectores.size()-1));
            vectores.add(nuevoVector);
            // aca transformamos el vector choto este en un array
            /// TODO VISUALIZAR VECTOR
            //System.out.println(nuevoVector.generarTextoPrueba());
            if (vectores.size() > 301){
                vectores.remove(300);
            }
        }
        return vectores;
    }

    public static List<VectorEstado> iniciarSimulacion(Long cantidadIteraciones, Long parametroT, Float saltoH, Long filaDesde, Long filaHasta) {
        List<VectorEstado> vectores = new LinkedList<>();

        // Configuración de la RK
        GeneradorRungeKutta.configurarTyH((float) parametroT, saltoH);
        // Configuracion de ids
        Auto.reiniciarIdIncremental();
        // configuracion num aleat
        GeneradorNumAleat.reiniciarAleat();;

        // Primera iteración
        VectorEstado vectorActual = VectorEstado.obtenerVectorInicial();

        for (long i = 0; i < cantidadIteraciones; i++) {
            // Guardar el vector si está dentro del rango o si es la última fila
            if ((i >= filaDesde && i <= filaHasta) || i == cantidadIteraciones - 1) {
                vectores.add(vectorActual);
            }

            // Predecir el siguiente vector para la próxima iteración
            if (i < cantidadIteraciones - 1) {
                vectorActual = VectorEstado.predecirProximoVector(vectorActual);
            }
        }

        return vectores;
    }


}
