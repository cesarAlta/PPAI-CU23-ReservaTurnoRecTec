package Models;

public class PersonalCientifico {
    private int legajo;
    private String nombre;
    private String apellido;
    private int numeroDocumento;
    private String correoElectronicoInstitucional;
    private String correoElectronicoPersonal;
    private int telefonoCelular;
    private Usuario usuario;

    public PersonalCientifico(int legajo, String nombre, String apellido, int numeroDocumento,
                              String correoElectronicoInstitucional, String correoElectronicoPersonal,
                              int telefonoCelular, Usuario usuario) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroDocumento = numeroDocumento;
        this.correoElectronicoInstitucional = correoElectronicoInstitucional;
        this.correoElectronicoPersonal = correoElectronicoPersonal;
        this.telefonoCelular = telefonoCelular;
        this.usuario = usuario;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getCorreoElectronicoInstitucional() {
        return correoElectronicoInstitucional;
    }

    public void setCorreoElectronicoInstitucional(String correoElectronicoInstitucional) {
        this.correoElectronicoInstitucional = correoElectronicoInstitucional;
    }

    public String getCorreoElectronicoPersonal() {
        return correoElectronicoPersonal;
    }

    public void setCorreoElectronicoPersonal(String correoElectronicoPersonal) {
        this.correoElectronicoPersonal = correoElectronicoPersonal;
    }

    public int getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(int telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }
    public void mostrarPersonalCientifico(){}
    public void inhabilitarUsuario(){}
    public void habilitarUsuario(){}
    public void tengoUsuarioHabilitado(){}
    public void mostrarMisNovedades(){}

}
