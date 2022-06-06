package Models;

import java.util.Date;

public class Sesion {
    private Date fechaHoraDesde;
    private Date fechaHoraHasta;
    private Usuario usuario;

    public Sesion(Date fechaHoraDesde, Usuario usuario) {
        this.fechaHoraDesde = fechaHoraDesde;
        this.usuario = usuario;
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
}
