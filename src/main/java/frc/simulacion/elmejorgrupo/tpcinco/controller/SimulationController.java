package frc.simulacion.elmejorgrupo.tpcinco.controller;

import java.util.List;
import frc.simulacion.elmejorgrupo.tpcinco.ElementoListaDTO;
import frc.simulacion.elmejorgrupo.tpcinco.web.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class SimulationController {
    @Autowired
    public SimulationService simulationService;

    @GetMapping("/simular/{cantidadIteraciones}/{parametroT}/{saltoH}/{filaDesde}/{filaHasta}/")
    public ResponseEntity<List<ElementoListaDTO>> Simular(@PathVariable Long cantidadIteraciones, @PathVariable Long parametroT, @PathVariable Float saltoH,
                                    @PathVariable Long filaDesde, @PathVariable Long filaHasta){
        return ResponseEntity.ok(simulationService.iniciarSimulacion(cantidadIteraciones, parametroT, saltoH, filaDesde, filaHasta));
    }
}
