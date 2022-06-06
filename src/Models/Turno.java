package Models;

import java.time.LocalDateTime;
import java.util.Date;
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

    public void mostrarDatos(LocalDateTime fechaHoraActual) {
        LocalDateTime horaFechaInicio =  getFechaHoraInicio();
        LocalDateTime horaFechaFin =getFechaHoraFin();
        for (CambioEstadoTurno cET: getCambioEstadoTurnos()){
            cET.esActual(horaFechaInicio, horaFechaFin);
        }


    }
}
