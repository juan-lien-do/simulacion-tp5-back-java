package frc.simulacion.elmejorgrupo.tpcinco.model;

import frc.simulacion.elmejorgrupo.tpcinco.util.CustomPair;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


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

    public void purgar() {
        List<Auto> purgados = new ArrayList<>();
        for (Auto auto : autos) {
            if (auto.getEstadoAuto().estoyEnSector()
                    || auto.getEstadoAuto().estoyEnCobro()
                    || auto.getEstadoAuto().estoyEnColaCobro()) {
                purgados.add(auto);
            }
        }
        this.autos = purgados;
    }

    public void agregarAuto(Auto aut){
        autos.add(aut);
    }

    public Double horaMasCercana(Double rel) {
        Double horaMasCercana = Double.MAX_VALUE;
        for (int i = 0; i < autos.size(); i++){
            Auto aut =autos.get(i);
            if (aut.getEstadoAuto().estoyEnSector()){
                if (horaMasCercana > aut.getHoraFinEstado() && aut.getHoraFinEstado() > rel){
                    horaMasCercana = aut.getHoraFinEstado();
                }
            }
        }
        return horaMasCercana;
    }

    public CustomPair<Boolean, Double> horaMasCercanaCobroOEstacionamiento(Double rel) {
        Double horaMasCercana = Double.MAX_VALUE;
        Boolean esCobro = false;
        for (int i = 0; i < autos.size(); i++){
            Auto aut =autos.get(i);
            if (aut.sePuedeTenerEnCuenta(rel)){
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

    /**
     *
     * @param reloj
     * @return Id del sector donde esta estacionado
     */
    public Auto buscarAutoFinalizaEstacionamiento(Double reloj) {
        for (int i = 0; i < this.autos.size() ;i++){
            Auto aut = this.autos.get(i);
            if (aut.getEstadoAuto().estoyEnSector() && Objects.equals(aut.getHoraFinEstado(), reloj)){
                return aut;
            }
        }
        //System.out.println("ERROR BUSCANDO AUTO CON ID" + reloj);
        return this.autos.get(this.autos.size()-1);

    }

    public Auto buscarAutoFinalizaCobro() {
        for (int i = 0; i < this.autos.size() ;i++){
            Auto aut = this.autos.get(i);
            if (aut.getEstadoAuto().estoyEnCobro()){
                return aut;
            }
        }
        return null;
    }

    public Auto buscarAutoPorId( Long id) {
        for (int i = 0; i < this.autos.size() ;i++){
            Auto aut = this.autos.get(i);
            if (Objects.equals(aut.getId(), id)){
                return aut;
            }
        }
        return null;
    }

    // esto no hace falta optimizarlo
    public void devolverDatosTexto(StringBuilder sb) {
        for (int i = 0; i < autos.size(); i++){
            Auto auto = autos.get(i);
            sb.append(auto.getId());
            sb.append(" || ");
            sb.append(auto.getTipoAuto().name());
            sb.append(" || ");
            sb.append(auto.getHoraFinEstado());
            sb.append(" || ");
            sb.append(auto.getEstadoAuto().getEstadoAuto());
            sb.append(" || ");
            sb.append(auto.getEstadoAuto().getIdSector());
            sb.append(" || ");
        }
    }

    public List<Auto> getAutos() {
        return autos;
    }

    public void setAutos(List<Auto> autos) {
        this.autos = autos;
    }
}
