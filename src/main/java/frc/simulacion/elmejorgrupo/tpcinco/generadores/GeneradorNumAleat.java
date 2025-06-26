package frc.simulacion.elmejorgrupo.tpcinco.generadores;

public class GeneradorNumAleat {
    public static Long nextNumber = 9L;
    public static final Long g = 24L;
    public static final Long m = (long) Math.pow(2L, g);
    public final Long k = 5L;
    public static final Long a = 45L; // 5 + 8 * 5

    public static Double generarNumeroAleatorio(){
        nextNumber = ((a * nextNumber) % m);
        return (double) nextNumber / (m - 1);
    }

    public static void reiniciarAleat(){
        nextNumber = 9L;
    }
}
