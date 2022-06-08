package Models;

import java.time.LocalDateTime;
import java.util.Date;

public class CambioEstadoTurno {
    /*
        Atributos
    */
    private LocalDateTime fechaHoraDesde;
    private LocalDateTime fechaHoraHasta;
    private Estado estado;
    /*
        Constructor
    */
    public CambioEstadoTurno(LocalDateTime fechaHoraDesde, LocalDateTime fechaHoraHasta) {
        this.fechaHoraDesde = fechaHoraDesde;
        this.fechaHoraHasta = fechaHoraHasta;
    }

    public CambioEstadoTurno() {

    }
    /*
        Inicio metodos gets, sets
    */

    public LocalDateTime getFechaHoraDesde() {
        return fechaHoraDesde;
    }

    public void setFechaHoraDesde(LocalDateTime fechaHoraDesde) {
        this.fechaHoraDesde = fechaHoraDesde;
    }

    public LocalDateTime getFechaHoraHasta() {
        return fechaHoraHasta;
    }

    public void setFechaHoraHasta(LocalDateTime fechaHoraHasta) {
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
/*
    El metodo esActual() verifica que sea el ultimo estado comparando la fechaHoraHasta este vacia.
    El metodo equals() compara dos fechas y devuelve true si son iguales.
 */
    public Boolean esActual() {
        if(getFechaHoraHasta()==null)
            return true;
        return false;
    }

    public String mostrarEstado() {
        return getEstado().getNombre();
    }
}
