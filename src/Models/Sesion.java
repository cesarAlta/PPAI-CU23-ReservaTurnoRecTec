package Models;

import java.time.LocalDateTime;
import java.util.Date;

public class Sesion {
    private LocalDateTime fechaHoraDesde;
    private LocalDateTime fechaHoraHasta;
    private Usuario usuario;

    public Sesion(LocalDateTime fechaHoraDesde, Usuario usuario) {
        this.fechaHoraDesde = fechaHoraDesde;
        this.usuario = usuario;
    }

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    //deberia devolver el cientifico.
    public PersonalCientifico obtenerCientifico() {
        return getUsuario().getPersonalCientifico();
    }
}
