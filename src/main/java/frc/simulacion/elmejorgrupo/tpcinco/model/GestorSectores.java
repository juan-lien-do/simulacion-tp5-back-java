package frc.simulacion.elmejorgrupo.tpcinco.model;

import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GestorSectores implements Serializable {
    public List<Sector> sectores;

    public void inicializar(){
        sectores = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            Sector sector = new Sector((long) i+1, true);
            sectores.add(sector);
        }
    }

    @Override
    public GestorSectores clone(){
        return (GestorSectores) SerializationUtils.clone(this);
    }

    public Long buscarYOcuparLibre() {
        Long idLibre = 0L;
        for (int i = 0; i < sectores.size(); i++){
            if (sectores.get(i).getEsLibre()){
                idLibre = sectores.get(i).getIdSector();
                sectores.get(i).setEsLibre(false);
                break;
            }
        }

        return idLibre;
    }

    public boolean estaLleno() {
        for (int i = 0; i < sectores.size(); i++){
            if(sectores.get(i).getEsLibre()){
                return false;
            }
        }
        return true;
    }

    public void liberarSector(Long idSector) {
        for (int i = 0; i < sectores.size(); i++){
            if (Objects.equals(sectores.get(i).getIdSector(), idSector)){
                sectores.get(i).setEsLibre(true);
            }
        }
    }

    public void devolverDatosTexto(StringBuilder sb) {
        for (int i = 0; i < sectores.size(); i++){
            Sector sec = sectores.get(i);
            sb.append(sec.getIdSector());
            sb.append(" || ");
            sb.append(sec.getEsLibre());
            sb.append(" || ");
        }
    }

    public List<Sector> getSectores() {
        return sectores;
    }

    public void setSectores(List<Sector> sectores) {
        this.sectores = sectores;
    }
}
