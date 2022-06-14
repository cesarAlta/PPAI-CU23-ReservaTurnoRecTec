package Models;

import javax.xml.bind.PrintConversionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CentroDeInvestigacion {
    /*
       Atributos
    */
    private String nombre;
    private String sigla;
    private String direccion;
    private String edificio;
    private int piso;
    private int coordenadas;
    private int telefonosContacto[];
    private String correoElectronico;
    private int numeroResolucionCreacion;
    private Date fechaResolucionCreacion;
    private String reglamento;
    private String caracteristicasGenerales;
    private Date fechaAlta;
    private int tiempoAntelacionReserva;
    private Date fechaBaja;
    private String motivoBaja;
    private AsignacionCientificoDelCI asignacionCientificoDelCI;
    private RecursoTecnologico[] recursoTecnologico;
    /*
       Constructor
    */
    public CentroDeInvestigacion(String nombre, String sigla, String direccion, String edificio, int piso,
                                 int coordenadas, int[] telefonosContacto, String correoElectronico,
                                 int numeroResolucionCreacion, Date fechaResolucionCreacion, String reglamento,
                                 String caracteristicasGenerales, Date fechaAlta, int tiempoAntelacionReserva,
                                 Date fechaBaja, String motivoBaja) {
        this.nombre = nombre;
        this.sigla = sigla;
        this.direccion = direccion;
        this.edificio = edificio;
        this.piso = piso;
        this.coordenadas = coordenadas;
        this.telefonosContacto = telefonosContacto;
        this.correoElectronico = correoElectronico;
        this.numeroResolucionCreacion = numeroResolucionCreacion;
        this.fechaResolucionCreacion = fechaResolucionCreacion;
        this.reglamento = reglamento;
        this.caracteristicasGenerales = caracteristicasGenerales;
        this.fechaAlta = fechaAlta;
        this.tiempoAntelacionReserva = tiempoAntelacionReserva;
        this.fechaBaja = fechaBaja;
        this.motivoBaja = motivoBaja;
    }

    public CentroDeInvestigacion(String nombre, String sigla, String direccion, String edificio) {
        this.nombre = nombre;
        this.sigla = sigla;
        this.direccion = direccion;
        this.edificio = edificio;
    }

    public CentroDeInvestigacion() {

    }

    /*
        Inicio de metodos get y set
    */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(int coordenadas) {
        this.coordenadas = coordenadas;
    }

    public int[] getTelefonosContacto() {
        return telefonosContacto;
    }

    public void setTelefonosContacto(int[] telefonosContacto) {
        this.telefonosContacto = telefonosContacto;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public int getNumeroResolucionCreacion() {
        return numeroResolucionCreacion;
    }

    public void setNumeroResolucionCreacion(int numeroResolucionCreacion) {
        this.numeroResolucionCreacion = numeroResolucionCreacion;
    }

    public Date getFechaResolucionCreacion() {
        return fechaResolucionCreacion;
    }

    public void setFechaResolucionCreacion(Date fechaResolucionCreacion) {
        this.fechaResolucionCreacion = fechaResolucionCreacion;
    }

    public String getReglamento() {
        return reglamento;
    }

    public void setReglamento(String reglamento) {
        this.reglamento = reglamento;
    }

    public String getCaracteristicasGenerales() {
        return caracteristicasGenerales;
    }

    public void setCaracteristicasGenerales(String caracteristicasGenerales) {
        this.caracteristicasGenerales = caracteristicasGenerales;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public int getTiempoAntelacionReserva() {
        return tiempoAntelacionReserva;
    }

    public void setTiempoAntelacionReserva(int tiempoAntelacionReserva) {
        this.tiempoAntelacionReserva = tiempoAntelacionReserva;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getMotivoBaja() {
        return motivoBaja;
    }

    public void setMotivoBaja(String motivoBaja) {
        this.motivoBaja = motivoBaja;
    }

    /*
        Fin de metodos get y set
    */


    public String mostrar(){
        return "mostrar";
    }
    public String miDireccionActual(){
        return getDireccion();
    }
    public ArrayList<String> misDirectores(){
        ArrayList<String> directores = new ArrayList<>();
        return directores;
    }
    public boolean estoyActivo(){
        return true;
    }
    public ArrayList<String> misCientificos(){
        ArrayList<String> cientificos = new ArrayList<>();
        return cientificos;
    }
    public ArrayList<String> misCientificosActivos(){
        ArrayList<String> cientificosActivos = new ArrayList<>();
        return cientificosActivos;
    }
    public ArrayList<String> misRecursosTecnologicos(){
        ArrayList<String> misRecursosTecnologicos = new ArrayList<>();
        return misRecursosTecnologicos;
    }
    public void miBaja(){

    }


}
