package frc.simulacion.elmejorgrupo.tpcinco.generadores;

@FunctionalInterface
public interface EcuacionDiferencial {
    Double evaluar(Double x, Double y);
}
