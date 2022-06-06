package Models;

import java.util.Date;

public class AsignacionCientificoDelCI {

    /*
        Atributos
    */
    private Date fechaHoraDesde;
    private Date fechaHoraHasta;
    private Turno turno;
    private PersonalCientifico personalCientifico;

    /*
        Constructor
    */

    public AsignacionCientificoDelCI(Date fechaHoraDesde, Date fechaHoraHasta) {
        this.fechaHoraDesde = fechaHoraDesde;
        this.fechaHoraHasta = fechaHoraHasta;
    }

    // Inicio de metodos get y set

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
    //Fin de metodos get y set

    public void mostrarCientificoDelCI(){

    }
    public boolean esCientificoActivo(){
        return true;
    }
    public void misTurnos(){

    }

}
