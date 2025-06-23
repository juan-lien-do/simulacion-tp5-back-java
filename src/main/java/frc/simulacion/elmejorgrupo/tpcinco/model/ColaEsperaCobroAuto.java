package frc.simulacion.elmejorgrupo.tpcinco.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

public class ColaEsperaCobroAuto implements Serializable {
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

    public void inicializar(){
        this.colaIdAuto = new LinkedList<>();
    }
}
