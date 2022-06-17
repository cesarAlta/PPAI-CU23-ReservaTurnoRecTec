package Models;

import java.time.LocalDateTime;

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

    public AsignacionCientificoDelCI(LocalDateTime fechaHoraDesde, LocalDateTime fechaHoraHasta, PersonalCientifico pc) {
        this.fechaHoraDesde = fechaHoraDesde;
        this.fechaHoraHasta = fechaHoraHasta;
        this.personalCientifico = pc;
    }

    public AsignacionCientificoDelCI(LocalDateTime fechaHoraDesde, LocalDateTime fechaHoraHasta, Turno turno, PersonalCientifico personalCientifico) {
        this.fechaHoraDesde = fechaHoraDesde;
        this.fechaHoraHasta = fechaHoraHasta;
        this.turno = turno;
        this.personalCientifico = personalCientifico;
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

    public PersonalCientifico getPersonalCientifico() {
        return personalCientifico;
    }

    public void setPersonalCientifico(PersonalCientifico personalCientifico) {
        this.personalCientifico = personalCientifico;
    }

    public void mostrarCientificoDelCI(){

    }
    public boolean esCientificoActivo(){
        return true;
    }
    public void misTurnos(){

    }

    public boolean esTuCientifico(PersonalCientifico pc) {
        return getPersonalCientifico().getLegajo()== pc.getLegajo();
    }
}
