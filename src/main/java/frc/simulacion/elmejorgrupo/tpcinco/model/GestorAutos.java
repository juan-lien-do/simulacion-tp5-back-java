package frc.simulacion.elmejorgrupo.tpcinco.model;

import frc.simulacion.elmejorgrupo.tpcinco.util.CustomPair;

import java.util.ArrayList;
import java.util.List;

public class GestorAutos {
    public List<Auto> autos;

    public void inicializar(){
        this.autos = new ArrayList<>();
    }
    public boolean estaVacia(){
        return this.autos.isEmpty();
    }

    public Float horaMasCercana() {
        Float horaMasCercana = Float.MAX_VALUE;
        for (int i = 0; i < autos.size(); i++){
            Auto aut =autos.get(i);
            if (aut.sePuedeTenerEnCuenta()){
                if (horaMasCercana > aut.getHoraFinEstado()){
                    horaMasCercana = aut.getHoraFinEstado();
                }
            }
        }
        return horaMasCercana;
    }


    public CustomPair<Boolean, Float> horaMasCercanaCobroOEstacionamiento() {
        Float horaMasCercana = Float.MAX_VALUE;
        Boolean esCobro = null;
        for (int i = 0; i < autos.size(); i++){
            Auto aut =autos.get(i);
            if (aut.sePuedeTenerEnCuenta()){
                if (horaMasCercana > aut.getHoraFinEstado()){
                    horaMasCercana = aut.getHoraFinEstado();
                    esCobro = aut.getEstadoAuto().estoyEnCobro();
                }
            }
        }
        return new CustomPair<>(esCobro, horaMasCercana);

    }
}
