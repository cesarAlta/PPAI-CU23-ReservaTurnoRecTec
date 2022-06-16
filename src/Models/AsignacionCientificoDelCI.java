package Models;

import java.time.LocalDateTime;
import java.util.Date;

public class AsignacionCientificoDelCI {

    /*
        Atributos
    */
    private LocalDateTime fechaHoraDesde;
    private LocalDateTime fechaHoraHasta;
    private Turno turno;
    private PersonalCientifico personalCientifico;

    /*
        Constructor
    */

    public AsignacionCientificoDelCI(LocalDateTime fechaHoraDesde, LocalDateTime fechaHoraHasta) {
        this.fechaHoraDesde = fechaHoraDesde;
        this.fechaHoraHasta = fechaHoraHasta;
    }

    // Inicio de metodos get y set

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
    //Fin de metodos get y set

    public void mostrarCientificoDelCI(){

    }
    public boolean esCientificoActivo(){
        return true;
    }
    public void misTurnos(){

    }

    public void esTuCientifico() {
    }
}
