package frc.simulacion.elmejorgrupo.tpcinco.controller;

import frc.simulacion.elmejorgrupo.tpcinco.generadores.GeneradorRungeKutta;
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


}
