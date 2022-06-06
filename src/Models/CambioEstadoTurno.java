package Models;

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
    /*
        Fin metodos gets, sets
    */

    public String mostrarCambioEstadoTurno(){
        return "mostrar cambio de estado turno";
    }
}
