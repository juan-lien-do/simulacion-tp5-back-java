package frc.simulacion.elmejorgrupo.tpcinco.web;

import frc.simulacion.elmejorgrupo.tpcinco.ElementoListaDTO;
import frc.simulacion.elmejorgrupo.tpcinco.controller.SimulationManager;
import frc.simulacion.elmejorgrupo.tpcinco.model.VectorEstado;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimulationService {
    public List<ElementoListaDTO> iniciarSimulacion(Long cantidadIteraciones, Long parametroT, Float saltoH){
        List<VectorEstado> respuesta = SimulationManager.iniciarSimulacion(cantidadIteraciones, parametroT, saltoH);

        return respuesta.stream().map(VectorEstado::toDTO).toList();
    }
}
