package Models;

import java.time.LocalDateTime;
import java.util.Date;

public class CambioEstadoTurno {
    /*
        Atributos
    */
    private Date fechaHoraDesde;
    private Date fechaHoraHasta;
    private Estado estado;
    /*
        Constructor
    */
    public CambioEstadoTurno(Date fechaHoraDesde, Date fechaHoraHasta) {
        this.fechaHoraDesde = fechaHoraDesde;
        this.fechaHoraHasta = fechaHoraHasta;
    }
    /*
        Inicio metodos gets, sets
    */

    public Date getFechaHoraDesde() {
        return fechaHoraDesde;
    }

    public void setFechaHoraDesde(Date fechaHoraDesde) {
        this.fechaHoraDesde = fechaHoraDesde;
    }

    public Date getFechaHoraHasta() {
        return fechaHoraHasta;
    }

    public void setFechaHoraHasta(Date fechaHoraHasta) {
        this.fechaHoraHasta = fechaHoraHasta;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    /*
        Fin metodos gets, sets
    */

    public String mostrarCambioEstadoTurno(){
        return "mostrar cambio de estado turno";
    }

    public Estado esActual(LocalDateTime horaFechaInicio, LocalDateTime horaFechaFin) {
        if(getFechaHoraDesde().equals(horaFechaInicio) && getFechaHoraHasta().equals(horaFechaFin))
            return mostrarEstado();
        return null;

    }

    private Estado mostrarEstado() {
        return getEstado();
    }
}
