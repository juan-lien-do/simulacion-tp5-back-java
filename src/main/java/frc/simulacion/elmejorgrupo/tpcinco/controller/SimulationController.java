package frc.simulacion.elmejorgrupo.tpcinco.controller;

import java.util.List;
import frc.simulacion.elmejorgrupo.tpcinco.ElementoListaDTO;
import frc.simulacion.elmejorgrupo.tpcinco.web.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimulationController {
    @Autowired
    public SimulationService simulationService;

    @GetMapping("/simular/{cantidadIteraciones}/{parametroT}/{saltoH}/")
    public ResponseEntity<List<ElementoListaDTO>> Simular(@PathVariable Long cantidadIteraciones, @PathVariable Long parametroT, @PathVariable Float saltoH){
        return ResponseEntity.ok(simulationService.iniciarSimulacion(cantidadIteraciones, parametroT, saltoH));
    }
}
