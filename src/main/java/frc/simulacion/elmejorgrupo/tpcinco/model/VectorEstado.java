package frc.simulacion.elmejorgrupo.tpcinco.model;

import frc.simulacion.elmejorgrupo.tpcinco.DTO.AutoDTO;
import frc.simulacion.elmejorgrupo.tpcinco.ElementoListaDTO;
import frc.simulacion.elmejorgrupo.tpcinco.util.CustomPair;
import frc.simulacion.elmejorgrupo.tpcinco.util.SectorDTO;

import java.util.List;
import java.util.stream.Collectors;

import static frc.simulacion.elmejorgrupo.tpcinco.generadores.GeneradorRungeKutta.calcularYDevolverMatriz;

public class VectorEstado {

    private Long nroIteracion;
    private Evento evento;
    private Float reloj = 0f;
    private Boolean esLibrePlaya;
    private GestorLlegadas gestorLlegadas;
    private GestorSectores gestorSectores;
    private GestorAutos gestorAutos;
    private VentanillaCobro ventanillaCobro ;
    // matriz del RK
    private List<float[]> matriz;
    // TODO otras cosas
    private Float dineroACobrar;

    private Long contadorAutosNoAtendidos = 0L;
    private Float acumuladorGanancia = 0f;
    private Float acumuladorTiempoEstacionamiento = 0f;

    public ElementoListaDTO toDTO() {
        ElementoListaDTO dto = new ElementoListaDTO();

        // Mapeo de atributos simples
        dto.setNroIteracion(this.nroIteracion);
        dto.setEvento(this.evento != null ? this.evento.name() : null);
        dto.setReloj(this.reloj);
        dto.setEsLibre(this.esLibrePlaya);
        dto.setMatriz(this.matriz);
        dto.setDineroACobrar(this.dineroACobrar);
        dto.setContadorAutosNoAtendidos(this.contadorAutosNoAtendidos);
        dto.setAcumuladorGanancia(this.acumuladorGanancia);
        dto.setAcumuladorTiempoEstacionamiento(this.acumuladorTiempoEstacionamiento);

        // Mapeo de GestorLlegadas
        if (this.gestorLlegadas != null) {
            dto.setRndLlegadaAuto(this.gestorLlegadas.getRndLlegadaAuto());
            dto.setTiempoLlegadaAuto(this.gestorLlegadas.getTiempoLlegadaAuto());
            dto.setHoraLlegadaAuto(this.gestorLlegadas.getHoraLlegadaAuto());
            dto.setRndTipoAuto(this.gestorLlegadas.getRndTipoAuto());
            dto.setTipoAuto(this.gestorLlegadas.getTipoAuto() != null ? this.gestorLlegadas.getTipoAuto().name() : null);
            dto.setRndTiempo(this.gestorLlegadas.getRndTiempo());
            dto.setTiempoEstacionamiento(this.gestorLlegadas.getTiempoEstacionamiento());
        }

        // Mapeo de VentanillaCobro
        if (this.ventanillaCobro != null) {
            dto.setEstaLibre(this.ventanillaCobro.getEstaLibre());
            dto.setValorRungeKutta(this.ventanillaCobro.getValorRungeKutta());
            dto.setFinCobroAuto(this.ventanillaCobro.getFinCobroAuto());
        }

        // Mapeo de GestorSectores a SectorDTO
        if (this.gestorSectores != null && this.gestorSectores.getSectores() != null) {
            List<SectorDTO> sectoresDTO = this.gestorSectores.getSectores().stream()
                    .map(sector -> {
                        SectorDTO sectorDTO = new SectorDTO();
                        sectorDTO.setId(sector.getIdSector());
                        sectorDTO.setEsLibre(sector.getEsLibre());
                        return sectorDTO;
                    })
                    .collect(Collectors.toList());
            dto.setSectorDTOS(sectoresDTO);
        }

        // Mapeo de GestorAutos a AutoDTO
        if (this.gestorAutos != null && this.gestorAutos.getAutos() != null) {
            List<AutoDTO> autosDTO = this.gestorAutos.getAutos().stream()
                    .map(auto -> {
                        AutoDTO autoDTO = new AutoDTO();
                        autoDTO.setId(auto.getId());
                        autoDTO.setTipoAuto(auto.getTipoAuto() != null ? auto.getTipoAuto().name() : null);
                        autoDTO.setEstadoAuto(auto.getEstadoAuto() != null ? auto.getEstadoAuto().getEstadoAuto().name() : null);
                        autoDTO.setHoraFinEstacionamiento(auto.getHoraFinEstado());
                        autoDTO.setIdSector(auto.getEstadoAuto() != null ? auto.getEstadoAuto().getIdSector() : null);
                        return autoDTO;
                    })
                    .collect(Collectors.toList());
            dto.setAutoDTOS(autosDTO);
            dto.setAutosEnCola((long) this.ventanillaCobro.conseguirAutosEnCola()); //
        }

        return dto;
    }

    // constructor vacio
    public VectorEstado() {
    }

    // metodo para conseguir una lista que visualice el vector
    // orden: datos iniciales, gestor llegada, ventanilla de cobro,
    // gestor sectores, gestor autos
    /// HACER ESTO PORQUE ES IMPORTANTE
    public String generarTextoPrueba(){
        // generamos un texto y le vamos adosando los valores que queremos...
        StringBuilder sb = new StringBuilder();
        sb.append("|| ");
        sb.append(this.nroIteracion);
        sb.append("|| ");
        sb.append(this.evento.name());
        sb.append("|| ");
        sb.append(this.reloj);
        sb.append("|| ");
        sb.append(this.esLibrePlaya);
        sb.append("\n|| GESTORLLEGADAS");
        sb.append("|| ");
        this.gestorLlegadas.devolverDatosTexto(sb);
        sb.append("\n|| ZONACOBRO");
        sb.append("|| ");
        this.ventanillaCobro.devolverDatosTexto(sb);
        sb.append("\n|| GESTORSECTORES");
        sb.append("|| ");
        this.gestorSectores.devolverDatosTexto(sb);
        sb.append("\n|| GESTORAUTOS");
        sb.append("|| ");
        this.gestorAutos.devolverDatosTexto(sb);
        sb.append("\n|| ACUMULADORES || ");
        sb.append(this.contadorAutosNoAtendidos);
        return sb.toString();
    }

    // metodo para conseguir el vector inicial inicial
    public static VectorEstado obtenerVectorInicial() {
        VectorEstado vecInicial = new VectorEstado();

        vecInicial.nroIteracion = 1L;
        vecInicial.evento = Evento.INICIO_SIMULACION;
        vecInicial.reloj = 0f;

        // parte de los generadores
        vecInicial.gestorLlegadas = new GestorLlegadas();
        vecInicial.gestorLlegadas.calcularLlegada(vecInicial.reloj);

        vecInicial.esLibrePlaya = true;

        vecInicial.gestorSectores = new GestorSectores();
        vecInicial.gestorSectores.inicializar();

        vecInicial.ventanillaCobro = new VentanillaCobro();
        vecInicial.ventanillaCobro.inicializar();

        vecInicial.gestorAutos = new GestorAutos();
        vecInicial.gestorAutos.inicializar();
        return vecInicial;
    }

    public static VectorEstado obtenerCopia(VectorEstado vec){
        VectorEstado nuevoVec = new VectorEstado();

        // valores primitivos
        nuevoVec.nroIteracion = vec.nroIteracion;
        nuevoVec.evento = vec.evento;
        nuevoVec.reloj = vec.reloj;
        nuevoVec.esLibrePlaya = vec.esLibrePlaya;

        // acumuladores
        nuevoVec.contadorAutosNoAtendidos = vec.contadorAutosNoAtendidos;
        nuevoVec.acumuladorGanancia = vec.acumuladorGanancia;
        nuevoVec.acumuladorTiempoEstacionamiento = vec.acumuladorTiempoEstacionamiento;
        nuevoVec.dineroACobrar = vec.dineroACobrar;

        // valores por referencia
        nuevoVec.gestorLlegadas = vec.gestorLlegadas.clone();

        nuevoVec.gestorSectores = vec.gestorSectores.clone();

        nuevoVec.ventanillaCobro = vec.ventanillaCobro.clone();

        nuevoVec.gestorAutos = vec.gestorAutos.clone();

        // esto es para optimizar
        nuevoVec.gestorAutos.purgar();


        return nuevoVec;
    }

    public static VectorEstado operarNuevaLlegada(VectorEstado vector){

        // Hay que armar el auto
        Auto auto = new Auto();
        TiposAuto nuevoTipo = vector.gestorLlegadas.getTipoAuto();

        auto.setTipoAuto(nuevoTipo);
        auto.setHoraLlegada(vector.reloj);
        // hay que ver si la playa esta llena
        if (vector.gestorSectores.estaLleno()){
            //System.out.println("XD");
        }

        if (!vector.esLibrePlaya){
            // si la playa esta llena el auto se va -> modificar el auto
            auto.setEstadoAuto(new WrapperEstadoAuto(EstadoAuto.NO_ATENDIDO));
            vector.gestorAutos.agregarAuto(auto);
            // actualizar contador
            vector.contadorAutosNoAtendidos++;
            //System.out.println("aca hay uno sin atender");
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
            if (!vector.esLibrePlaya){
                //System.out.println(vector.nroIteracion);
            }
            // agregar el auto al gestor
            vector.gestorAutos.agregarAuto(auto);

        }

        // se calcula la nueva llegada
        vector.gestorLlegadas.calcularLlegada(vector.reloj);

        return vector;
        /*
        * TODO
        * Calcular el resto de acumuladores si es necesario
        * */
    }

    public static VectorEstado predecirProximoVector(VectorEstado prev){
        VectorEstado nuevoVec;

        // copiamos y hacemos clonacion del vector anterior.
        nuevoVec = obtenerCopia(prev);

        CustomPair<Evento, Float> proxEvento = prev.decidirProximoEvento();
        nuevoVec.nroIteracion = prev.nroIteracion+1;
        nuevoVec.reloj = proxEvento.getValue();
        nuevoVec.evento = proxEvento.getKey();


        // calcular acumulado de tiempo ocupado de la playa
        if (!prev.esLibrePlaya){
            nuevoVec.acumuladorTiempoEstacionamiento += nuevoVec.reloj - prev.reloj;
        }

        System.out.println(nuevoVec.nroIteracion);

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
            // actualizar acumulador ganancia
            nuevoVec.acumuladorGanancia += nuevoVec.dineroACobrar;
            nuevoVec.dineroACobrar = 0f;

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
            nuevoVec.matriz = matriz;

            // ahora hay que buscar el resultado en la matriz
            // eso nos va a dar el Xn que apunta al objetivo
            // el Xn+1 va a estar en el anteultimo elemento de la ultima fila de la lista.
            Float Xn = matriz.get(matriz.size()-1)[7];
            nuevoVec.ventanillaCobro.setValorRungeKutta(Xn);
            nuevoVec.ventanillaCobro.setEstaLibre(false);
            nuevoVec.ventanillaCobro.setFinCobroAuto(nuevoVec.reloj+Xn);
            nuevoAut.getEstadoAuto().setEstadoAuto(EstadoAuto.EN_COBRO);


            // aca el bugfix es facil:
            // hay que poner que el dinero se guarde como un atributo del vector de estado, despues en cada fin de cobro sumar el
            // dinero que quedo antes


            nuevoVec.acumuladorGanancia += nuevoVec.dineroACobrar;
            /// TODO PROGRAMAR EL ACUMULADOR DE PLATA
            float dinero = 0f;
            float diferencia = aut.getHoraFinEstado() - aut.getHoraLlegada();
            if (aut.getTipoAuto() == TiposAuto.PEQUENIO){
                dinero = (diferencia / 60f) * 300f;
            } else if (aut.getTipoAuto() == TiposAuto.GRANDE){
                dinero = (diferencia / 60f) * 500f;
            } else {
                dinero = (diferencia / 60f) * 500f;
            }
            nuevoVec.dineroACobrar = dinero;

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

            nuevoVec.matriz = matriz;
            // ahora hay que buscar el resultado en la matriz
            // eso nos va a dar el Xn que apunta al objetivo
            // el Xn+1 va a estar en el anteultimo elemento de la ultima fila de la lista.
            Float Xn = matriz.get(matriz.size()-1)[7];
            nuevoVec.ventanillaCobro.setValorRungeKutta(Xn);
            nuevoVec.ventanillaCobro.setEstaLibre(false);
            nuevoVec.ventanillaCobro.setFinCobroAuto(nuevoVec.reloj+Xn);
            aut.getEstadoAuto().setEstadoAuto(EstadoAuto.EN_COBRO);



            /// TODO PROGRAMAR EL ACUMULADOR DE PLATA
            /// necesito el auto, el acumulador, hora de inicio y hora de fin
            float dinero = 0f;
            float diferencia = aut.getHoraFinEstado() - aut.getHoraLlegada();
            if (aut.getTipoAuto() == TiposAuto.PEQUENIO){
                dinero = (diferencia / 60f) * 300f;
            } else if (aut.getTipoAuto() == TiposAuto.GRANDE){
                dinero = (diferencia / 60f) * 500f;
            } else {
                dinero = (diferencia / 60f) * 500f;
            }
            nuevoVec.dineroACobrar = dinero;


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
            //System.out.println("ZONA DECISION EVENTO 1");

        } else if (this.ventanillaCobro.getEstaLibre() && !this.gestorAutos.estaVacia()){
            // si no hay autos en ventanilla pero si llegaron autos antes
            // puede haber fin_estacionamiento y nuevallegada
            // de los autos

            // fin de estacionamiento mas cercano
            Float horaAutoMasCercana = this.gestorAutos.horaMasCercana(this.reloj);

            // llegada mas cercana
            Float horaLlegadaAuto = gestorLlegadas.getHoraLlegadaAuto();
            if (horaLlegadaAuto < horaAutoMasCercana){
                nextHora = horaLlegadaAuto;
                nextEvento = Evento.LLEGADA_AUTO;
            } else {
                nextHora = horaAutoMasCercana;
                nextEvento = Evento.FIN_ESTACIONAMIENTO;
            }
            //System.out.println("ZONA DECISION EVENTO 2");

        } else if (!this.ventanillaCobro.getEstaLibre() && this.gestorAutos.estaVacia()){
            // si hay autos en ventanilla pero no existen autos ?????
            // esto no debe pasar
            System.out.print("ERROR FATAL EN LA SIGUIENTE ITERACION: ");
            System.out.println(this.nroIteracion);

        } else {
            // si no esta libre la ventanilla pero HAY autos
            // hay que tener en cuenta =
            // llegada prox auto,
            // fin de cobro
            /// fin de cobro estacionamiento
            Float horaVentanilla = ventanillaCobro.getFinCobroAuto();

            /// fin de estacionamiento
            Float finEstacionamiento = gestorAutos.horaMasCercana(this.reloj);

            /// nueva llegada
            Float horaNuevaLlegada = gestorLlegadas.getHoraLlegadaAuto();


            if(horaVentanilla < finEstacionamiento && horaVentanilla < horaNuevaLlegada && horaVentanilla > this.reloj){
                nextHora = horaVentanilla;
                nextEvento = Evento.FIN_COBRO;
            } else if (finEstacionamiento < horaNuevaLlegada && finEstacionamiento > this.reloj) {
                nextHora = finEstacionamiento;
                nextEvento = Evento.FIN_ESTACIONAMIENTO;
            } else {
                nextHora = horaNuevaLlegada;
                nextEvento = Evento.LLEGADA_AUTO;
            }
            //System.out.println("ZONA DECISION EVENTO 4");
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

    public Long getNroIteracion() {
        return nroIteracion;
    }

    public void setNroIteracion(Long nroIteracion) {
        this.nroIteracion = nroIteracion;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Float getReloj() {
        return reloj;
    }

    public void setReloj(Float reloj) {
        this.reloj = reloj;
    }

    public Boolean getEsLibrePlaya() {
        return esLibrePlaya;
    }

    public void setEsLibrePlaya(Boolean esLibrePlaya) {
        this.esLibrePlaya = esLibrePlaya;
    }

    public GestorLlegadas getGestorLlegadas() {
        return gestorLlegadas;
    }

    public void setGestorLlegadas(GestorLlegadas gestorLlegadas) {
        this.gestorLlegadas = gestorLlegadas;
    }

    public GestorSectores getGestorSectores() {
        return gestorSectores;
    }

    public void setGestorSectores(GestorSectores gestorSectores) {
        this.gestorSectores = gestorSectores;
    }

    public GestorAutos getGestorAutos() {
        return gestorAutos;
    }

    public void setGestorAutos(GestorAutos gestorAutos) {
        this.gestorAutos = gestorAutos;
    }

    public VentanillaCobro getVentanillaCobro() {
        return ventanillaCobro;
    }

    public void setVentanillaCobro(VentanillaCobro ventanillaCobro) {
        this.ventanillaCobro = ventanillaCobro;
    }

    public List<float[]> getMatriz() {
        return matriz;
    }

    public void setMatriz(List<float[]> matriz) {
        this.matriz = matriz;
    }

    public Long getContadorAutosNoAtendidos() {
        return contadorAutosNoAtendidos;
    }

    public void setContadorAutosNoAtendidos(Long contadorAutosNoAtendidos) {
        this.contadorAutosNoAtendidos = contadorAutosNoAtendidos;
    }

    public Float getAcumuladorGanancia() {
        return acumuladorGanancia;
    }

    public void setAcumuladorGanancia(Float acumuladorGanancia) {
        this.acumuladorGanancia = acumuladorGanancia;
    }

    public Float getAcumuladorTiempoEstacionamiento() {
        return acumuladorTiempoEstacionamiento;
    }

    public void setAcumuladorTiempoEstacionamiento(Float acumuladorTiempoEstacionamiento) {
        this.acumuladorTiempoEstacionamiento = acumuladorTiempoEstacionamiento;
    }

    public Float getDineroACobrar() {
        return dineroACobrar;
    }

    public void setDineroACobrar(Float dineroACobrar) {
        this.dineroACobrar = dineroACobrar;
    }
}
