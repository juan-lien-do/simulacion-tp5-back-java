package frc.simulacion.elmejorgrupo.tpcinco.model;

import frc.simulacion.elmejorgrupo.tpcinco.util.CustomPair;

public class VectorEstado {

    private Long nroIteracion;
    private Evento evento;
    private Float reloj = 0f;
    private Boolean esLibrePlaya;
    private GestorLlegadas gestorLlegadas;
    private GestorSectores gestorSectores;
    private GestorAutos gestorAutos;
    private VentanillaCobro ventanillaCobro;
    // TODO otras cosas

    private Long contadorAutosNoAtendidos;
    private Float acumuladorGanancia;
    private Float acumuladorTiempoEstacionamiento;

    // constructor vacio
    public VectorEstado() {
    }

    // metodo para conseguir el vector inicial inicial
    public static VectorEstado obtenerVectorInicial() {
        VectorEstado porFavorApruebenos = new VectorEstado();

        porFavorApruebenos.nroIteracion = 1L;
        porFavorApruebenos.evento = Evento.INICIO_SIMULACION;
        porFavorApruebenos.reloj = 0f;

        // parte de los generadores
        porFavorApruebenos.gestorLlegadas = new GestorLlegadas();
        porFavorApruebenos.gestorLlegadas.calcularLlegada(porFavorApruebenos.reloj);

        porFavorApruebenos.esLibrePlaya = true;

        porFavorApruebenos.gestorSectores = new GestorSectores();
        porFavorApruebenos.gestorSectores.inicializar();

        porFavorApruebenos.ventanillaCobro = new VentanillaCobro();
        porFavorApruebenos.ventanillaCobro.inicializar();

        porFavorApruebenos.gestorAutos = new GestorAutos();
        porFavorApruebenos.gestorAutos.inicializar();
        return porFavorApruebenos;
    }

    public static VectorEstado obtenerCopia(VectorEstado vec){
        VectorEstado nuevoVec = new VectorEstado();

        // valores primitivos
        nuevoVec.nroIteracion = vec.nroIteracion;
        nuevoVec.evento = vec.evento;
        nuevoVec.reloj = vec.reloj;
        nuevoVec.esLibrePlaya = vec.esLibrePlaya;

        // valores por referencia
        nuevoVec.gestorLlegadas = vec.gestorLlegadas.clone();

        nuevoVec.gestorSectores = vec.gestorSectores.clone();

        nuevoVec.ventanillaCobro = vec.ventanillaCobro.clone();

        nuevoVec.gestorAutos = vec.gestorAutos.clone();

        return nuevoVec;
    }

    public static VectorEstado operarNuevaLlegada(VectorEstado vector){

        // Hay que armar el auto
        Auto auto = new Auto();
        TiposAuto nuevoTipo = vector.gestorLlegadas.getTipoAuto();

        auto.setTipoAuto(nuevoTipo);
        // hay que ver si la playa esta llena
        if (!vector.esLibrePlaya){
            // si la playa esta llena el auto se va -> modificar el auto
            auto.setEstadoAuto(new WrapperEstadoAuto(EstadoAuto.NO_ATENDIDO));
            vector.gestorAutos.agregarAutoNoAtendido(auto);
            // actualizar contador
            vector.contadorAutosNoAtendidos++;
        } else {
            // calcular tiempo del estacionamiento, obtener id de un sector libre,
            // asignar ese auto en ese sector libre, si llego a los 10 sectores ocupados entonces hay que actualizar
            // y poner que la playa esta ocupada.
            Float tmpEstacionamiento = vector.gestorLlegadas.getTiempoEstacionamiento();
            auto.setHoraFinEstado(vector.reloj + tmpEstacionamiento);

            Long idSectorLibre = vector.gestorSectores.buscarYOcuparLibre();

            auto.setEstadoAuto(new WrapperEstadoAuto(EstadoAuto.EN_SECTOR, idSectorLibre));

            if (vector.gestorSectores.estaLleno()){
                vector.esLibrePlaya = false;
            }
        }

        // se calcula la nueva llegada
        vector.gestorLlegadas.calcularLlegada(vector.reloj);

        return vector;
        /*
        * TODO
        * Calcular el resto acumuladores si es necesario
        * */
    }

    public static VectorEstado predecirProximoVector(VectorEstado prev){
        VectorEstado nuevoVec;

        // copiamos y hacemos clonacion del vector anterior.
        nuevoVec = obtenerCopia(prev);

        // TODO hacer lo que falta
        CustomPair<Evento, Float> proxEvento = prev.decidirProximoEvento();
        nuevoVec.reloj = proxEvento.getValue();
        nuevoVec.evento = proxEvento.getKey();

        if (nuevoVec.evento == Evento.LLEGADA_AUTO){
            operarNuevaLlegada(nuevoVec);
        } else if (nuevoVec.evento == Evento.FIN_ESTACIONAMIENTO) {
            operarFinEstacionamiento(nuevoVec);
        }

        return nuevoVec;

    }

    private static void operarFinEstacionamiento(VectorEstado nuevoVec) {
        
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
            System.out.print("ERROR FATAL EN LA SIGUIENTE ITERACION: ");
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

    public VectorEstado(Long nroIteracion, Evento evento, Float reloj, GestorLlegadas gestorLlegadas, Boolean esLibrePlaya, GestorSectores gestorSectores, GestorAutos gestorAutos, VentanillaCobro ventanillaCobro, Long contadorAutosNoAtendidos, Float acumuladorGanancia, Float acumuladorTiempoEstacionamiento) {
        this.nroIteracion = nroIteracion;
        this.evento = evento;
        this.reloj = reloj;
        this.gestorLlegadas = gestorLlegadas;
        this.esLibrePlaya = esLibrePlaya;
        this.gestorSectores = gestorSectores;
        this.gestorAutos = gestorAutos;
        this.ventanillaCobro = ventanillaCobro;
        this.contadorAutosNoAtendidos = contadorAutosNoAtendidos;
        this.acumuladorGanancia = acumuladorGanancia;
        this.acumuladorTiempoEstacionamiento = acumuladorTiempoEstacionamiento;
        // creo q este metodo esta al vicio pero bueno que le vamos a hacer
    }
}
