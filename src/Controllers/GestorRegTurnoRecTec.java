package Controllers;

import Models.*;
import Persistents.CambioEstadoRT_DAO;
import Persistents.Marca_DAO;
import Persistents.RecursoTecnologico_DAO;
import Persistents.TipoRecursoTecnologico_DAO;
import Views.Controller;
import Views.TablaRecursosTec;
import javafx.scene.control.Button;

import javax.xml.stream.util.StreamReaderDelegate;
import java.time.LocalDateTime;
import java.util.*;

public class GestorRegTurnoRecTec {
    private Controller controller;
    private List<TipoRecursoTecnologico> tipoRecTecnologicos;
    private  List<RecursoTecnologico> recTecActivosReservables;
    private RecursoTecnologico recursoTecnologicoSeleccionado=null;
    private List<HashMap> turnosRecTecnologicoSeleccionado;
    private LocalDateTime fechaHoraActual;

    public GestorRegTurnoRecTec(Controller controller) {
        this.controller = controller;
    }

    public void recursoTecnologicoSeeccionado(TablaRecursosTec trecSelec) {
        for(RecursoTecnologico recTec: recTecActivosReservables){
            if(recTec.getNumeroRT() == trecSelec.getIdRec())
                recursoTecnologicoSeleccionado = recTec;
        }
        if (verificarCentroInvestigacionCientificoLogueado()){
            obtenerTurnosRecursoTecnologogicoSeleccionado();
            obtenerFechaHoraActual();
        }
    }

    private boolean verificarCentroInvestigacionCientificoLogueado() {
        return true;
    }

    public void opcionReservarTurnoDeRecursoTecnologico(){
        try {
            tipoRecTecnologicos = buscarTipoRecurso();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TipoRecursoTecnologico> buscarTipoRecurso() throws Exception {
        TipoRecursoTecnologico_DAO tipoRecTecnologicoDAO = new TipoRecursoTecnologico_DAO();
        List<TipoRecursoTecnologico> tiposRecTecnologicos=  tipoRecTecnologicoDAO.listar();
        return tiposRecTecnologicos;
    }

    public List<TipoRecursoTecnologico> getTipo(){ return tipoRecTecnologicos; }

    public List<TablaRecursosTec> tomarSeleccionTipoRecurso(String tipoRecursoTecnologico) throws Exception {

        recTecActivosReservables =  obtenerRecursoTecnologogicoActivo(tipoRecursoTecnologico);
        List<TablaRecursosTec> tablaRecursosTecs = new ArrayList<>();
        for( RecursoTecnologico rt : recTecActivosReservables){
            TablaRecursosTec trt = new TablaRecursosTec();
            trt.setIdRec(rt.getNumeroRT());
            trt.setModelo(rt.getModelo().getNombre());
            Marca_DAO marca_dao = new Marca_DAO();
            trt.setMarca(rt.getModelo().obtenerMiMarca(marca_dao.obtenerDatos(rt.getModelo().getNombre())));
           trt.setEstado( rt.getCambioEstadoRT().get(rt.getCambioEstadoRT().size()-1).getEstado().getNombre());
           trt.setButton(new Button("Ver"));
            tablaRecursosTecs.add(trt);
        }
        return tablaRecursosTecs;
    }

    public List<RecursoTecnologico> obtenerRecursoTecnologogicoActivo(String tipoRecursoTecnologico) throws Exception {
        RecursoTecnologico_DAO recursoTecnologico_dao = new RecursoTecnologico_DAO();
        List<RecursoTecnologico> recursosTecnologicos = new ArrayList<>();

        for (RecursoTecnologico rt : recursoTecnologico_dao.listar()) {
            if (rt.esDeTipoRecursoTecnologicoSeleccionado(tipoRecursoTecnologico)) {
                CambioEstadoRT_DAO cambioEstadoRT_dao = new CambioEstadoRT_DAO();
                rt.setCambioEstadoRT(cambioEstadoRT_dao.listar(rt.getNumeroRT()));
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
