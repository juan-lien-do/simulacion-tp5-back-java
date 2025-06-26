package frc.simulacion.elmejorgrupo.tpcinco.web;

import frc.simulacion.elmejorgrupo.tpcinco.ElementoListaDTO;
import frc.simulacion.elmejorgrupo.tpcinco.controller.SimulationManager;
import frc.simulacion.elmejorgrupo.tpcinco.model.VectorEstado;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimulationService {
    public List<ElementoListaDTO> iniciarSimulacion(Long cantidadIteraciones, Long parametroT, Double saltoH, Long filaDesde, Long filaHasta){
        List<VectorEstado> respuesta = SimulationManager.iniciarSimulacion(cantidadIteraciones, parametroT, saltoH, filaDesde, filaHasta);

        return respuesta.stream().map(VectorEstado::toDTO).toList();
    }
}
