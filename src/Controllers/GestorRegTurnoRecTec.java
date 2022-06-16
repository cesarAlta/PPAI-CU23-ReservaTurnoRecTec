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
    private List<String> nombreTipoRecTecnologicos;
    //Lista de recursos tecnologicos activos reservables
    private  List<RecursoTecnologico> recTecActivosReservables;
    private RecursoTecnologico recursoTecnologicoSeleccionado = null;
    private List<List<String>> turnosRecTecnologicoSeleccionado;
    private LocalDateTime fechaHoraActual;
    private Usuario usuarioActual;
    private Sesion sesionActual;


    public GestorRegTurnoRecTec(Controller controller) {
        this.controller = controller;
        usuarioActual = new Usuario("1234", "neuen" , true);
        sesionActual = new Sesion(LocalDateTime.parse("2022-03-03T01:00:02"), usuarioActual);
    }

    public void nuevaReservaTurnoDeRecursoTecnologico()  {
        try {
            buscarTipoRecurso();
        } catch (Exception e) {
            e.printStackTrace();
        }
        controller.pedirSeleccionTipoRecurso(this.nombreTipoRecTecnologicos);
    }

    private void buscarTipoRecurso() throws Exception {
        //Obtenemos todos los tipos de recursos tecnologicos.
        tipoRecTecnologicos = new TipoRecursoTecnologico_DAO().listar();
        // Obtenemos los nombres de los tipos recursos tecnologicos.
        nombreTipoRecTecnologicos = new ArrayList<>();
        for(TipoRecursoTecnologico trc : tipoRecTecnologicos){
            nombreTipoRecTecnologicos.add(trc.getNombre());
        }
    }

    public void tomarSeleccionTipoRecurso(String tipoRecursoTecnologico) throws Exception {

        //Obtenemos los recursos tecnologicos de acuerdo al tipo de recurso tecnologico seleccionado
        obtenerRecursoTecnologicoActivo(tipoRecursoTecnologico);
        // creo una lista de objetos tabla para que se adapte a la tabla de la vista.
        List<TablaRecursosTec> tablaRecursosTecs = new ArrayList<>();

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

    public void recursoTecnologicoSeeccionado(TablaRecursosTec trecSelec) {
        for(RecursoTecnologico recTec: recTecActivosReservables){
            if(recTec.getNumeroRT() == trecSelec.getIdRec())
                recursoTecnologicoSeleccionado = recTec;
        }
        // terminar de implementar.
        if (verificarCentroInvestigacionCientificoLogueado()){
            obtenerTurnosRecursoTecnologogicoSeleccionado();
            obtenerFechaHoraActual();
        }
    }

    private boolean verificarCentroInvestigacionCientificoLogueado() {
        Usuario usuarioCientifico = obtenerCientificoLogueado();
        recursoTecnologicoSeleccionado.esCientificoDeTuCentroInvestigacion(new CentroDeInvestigacion());
        return true;
    }

    //deberia devolver cientifico
    private Usuario obtenerCientificoLogueado() {
        return this.sesionActual.obtenerCientifico();
    }

    public List<TipoRecursoTecnologico> getTipo(){ return tipoRecTecnologicos; }



    private void obtenerTurnosRecursoTecnologogicoSeleccionado() {
        obtenerFechaHoraActual();
        turnosRecTecnologicoSeleccionado = recursoTecnologicoSeleccionado.mostrarTurnos(fechaHoraActual);
        agruparYOrdenar();
        definirColorTurno();

        controller.pedirSeleccionTurno(turnosRecTecnologicoSeleccionado);

    }

    private void obtenerFechaHoraActual() {
        fechaHoraActual = LocalDateTime.now();
    }

    private void agruparYOrdenar(){

    }
    private void definirColorTurno(){

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

        for(Estado est: estados){
            if(est.esAmbitoTurno())
                if(est.esEsReservable())
                    recursoTecnologicoSeleccionado.reservar(turnoSeleccionadoPorUsuario,new CentroDeInvestigacion(), fechaHoraActual);
        }
        generarMail();
    }

    private void generarMail() {

    }


}
