package Controllers;

import Models.*;
import Persistents.*;
import Views.Controller;
import Views.TablaRecursosTec;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;


// Patron controlador
public class GestorRegTurnoRecTec {

    private final Controller controller;
    //Lista de tipos de recursos tecnologicos.
    private List<TipoRecursoTecnologico> tipoRecTecnologicos;
    //Lista de nombres de tipos de recursostecnologicos.
    private List<String> nombreTipoRT;
    //Lista de recursos tecnologicos activos reservables
    private  List<RecursoTecnologico> recTecActivosReservables;
    private RecursoTecnologico recursoTecnologicoSeleccionado = null;
    private List<List<String>> turnosRecTecnologicoSeleccionado;
    private LocalDateTime fechaHoraActual;
    private Usuario usuarioActual;
    private Sesion sesionActual;
    private PersonalCientifico cientificoLogueado;
    private CentroDeInvestigacion centroDeInvestigacionRecSeleccionad;


    public GestorRegTurnoRecTec(Controller controller) {
        this.controller = controller;
        usuarioActual = new Usuario("1234", "neuen" , true);
        sesionActual = new Sesion(LocalDateTime.parse("2022-03-03T01:00:02"), usuarioActual); }

    public void nuevaReservaTurnoDeRecursoTecnologico()  {
        try {
            buscarTipoRecurso();
        } catch (Exception e) {
            e.printStackTrace();
        }
        controller.pedirSeleccionTipoRecurso(this.nombreTipoRT);
    }

    private void buscarTipoRecurso() throws Exception {
        //Obtenemos todos los tipos de recursos tecnologicos.
        tipoRecTecnologicos = new TipoRecursoTecnologico_DAO().listar();
        // Obtenemos los nombres de los tipos recursos tecnologicos.
        nombreTipoRT = new ArrayList<>();
        for(TipoRecursoTecnologico trc : tipoRecTecnologicos){
            nombreTipoRT.add(trc.getNombre());
        }
    }

    public void tomarSeleccionTipoRecurso(String tipoRecursoTecnologico) throws Exception {

        //Obtenemos los recursos tecnologicos de acuerdo al tipo de recurso tecnologico seleccionado
        obtenerRecursoTecnologicoActivo(tipoRecursoTecnologico);

        // creo una lista de objetos tabla para que se adapte a la tabla de la vista.
        List<TablaRecursosTec> tablaRecursosTecs = new ArrayList<>();

        //
        // agrupar despues del for.
        //
        //Agrupamos recursos por centro de investigacion.
        agruparPorCentroInvestigacion(tablaRecursosTecs);

        /*Recorro la lista de recursos tecnologicos reservables y obtengo los datos a mostrar y voy
        creando objetos tabla y agregandolos a la lista
         */

        for( RecursoTecnologico rt : recTecActivosReservables) {
            // envio por parametro el centro de investigacion y la marca, ambos materializados por sus respectivos DAO. para obtener sus datos de quien corresponda
            String datos[] = rt.mostrarDatosRecursoTecnologico(new CentroDeInvestigacion_DAO().obtenerDatos(rt.getNumeroRT()), new Marca_DAO().obtenerDatos(rt.getModelo().getNombre()));
            TablaRecursosTec trt = new TablaRecursosTec();
            trt.setIdRec(Integer.parseInt(datos[0]));
            trt.setEstado(datos[1]);
            trt.setMarca(datos[4]);
            trt.setModelo(datos[3]);
            trt.setCentroInvestigacion(datos[2]);

            tablaRecursosTecs.add(trt);
        }
        controller.pedirSeleccionRecursoTecnologico(tablaRecursosTecs);
    }
    private void agruparPorCentroInvestigacion(List<TablaRecursosTec> tablaRecursosTecs){
        //Agrupamos por centro de investigacion.
        Collections.sort(tablaRecursosTecs);
    }

    public void obtenerRecursoTecnologicoActivo(String tipoRecursoTecnologico) throws Exception {
        RecursoTecnologico_DAO recursoTecnologico_dao = new RecursoTecnologico_DAO();
        recTecActivosReservables = new ArrayList<>();

        for (RecursoTecnologico rt : recursoTecnologico_dao.listar()) {
            if (rt.esDeTipoRecursoTecnologicoSeleccionado(tipoRecursoTecnologico)) {
                if (rt.esReservable()) {
                    // si el recurso tecnologico es reservable lo agrelo a la lista.
                    recTecActivosReservables.add(rt);
                }
            }
        }
    }

    public void recursoTecnologicoSeeccionado(TablaRecursosTec trecSelec) throws SQLException {
        for(RecursoTecnologico recTec: recTecActivosReservables){
            if(recTec.getNumeroRT() == trecSelec.getIdRec())
                recursoTecnologicoSeleccionado = recTec;
        }

        // terminar de implementar.
        if (verificarCentroInvestigacionCientificoLogueado()){
            obtenerTurnosRecursoTecnologogicoSeleccionado();
            obtenerFechaHoraActual();
        }else {
            controller.avisarflujoA1();
        }
    }

    private boolean verificarCentroInvestigacionCientificoLogueado() throws SQLException {
        obtenerCientificoLogueado();
        centroDeInvestigacionRecSeleccionad = new CentroDeInvestigacion_DAO().obtenerDatos(recursoTecnologicoSeleccionado.getNumeroRT());
        List<AsignacionCientificoDelCI> asignacionesCientDelCI = new ArrayList<>();

        asignacionesCientDelCI.add(new AsignacionCientificoDelCI(LocalDateTime.parse("2022-02-02T00:00"), LocalDateTime.parse("2022-02-03T00:00"),cientificoLogueado));
        asignacionesCientDelCI.add(new AsignacionCientificoDelCI(LocalDateTime.parse("2022-02-02T00:00"), null, cientificoLogueado));
        asignacionesCientDelCI.add(new AsignacionCientificoDelCI(LocalDateTime.parse("2022-02-02T00:00"), null, cientificoLogueado));

//        asignacionesCientDelCI.add(new AsignacionCientificoDelCI(LocalDateTime.parse("2022-02-02T00:00"), LocalDateTime.parse("2022-02-03T00:00"),new PersonalCientifico(2,"Roberto","Gonsalez",33666009, "rces0365@gmail.com","rces0365@gmail.com")));
  //      asignacionesCientDelCI.add(new AsignacionCientificoDelCI(LocalDateTime.parse("2022-02-02T00:00"), null, new PersonalCientifico(2,"Roberto","Gonsalez",33666009, "rces0365@gmail.com","rces0365@gmail.com")));
    //    asignacionesCientDelCI.add(new AsignacionCientificoDelCI(LocalDateTime.parse("2022-02-02T00:00"), null, new PersonalCientifico(2,"Roberto","Gonsalez",33666009, "rces0365@gmail.com","rces0365@gmail.com")));

        centroDeInvestigacionRecSeleccionad.setAsignacionesCientificoDelCIs(asignacionesCientDelCI);


        return recursoTecnologicoSeleccionado.esCientificoDeTuCentroInvestigacion(centroDeInvestigacionRecSeleccionad, cientificoLogueado);
    }

    //deberia devolver cientifico
    private void obtenerCientificoLogueado() {
        this.sesionActual.obtenerCientifico();
        cientificoLogueado = new PersonalCientifico(1,"Roberto","Gonsalez",33666009, "rces0365@gmail.com","rces0365@gmail.com");
    }

    public List<TipoRecursoTecnologico> getTipo(){ return tipoRecTecnologicos; }



    private void obtenerTurnosRecursoTecnologogicoSeleccionado() {
        obtenerFechaHoraActual();
        turnosRecTecnologicoSeleccionado = recursoTecnologicoSeleccionado.mostrarTurnos(fechaHoraActual);
        agruparYOrdenar();

        controller.pedirSeleccionTurno(turnosRecTecnologicoSeleccionado);

    }

    private void obtenerFechaHoraActual() {
        fechaHoraActual = LocalDateTime.now();
    }

    private void agruparYOrdenar(){

    }

    private List<String> turnoSeleccionadoPorUsuario;

    public void turnoSeleccionado(List<String> tunoSeleccionado) {
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

        for(Estado est: estados){
            if(est.esAmbitoTurno())
                if(est.esReservado())
                    estadoReservado = est;
        }
        recursoTecnologicoSeleccionado.reservar(turnoSeleccionadoPorUsuario, centroDeInvestigacionRecSeleccionad, fechaHoraActual, estadoReservado, cientificoLogueado);
        generarMail();
    }

    private void generarMail() {

    }


}
