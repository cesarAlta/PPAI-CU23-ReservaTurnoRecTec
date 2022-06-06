package Models;

import java.util.Date;
import java.util.Objects;

public class CambioEstadoRT {
    private Date fechaHoraDesde;
    private Date fechaHoraHasta;
    private Estado estado;

    public CambioEstadoRT(Date fechaHoraDesde, Date fechaHoraHasta) {
        this.fechaHoraDesde = fechaHoraDesde;
        this.fechaHoraHasta = fechaHoraHasta;
    }

    public CambioEstadoRT() {

    }

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
    public String mostrarCambioEstadoRT(){
        return "mostrar cambio de estado RT";
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public boolean esActual() {
        return Objects.equals(this.getFechaHoraHasta(), null);
    }
    public boolean esReservable(){
        return getEstado().esEsReservable();
    }
}
