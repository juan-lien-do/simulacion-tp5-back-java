package frc.simulacion.elmejorgrupo.tpcinco.model;

import frc.simulacion.elmejorgrupo.tpcinco.generadores.GeneradorNumAleat;
import frc.simulacion.elmejorgrupo.tpcinco.util.CustomPair;

public class VectorEstado {
    private Long nroIteracion;
    private Evento evento;
    private Float reloj = 0f;
    private GestorLlegadas gestorLlegadas;
    private Boolean esLibrePlaya;
    private GestorSectores gestorSectores;
    private GestorAutos gestorAutos;
    private VentanillaCobro ventanillaCobro;
    // TODO otras cosas

    private Long contadorAutosAbortados;
    private Float acumuladorGanancia;
    private Float acumuladorTiempoEstacionamiento;


    // constructor vacio - inicial
    public VectorEstado() {
        this.nroIteracion = 1L;
        this.evento = Evento.INICIO_SIMULACION;
        this.reloj = 0f;

        // parte de los generadores
        this.gestorLlegadas = new GestorLlegadas();
        this.gestorLlegadas.calcularLlegada(reloj);

        this.esLibrePlaya = true;

        this.gestorSectores = new GestorSectores();
        gestorSectores.inicializar();

        this.ventanillaCobro = new VentanillaCobro();
        ventanillaCobro.inicializar();

        this.gestorAutos = new GestorAutos();
        gestorAutos.inicializar();
    }

    public VectorEstado(VectorEstado prev){
        this.nroIteracion = prev.nroIteracion +1;
        // TODO hacer lo que falta
        CustomPair<Evento, Float> proxEvento = prev.decidirProximoEvento();
        this.reloj = proxEvento.getValue();
        this.evento = proxEvento.getKey();

        if (evento == Evento.LLEGADA_AUTO){
            this.gestorLlegadas.calcularLlegada(this.reloj);
            // hay que ver si la playa esta llena
            Auto auto = new Auto();
            if (!prev.esLibrePlaya){
                //esto es si esta llena la playa
                contadorAutosAbortados++;

            }
        }

    }

    public CustomPair<Evento, Float> decidirProximoEvento(){
        Float nextHora = 0f;
        Evento nextEvento = Evento.FIN_SIMULACION;

        if (this.ventanillaCobro.getEstaLibre() && this.gestorAutos.estaVacia()){
            // no hay autos en ventanilla ni llegaron autos
            // osea que solo hay que esperar al prox auto
            nextHora = this.gestorLlegadas.getHoraLlegadaAuto();
            nextEvento = Evento.LLEGADA_AUTO;

        } else if (this.ventanillaCobro.getEstaLibre() && !this.gestorAutos.estaVacia()){
            // si no hay autos en ventanilla pero si llegaron autos antes
            // puede haber fin_estacionamiento y nuevallegada
            // de los autos
            Float horaAutoMasCercana = this.gestorAutos.horaMasCercana();

            //
            Float horaVentanilla = ventanillaCobro.getFinCobroAuto();
            if (horaVentanilla > horaAutoMasCercana){
                nextHora = horaAutoMasCercana;
                nextEvento = Evento.FIN_COBRO;
            } else {
                nextHora = horaVentanilla;
                nextEvento = Evento.LLEGADA_AUTO;
            }

        } else if (!this.ventanillaCobro.getEstaLibre() && this.gestorAutos.estaVacia()){
            // si hay autos en ventanilla pero no existen autos ?????
            // wtf esto no deberia pasar
            System.out.print("ERROR FATAL EN LA SIGUIENTE ITERACION");
            System.out.println(this.nroIteracion);

        } else {// si no esta libre la ventanilla pero HAY autos
            // hay que tener en cuenta =
            // llegada prox auto,
            // fin de cobro
            // fin de estacionamiento
            Float horaVentanilla = ventanillaCobro.getFinCobroAuto();

            // si es true aca es porque es cobro
            CustomPair<Boolean, Float> parRespuesta = gestorAutos.horaMasCercanaCobroOEstacionamiento();

            if(horaVentanilla < parRespuesta.getValue()){
                nextHora = horaVentanilla;
                nextEvento = Evento.LLEGADA_AUTO;
            } else if (parRespuesta.getKey()) {
                nextHora = parRespuesta.getValue();
                nextEvento = Evento.FIN_COBRO;
            } else {
                nextHora = parRespuesta.getValue();
                nextEvento = Evento.FIN_ESTACIONAMIENTO;
            }
        }

        // esto es para devolver la hora y el evento a la vez
        return new CustomPair<>(nextEvento, nextHora);
    }
}
