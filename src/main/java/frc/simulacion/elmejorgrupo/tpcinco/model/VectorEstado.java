package frc.simulacion.elmejorgrupo.tpcinco.model;

import frc.simulacion.elmejorgrupo.tpcinco.util.CustomPair;

import java.util.List;

import static frc.simulacion.elmejorgrupo.tpcinco.generadores.GeneradorRungeKutta.calcularYDevolverMatriz;

public class VectorEstado {

    private Long nroIteracion;
    private Evento evento;
    private Float reloj = 0f;
    private Boolean esLibrePlaya;
    private GestorLlegadas gestorLlegadas;
    private GestorSectores gestorSectores;
    private GestorAutos gestorAutos;
    private VentanillaCobro ventanillaCobro;
    // matriz del RK
    private List<float[]> matriz;
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
        } else if (nuevoVec.evento == Evento.FIN_COBRO){
            operarFinCobro(nuevoVec);
        }

        return nuevoVec;

    }

    private static void operarFinCobro(VectorEstado nuevoVec) {
        // hay dos caminos:
        // si la cola tiene items, entonces hay que descontar la cola y calcular el siguiente fin de cobro
        //      y los estados de LOS autos
        // si la cola no tiene autos, entonces hay que actualizar el estado de la zona de cobro
        //      y el estado del auto

        // si la cola no tiene autos / camino facil
        if (nuevoVec.ventanillaCobro.conseguirAutosEnCola() == 0L){
            // actualizar estado auto
            // buscar auto en zona cobro y actualizar
            Auto aut = nuevoVec.gestorAutos.buscarAutoFinalizaCobro();
            aut.getEstadoAuto().setEstadoAuto(EstadoAuto.FINALIZADO);

            // actualizar estado zona de cobro
            nuevoVec.ventanillaCobro.setEstaLibre(true);
        } else {// camino dificil
            // actualizar estado auto
            // buscar auto en zona cobro y actualizar
            Auto aut = nuevoVec.gestorAutos.buscarAutoFinalizaCobro();
            aut.getEstadoAuto().setEstadoAuto(EstadoAuto.FINALIZADO);

            // obtener id de prox auto
            Long idNuevoAuto = nuevoVec.ventanillaCobro.conseguirAuto();

            Auto nuevoAut = nuevoVec.gestorAutos.buscarAutoPorId(idNuevoAuto);

            // calcular runge kutta y actualizar el estado del auto
            Float paramD = (nuevoAut.getTipoAuto() == TiposAuto.GRANDE) ? 180f : 130f;
            Float paramC = nuevoVec.ventanillaCobro.conseguirAutosEnCola().floatValue();
            List<float[]> matriz = calcularYDevolverMatriz(paramD, paramC);
            // ahora hay que buscar el resultado en la matriz
            // eso nos va a dar el Xn que apunta al objetivo
            // el Xn+1 va a estar en el anteultimo elemento de la ultima fila de la lista.
            Float Xn = matriz.getLast()[7];
            nuevoVec.ventanillaCobro.setValorRungeKutta(Xn);
            nuevoVec.ventanillaCobro.setEstaLibre(false);
            nuevoVec.ventanillaCobro.setFinCobroAuto(nuevoVec.reloj+Xn);
            nuevoAut.getEstadoAuto().setEstadoAuto(EstadoAuto.EN_COBRO);


        }


    }

    private static void operarFinEstacionamiento(VectorEstado nuevoVec) {
        // hay que buscar el auto que acaba de finalizar y cambiar el estado
        // despues de eso hay que actualizar el estado del sector
        // hay que mandarle el auto a la zona de cobro
        //      si la zona de cobro esta ocupada, usar la cola
        //      si esta libre, actualizar su estado, actualizar la hora de fin de cobro de auto

        Auto aut = nuevoVec.gestorAutos.buscarAutoFinalizaEstacionamiento(nuevoVec.reloj);
        // el proximo estado del auto depende del estado de la cola
        if (nuevoVec.ventanillaCobro.getEstaLibre()){
            // calcular runge kutta y actualizar el estado del auto
            Float paramD = (aut.getTipoAuto() == TiposAuto.GRANDE) ? 180f : 130f;
            Float paramC = nuevoVec.ventanillaCobro.conseguirAutosEnCola().floatValue();
            List<float[]> matriz = calcularYDevolverMatriz(paramD, paramC);
            // ahora hay que buscar el resultado en la matriz
            // eso nos va a dar el Xn que apunta al objetivo
            // el Xn+1 va a estar en el anteultimo elemento de la ultima fila de la lista.
            Float Xn = matriz.getLast()[7];
            nuevoVec.ventanillaCobro.setValorRungeKutta(Xn);
            nuevoVec.ventanillaCobro.setEstaLibre(false);
            nuevoVec.ventanillaCobro.setFinCobroAuto(nuevoVec.reloj+Xn);
            aut.getEstadoAuto().setEstadoAuto(EstadoAuto.EN_COBRO);

        } else {
            // esto si esta ocupado: mandarlo a la cola
            nuevoVec.ventanillaCobro.agregarAuto(aut);
            // poner que el auto esta en cola
            aut.getEstadoAuto().setEstadoAuto(EstadoAuto.EN_COLA_COBRO);
        }

        // actualizar estado sector -> ponerlo como Libre
        Long idSector = aut.getEstadoAuto().getIdSector();
        nuevoVec.gestorSectores.liberarSector(idSector);

        nuevoVec.esLibrePlaya = true; // aca actualizamos para que se libere la playa

        // creo que eso es todo
    }

    //TODO
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
            Float horaAutoMasCercana = this.gestorAutos.horaMasCercana(this.reloj);

            //
            Float horaVentanilla = ventanillaCobro.getFinCobroAuto();
            if (horaVentanilla > horaAutoMasCercana){
                nextHora = horaAutoMasCercana;
                nextEvento = Evento.FIN_ESTACIONAMIENTO;
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
            CustomPair<Boolean, Float> parRespuesta = gestorAutos.horaMasCercanaCobroOEstacionamiento(this.reloj);

            if(horaVentanilla < parRespuesta.getValue() && horaVentanilla < this.reloj){
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
