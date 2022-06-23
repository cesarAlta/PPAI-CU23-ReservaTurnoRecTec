package Models;

public class Usuario {
    private String clave;
    private String usuario;
    private boolean habilitado;
    private PersonalCientifico personalCientifico;

    public Usuario(String clave, String usuario, boolean habilitado) {
        this.clave = clave;
        this.usuario = usuario;
        this.habilitado = habilitado;

    }

    public Usuario() {

    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
    public void habilitar(){}
    public void inhabilitar(){}
    public void modificarPassword(){}

    public PersonalCientifico getPersonalCientifico() {
        return personalCientifico;
    }

    public void setPersonalCientifico(PersonalCientifico personalCientifico) {
        this.personalCientifico = personalCientifico;
    }

    public PersonalCientifico obtenerCientifico(PersonalCientifico personalCientifico) {
        return personalCientifico;
    }
}
