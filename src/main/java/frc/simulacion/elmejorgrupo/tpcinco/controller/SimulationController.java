package frc.simulacion.elmejorgrupo.tpcinco.controller;

import frc.simulacion.elmejorgrupo.tpcinco.generadores.GeneradorRungeKutta;
import frc.simulacion.elmejorgrupo.tpcinco.model.VectorEstado;

import java.util.LinkedList;
import java.util.List;

public class SimulationController {
    public static void main(String[] args){
        //pruebaSerialization();
        iniciarSimulacion(20L, 100L, 0.1f);
    }

    public static void sillyPrueba(){
        System.out.println(Math.log(Math.E));
    }

    public static Object iniciarSimulacion(Long cantidadIteraciones, Long parametroT, Float saltoH){
        List<VectorEstado> vectores = new LinkedList<>();
        /*
        CONFIGURACION DE LA RK
                 */
        GeneradorRungeKutta.configurarTyH((float) parametroT, saltoH);

        // primera iteracion
        VectorEstado primerVector = VectorEstado.obtenerVectorInicial();
        vectores.add(primerVector);
        // siguientes iteraciones
        for (long i = 0; i < cantidadIteraciones-1; i++){
            VectorEstado nuevoVector = VectorEstado.predecirProximoVector(vectores.get((int) i));
            vectores.add(nuevoVector);
            // aca transformamos el vector choto este en un array


        }
        return null;
    }


}
