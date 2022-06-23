package Controllers;

import Models.*;
import Persistents.*;
import Views.PantallaRegistrarTurnoRecursoTecnologico;
import Views.TablaRecursosTec;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;


// Patron controlador
public class GestorRegTurnoRecTec {

    private final PantallaRegistrarTurnoRecursoTecnologico controller;
    //Lista de tipos de recursos tecnologicos.
    private List<TipoRecursoTecnologico> tipoRecTecnologicos;
    //Lista de nombres de tipos de recursostecnologicos.
    //private List<String> nombreTipoRT;
    //Lista de recursos tecnologicos activos reservables
    private List<RecursoTecnologico> recursosTecActivosReservables;
    private RecursoTecnologico recursoTecnologicoSeleccionado = null;
    private HashMap<Estado, Turno> turnosRecTecnologicoSeleccionado;
    private LocalDateTime fechaHoraActual;
    private Usuario usuarioActual;
    private Sesion sesionActual;
    private PersonalCientifico cientificoLogueado;
    private CentroDeInvestigacion centroDeInvestigacionRecSeleccionad;


    public GestorRegTurnoRecTec(PantallaRegistrarTurnoRecursoTecnologico controller) {
        this.controller = controller;
        /*
        Se corrigio: no se deberian crear en el constructor del controlador.
            Lo del usuario no respetaba el diagrama.
            La sesionAcual se enviara por parametro caundo se ejecute el metodo nuevaReservaTurnoTecnologico.
            en este caso lo vamos a harcodear en dicho metodo.
        */

        /*
          Usuario uisuarioLogueado = new Usuario();
           sesionActual = new Sesion(LocalDateTime.parse("2022-03-03T01:00:02"));
         */
    }

    public void nuevaReservaTurnoDeRecursoTecnologico() throws Exception {
        sesionActual = new Sesion(LocalDateTime.parse("2022-03-03T01:00:02"));

        controller.pedirSeleccionTipoRecurso(buscarTipoRecurso());
    }

    private HashMap<String, TipoRecursoTecnologico> buscarTipoRecurso() throws Exception {
        //Obtenemos todos los tipos de recursos tecnologicos.
        tipoRecTecnologicos = new TipoRecursoTecnologico_DAO().listar();
        HashMap<String, TipoRecursoTecnologico> tiposRecTecnologicos = new HashMap<>();

        for (TipoRecursoTecnologico tipoRT : tipoRecTecnologicos) {
            tiposRecTecnologicos.put(tipoRT.getNombre(), tipoRT);
        }
        return tiposRecTecnologicos;
    }

    public void tomarSeleccionTipoRecurso(TipoRecursoTecnologico tipoRecursoTecnologico) throws Exception {

        //Obtenemos los recursos tecnologicos de acuerdo al tipo de recurso tecnologico seleccionado
        recursosTecActivosReservables = obtenerRecursoTecnologicoActivo(tipoRecursoTecnologico);

        HashMap<String[], RecursoTecnologico> reTecHasMap = new HashMap<>();

        for (RecursoTecnologico rt : recursosTecActivosReservables) {
           String datos[] = rt.mostrarDatosRecursoTecnologico(new CentroDeInvestigacion_DAO().obtenerDatos(rt.getNumeroRT()), new Marca_DAO().obtenerDatos(rt.getModelo().getNombre()));
           reTecHasMap.put(datos, rt);
        }

        LinkedHashMap<String[], RecursoTecnologico> listaOrdenadaRecursoTec = agruparPorCentroInvestigacion(reTecHasMap);
        agruparPorCentroInvestigacion(listaOrdenadaRecursoTec);
        definirColorRecursoTecnologico(listaOrdenadaRecursoTec);
        controller.pedirSeleccionRecursoTecnologico(listaOrdenadaRecursoTec);
    }

    private LinkedHashMap<String[], RecursoTecnologico> agruparPorCentroInvestigacion(HashMap<String[], RecursoTecnologico> recursosTec) {
        //ordeno por fechas y turnos
        List<Map.Entry<String[], RecursoTecnologico>> list = new ArrayList<>(recursosTec.entrySet());
        list.sort(Map.Entry.comparingByKey((o1, o2) -> 1 * o1[4].compareTo(o2[4])));
        //arreglo que mantiene el orden de insercion
        LinkedHashMap<String[], RecursoTecnologico> recursosAgrupadosXCtroInvest = new LinkedHashMap<>();
        list.forEach((i) -> recursosAgrupadosXCtroInvest.put(i.getKey(), i.getValue()));
        return recursosAgrupadosXCtroInvest;
    }

    private void definirColorRecursoTecnologico(LinkedHashMap<String[], RecursoTecnologico> recursoTecnologicos) {

        recursoTecnologicos.forEach(
                (k, v) -> {
                    switch (k[1]) {
                        case "Disponible":
                            k[1] = "BLUE";
                            break;
                        case "En Mantenimiento Preventivo":
                            k[1] = "GREEN";
                            break;
                        case "En Mantenimineto Correctivo":
                            k[1] = "GRAY";
                            break;

                    }
                }
        );

    }

    public List<RecursoTecnologico> obtenerRecursoTecnologicoActivo(TipoRecursoTecnologico tipoRecursoTecnologico) throws Exception {
        RecursoTecnologico_DAO recursoTecnologico_dao = new RecursoTecnologico_DAO();
        List<RecursoTecnologico> recursosTecActivosReservables = new ArrayList<>();

        for (RecursoTecnologico rt : recursoTecnologico_dao.listar()) {
            if (rt.esDeTipoRecursoTecnologicoSeleccionado(tipoRecursoTecnologico)) {
                if (rt.esReservable()) {
                    // si el recurso tecnologico es reservable lo agrego a la lista.
                    recursosTecActivosReservables.add(rt);
                }
            }
        }
        return recursosTecActivosReservables;
    }

    public void recursoTecnologicoSeeccionado(TablaRecursosTec trecSelec) throws SQLException {
        for (RecursoTecnologico recTec : recursosTecActivosReservables) {
            if (recTec.getNumeroRT() == trecSelec.getIdRec())
                recursoTecnologicoSeleccionado = recTec;
        }

        if (verificarCentroInvestigacionCientificoLogueado()) {
            obtenerTurnosRecursoTecnologogicoSeleccionado();
            obtenerFechaHoraActual();
        } else {
            controller.avisarflujoA1();
        }
    }

    private boolean verificarCentroInvestigacionCientificoLogueado() throws SQLException {
        PersonalCientifico personalCientificoLogeuado = obtenerCientificoLogueado();
        PersonalCientifico personalCientificoLogeuado2 = new PersonalCientifico(2, "sdsdf", "asdfdf", 32999000, "sdfsdf", "asdfsd");
        centroDeInvestigacionRecSeleccionad = new CentroDeInvestigacion_DAO().obtenerDatos(recursoTecnologicoSeleccionado.getNumeroRT());
        List<AsignacionCientificoDelCI> asignacionesCientDelCI = new ArrayList<>();

        asignacionesCientDelCI.add(new AsignacionCientificoDelCI(LocalDateTime.parse("2022-02-02T00:00"), LocalDateTime.parse("2022-02-03T00:00"), personalCientificoLogeuado));
        asignacionesCientDelCI.add(new AsignacionCientificoDelCI(LocalDateTime.parse("2022-02-02T00:00"), null, personalCientificoLogeuado));
        asignacionesCientDelCI.add(new AsignacionCientificoDelCI(LocalDateTime.parse("2022-02-02T00:00"), null, personalCientificoLogeuado));

//        asignacionesCientDelCI.add(new AsignacionCientificoDelCI(LocalDateTime.parse("2022-02-02T00:00"), LocalDateTime.parse("2022-02-03T00:00"),new PersonalCientifico(2,"Roberto","Gonsalez",33666009, "rces0365@gmail.com","rces0365@gmail.com")));
        //      asignacionesCientDelCI.add(new AsignacionCientificoDelCI(LocalDateTime.parse("2022-02-02T00:00"), null, new PersonalCientifico(2,"Roberto","Gonsalez",33666009, "rces0365@gmail.com","rces0365@gmail.com")));
        //    asignacionesCientDelCI.add(new AsignacionCientificoDelCI(LocalDateTime.parse("2022-02-02T00:00"), null, new PersonalCientifico(2,"Roberto","Gonsalez",33666009, "rces0365@gmail.com","rces0365@gmail.com")));

        centroDeInvestigacionRecSeleccionad.setAsignacionesCientificoDelCIs(asignacionesCientDelCI);

        return recursoTecnologicoSeleccionado.esCientificoDeTuCentroInvestigacion(centroDeInvestigacionRecSeleccionad, personalCientificoLogeuado);
    }

    //deberia devolver cientifico
    private PersonalCientifico obtenerCientificoLogueado() throws SQLException {
        sesionActual.setUsuario(new Usuario_DAO().getUser());
        return this.sesionActual.obtenerCientifico(new PersonalCientifico(1, "Roberto", "Gonsalez", 33666009, "rces0365@gmail.com", "rces0365@gmail.com"));
    }

    public List<TipoRecursoTecnologico> getTipo() {
        return tipoRecTecnologicos;
    }

    private void obtenerTurnosRecursoTecnologogicoSeleccionado() {
        obtenerFechaHoraActual();
        turnosRecTecnologicoSeleccionado = recursoTecnologicoSeleccionado.mostrarTurnos(fechaHoraActual);
        LinkedHashMap<Estado, Turno> turnosOrdenadosYAgrupados = agruparYOrdenar();
        controller.pedirSeleccionTurno(turnosOrdenadosYAgrupados);
    }

    private void obtenerFechaHoraActual() {
        fechaHoraActual = LocalDateTime.now();
    }

    private LinkedHashMap<Estado, Turno> agruparYOrdenar() {
        //ordeno por fechas y turnos
        List<Map.Entry<Estado, Turno>> list = new ArrayList<>(turnosRecTecnologicoSeleccionado.entrySet());
        list.sort(Map.Entry.comparingByValue((o1, o2) -> 1 * o1.getFechaHoraInicio().compareTo(o2.getFechaHoraInicio())));
        //arreglo que mantiene el orden de insercion
        LinkedHashMap<Estado, Turno> listaOrdenadaTurnos = new LinkedHashMap<>();
        list.forEach((i) -> listaOrdenadaTurnos.put(i.getKey(), i.getValue()));
        return listaOrdenadaTurnos;
    }

    private Turno turnoSeleccionadoPorUsuario;

    public void turnoSeleccionado(Turno tunoSeleccionado) {
        turnoSeleccionadoPorUsuario = tunoSeleccionado;
        controller.pedirConfirmacionReserva();
    }

    public void reservarRecursoTecnologico() throws SQLException {
        generarReservaRecursoTecnologico();
    }

    private void generarReservaRecursoTecnologico() throws SQLException {
        Estado_DAO estado_dao = new Estado_DAO();
        List<Estado> estados = estado_dao.listar();
        Estado estadoReservado = new Estado();

        for (Estado est : estados) {
            if (est.esAmbitoTurno())
                if (est.esReservado())
                    estadoReservado = est;
        }
        recursoTecnologicoSeleccionado.reservar(turnoSeleccionadoPorUsuario, centroDeInvestigacionRecSeleccionad, fechaHoraActual, estadoReservado, cientificoLogueado);
        generarMail();
    }

    private void generarMail() {
        String mensajeMail =   "Turno reservado con exito. \n " +
                "Recurso tecnologico: "+ recursoTecnologicoSeleccionado.getModelo().getNombre()+"\n"+
                "Intitucion: " +centroDeInvestigacionRecSeleccionad.getNombre()+"\n"+
                "Fecha: "+turnoSeleccionadoPorUsuario.getFechaHoraInicio().toLocalDate()+ "\nHora:"+turnoSeleccionadoPorUsuario.getFechaHoraInicio().toLocalTime()+ " a "+ turnoSeleccionadoPorUsuario.getFechaHoraFin().toLocalTime() ;
        enviarEmail( mensajeMail);
    }

    private void enviarEmail(String mensajeMail) {
        // no deberia ir a la pantalla, solo es para mostrar como se veria el cuerpo del mail
        System.out.println(mensajeMail);
    }
}
