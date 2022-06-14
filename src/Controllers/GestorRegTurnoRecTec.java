package Controllers;

import Models.*;
import Persistents.*;
import Views.Controller;
import Views.TablaRecursosTec;
import javafx.scene.control.Button;

import javax.xml.stream.util.StreamReaderDelegate;
import java.time.LocalDateTime;
import java.util.*;

public class GestorRegTurnoRecTec {
    private Controller controller;
    // lista de tipos de recursos tecnologicos.
    private List<TipoRecursoTecnologico> tipoRecTecnologicos;
    private  List<RecursoTecnologico> recTecActivosReservables;
    private RecursoTecnologico recursoTecnologicoSeleccionado = null;
    private List<List<String>> turnosRecTecnologicoSeleccionado;
    private LocalDateTime fechaHoraActual;
    private Usuario usuarioActual;


    public GestorRegTurnoRecTec(Controller controller) {
        this.controller = controller;
    }

    public void nuevaReservaTurnoDeRecursoTecnologico()  {
        // verificar si el usuario va aqui o en el contructor
        usuarioActual = new Usuario("1234", "neuen" , true);
        try {
            buscarTipoRecurso();
        } catch (Exception e) {
            e.printStackTrace();
        }
        controller.pedirSeleccionTipoRecurso(this.tipoRecTecnologicos);
    }

    private void buscarTipoRecurso() throws Exception {
        TipoRecursoTecnologico_DAO tipoRecTecnologicoDAO = new TipoRecursoTecnologico_DAO();
        tipoRecTecnologicos =  tipoRecTecnologicoDAO.listar();
        // implementar el metodo *getNombre() terminar de implementar
        List<String> nombresRecTecnologicos = new ArrayList<>();
        for(TipoRecursoTecnologico trc : tipoRecTecnologicos){
            nombresRecTecnologicos.add(trc.getNombre());
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
        return true;
    }

    public List<TipoRecursoTecnologico> getTipo(){ return tipoRecTecnologicos; }

    public void tomarSeleccionTipoRecurso(String tipoRecursoTecnologico) throws Exception {

        //Obtenemos los recursos tecnologicos de acuerdo al tipo de recurso tecnologico seleccionado
        recTecActivosReservables =  obtenerRecursoTecnologogicoActivo(tipoRecursoTecnologico);
        List<TablaRecursosTec> tablaRecursosTecs = new ArrayList<>();

        //mientras exitan rectec seleccionados y activos.
        for( RecursoTecnologico rt : recTecActivosReservables) {
            Marca_DAO marca_dao = new Marca_DAO();
            CentroDeInvestigacion_DAO centroDeInvestigacion_dao = new CentroDeInvestigacion_DAO();

            // creo un arreglo para colocar los datos obtenidos
            CentroDeInvestigacion ci = centroDeInvestigacion_dao.obtenerDatos(rt.getNumeroRT());
            Marca marca = marca_dao.obtenerDatos(rt.getModelo().getNombre());

            String datos[] = rt.mostrarDatosRecursoTecnologico(ci, marca);
            TablaRecursosTec trt = new TablaRecursosTec();
            trt.setIdRec(Integer.parseInt(datos[0]));
            trt.setEstado(datos[1]);
            trt.setMarca(datos[4]);
            trt.setModelo(datos[3]);
            trt.setCentroInvestigacion(datos[2]);

            tablaRecursosTecs.add(trt);
        }
        // implementar, pasar por parametro el arreglo de rectec
        agruparYOrdenar();

        controller.pedirSeleccionRecursoTecnologico(tablaRecursosTecs);
    }

    public List<RecursoTecnologico> obtenerRecursoTecnologogicoActivo(String tipoRecursoTecnologico) throws Exception {
        RecursoTecnologico_DAO recursoTecnologico_dao = new RecursoTecnologico_DAO();
        List<RecursoTecnologico> recursosTecnologicos = new ArrayList<>();

        for (RecursoTecnologico rt : recursoTecnologico_dao.listar()) {
            if (rt.esDeTipoRecursoTecnologicoSeleccionado(tipoRecursoTecnologico)) {
                if (rt.esReservable()) {
                    recursosTecnologicos.add(rt);
                }
            }
        }

        return recursosTecnologicos;
    }

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



}
