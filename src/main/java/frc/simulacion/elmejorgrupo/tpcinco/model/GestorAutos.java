package frc.simulacion.elmejorgrupo.tpcinco.model;

import frc.simulacion.elmejorgrupo.tpcinco.util.CustomPair;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class GestorAutos implements Serializable {
    public List<Auto> autos;

    @Override
    public GestorAutos clone(){
        return (GestorAutos) SerializationUtils.clone(this);
    }

    public void inicializar(){
        this.autos = new ArrayList<>();
    }
    public boolean estaVacia(){
        return this.autos.isEmpty();
    }

    public void agregarAutoNoAtendido(Auto aut){
        autos.add(aut);
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

    public GestorAutos() {
        this.autos = new ArrayList<>();
    }
}
