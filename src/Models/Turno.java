package Models;

import jdk.nashorn.internal.runtime.OptimisticReturnFilters;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Turno {
    private Date fechaGeneracion;
    private String diaSemana;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private List<CambioEstadoTurno> cambioEstadoTurnos;

    public Turno(Date fechaGeneracion, String diaSemana, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
        this.fechaGeneracion = fechaGeneracion;
        this.diaSemana = diaSemana;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
    }

    public Turno() {

    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public List<CambioEstadoTurno> getCambioEstadoTurnos() {
        return cambioEstadoTurnos;
    }

    public void setCambioEstadoTurnos(List<CambioEstadoTurno> cambioEstadoTurnos) {
        this.cambioEstadoTurnos = cambioEstadoTurnos;
    }

    public void mostrarTurno(){

    }
    public void estoyDisponible(){}

    /*
     Compara la fecha actual con la fecha del turno;
     devuelve verdadero si la fecha del turno esta despues de la actual.
     El metodo isfAfter() retorna true si getFechaHoraInico() esta despues de fechaHoraActual.
    */
    public boolean esPosteriorFechaActual(LocalDateTime fechaHoraActual) {
        if(getFechaHoraInicio().isAfter(fechaHoraActual)){
            return true;
        }
        return false;
    }

    public List<String> mostrarDatos() {
        LocalDateTime horaFechaInicio =  getFechaHoraInicio();
        LocalDateTime horaFechaFin =getFechaHoraFin();
        String estadoActual= null;

        for (CambioEstadoTurno cET: getCambioEstadoTurnos()){
            if(cET.esActual())
                estadoActual = cET.mostrarEstado();
        }

        List<String> datos = new ArrayList<>();
        datos.add(horaFechaInicio.toString());
        datos.add(horaFechaFin.toString());
        datos.add(estadoActual);

        return datos;
    }

    public void reservar(LocalDateTime fechaHoraActual) {
        for(CambioEstadoTurno cet: getCambioEstadoTurnos()){
            if(cet.esActual())
                cet.setFechaHoraHasta(fechaHoraActual);
        }
    }
}
