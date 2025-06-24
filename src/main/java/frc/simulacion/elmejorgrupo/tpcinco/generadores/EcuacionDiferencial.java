package frc.simulacion.elmejorgrupo.tpcinco.generadores;

@FunctionalInterface
public interface EcuacionDiferencial {
    float evaluar(float x, float y);
}
