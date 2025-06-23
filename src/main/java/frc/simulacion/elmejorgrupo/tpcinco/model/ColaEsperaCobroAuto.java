package frc.simulacion.elmejorgrupo.tpcinco.model;

import java.util.Queue;

public class ColaEsperaCobroAuto {
    private Queue<Long> colaIdAuto;

    public boolean estaVacia(){
        return colaIdAuto.isEmpty();
    }

    public Long conseguirProximoAuto(){
        return colaIdAuto.poll();
    }

    public void agregarAuto(Long idAuto){
        colaIdAuto.add(idAuto);
    }
}
