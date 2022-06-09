package Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class RecursoTecnologico {
    private int numeroRT;
    private Date fechaAlta;
    private String imagenes;
    private int periodicidadMantenimientoPrev;
    private int duracionMantenimiento;
    private int fraccionTurnos;
    private List<Turno> turnos;
    private List<CambioEstadoRT> cambioEstadoRT;
    private Modelo modelo;
    private TipoRecursoTecnologico tipoRecursoTecnologico;

    public RecursoTecnologico(){}

    public RecursoTecnologico(int numeroRT, Date fechaAlta, String imagenes,
                              int periodicidadMantenimientoPrev,
                              int duracionMantenimiento, int fraccionTurnos) {
        this.numeroRT = numeroRT;
        this.fechaAlta = fechaAlta;
        this.imagenes = imagenes;
        this.periodicidadMantenimientoPrev = periodicidadMantenimientoPrev;
        this.duracionMantenimiento = duracionMantenimiento;
        this.fraccionTurnos = fraccionTurnos;
    }

    public int getNumeroRT() {
        return numeroRT;
    }

    public void setNumeroRT(int numeroRT) {
        this.numeroRT = numeroRT;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getImagenes() {
        return imagenes;
    }

    public void setImagenes(String imagenes) {
        this.imagenes = imagenes;
    }

    public int getPeriodicidadMantenimientoPrev() {
        return periodicidadMantenimientoPrev;
    }

    public void setPeriodicidadMantenimientoPrev(int periodicidadMantenimientoPrev) {
        this.periodicidadMantenimientoPrev = periodicidadMantenimientoPrev;
    }

    public int getDuracionMantenimiento() {
        return duracionMantenimiento;
    }

    public void setDuracionMantenimiento(int duracionMantenimiento) {
        this.duracionMantenimiento = duracionMantenimiento;
    }

    public int getFraccionTurnos() {
        return fraccionTurnos;
    }

    public void setFraccionTurnos(int fraccionTurnos) {
        this.fraccionTurnos = fraccionTurnos;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public TipoRecursoTecnologico getTipoRecursoTecnologico() {
        return tipoRecursoTecnologico;
    }

    public void setTipoRecursoTecnologico(TipoRecursoTecnologico tipoRecursoTecnologico) {
        this.tipoRecursoTecnologico = tipoRecursoTecnologico;
    }

    public List<Turno> getTurnos() {return turnos;}

    public void setTurnos(List<Turno> turnos) {this.turnos = turnos;}

    public List<CambioEstadoRT> getCambioEstadoRT() {
        return cambioEstadoRT;
    }

    public void setCambioEstadoRT(List<CambioEstadoRT> cambioEstadoRT) {
        this.cambioEstadoRT = cambioEstadoRT;
    }

    public boolean esDeTipoRecursoTecnologicoSeleccionado(String tipoRecSeleccionado){
        return getTipoRecursoTecnologico().getNombre().equals(tipoRecSeleccionado);
    }

    public boolean esReservable(){
        for(CambioEstadoRT ceRT: cambioEstadoRT) {
            if(ceRT.esActual()){
                if(ceRT.esReservable())
                    return true;
            }
        }
        return false;
    }

    public void mostrarRT(){}
    public void habilitar(){}
    public void conocerCategoria(){}
    public void conocerCaracteristicaRecurso(){}
    public void miModeloYMaarca(){}
    public void nuevoMantenimietoPreventivo(){}
    public void misTurnosDisponibles(){}

    public List<List<String>> mostrarTurnos(LocalDateTime fechaHoraActual) {
        List<List<String>> datosTrunos = new ArrayList<>();
        for(Turno turno: getTurnos()){
            if(turno.esPosteriorFechaActual(fechaHoraActual)){
                datosTrunos.add(turno.mostrarDatos());
            }
        }
        return datosTrunos;
    }

    public String[] mostrarDatosRecursoTecnologico(CentroDeInvestigacion centroDInvest,Marca marca) {
        String numeroInventarioRT = String.valueOf(getNumeroRT());
        //hay que mejorar este codigo
        String estadoRT = getCambioEstadoRT().get(getCambioEstadoRT().size()-1).getEstado().getNombre();
        String nombreCentroInvestigacvion = obtenerCentroDeInvestigacion(centroDInvest);
        String modeloRT = getModelo().getNombre();
        String marcaRT = mostrarMarcayModelo(marca);

       return  new String[]{numeroInventarioRT,estadoRT,nombreCentroInvestigacvion,modeloRT,marcaRT};
    }

    private String obtenerCentroDeInvestigacion(CentroDeInvestigacion centroDInvest) {
        return centroDInvest.getNombre();
    }

    private String mostrarMarcayModelo(Marca marca) {
        return getModelo().mostrarMarcayModelo(marca);
    }
}
