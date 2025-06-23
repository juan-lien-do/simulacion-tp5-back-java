package frc.simulacion.elmejorgrupo.tpcinco.controller;

import frc.simulacion.elmejorgrupo.tpcinco.model.VectorEstado;

import java.util.LinkedList;
import java.util.List;

public class SimulationController {
    public static void main(String[] args){
        sillyPrueba();

    }

    public static void sillyPrueba(){
        System.out.println(Math.log(Math.E));
    }

    public static Object iniciarSimulacion(Long cantidadIteraciones, Long parametroT){
        List<VectorEstado> vectores = new LinkedList<>();

        // primera iteracion
        VectorEstado primerVector = new VectorEstado();
        vectores.add(primerVector);
        // siguientes iteraciones
        for (long i = 0; i < cantidadIteraciones-1; i++){
            VectorEstado nuevoVector = new VectorEstado(vectores.get(i));
        }
    }
}
