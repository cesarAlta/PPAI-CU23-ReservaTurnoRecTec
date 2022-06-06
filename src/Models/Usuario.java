package Models;

public class Usuario {
    private String clave;
    private String usuario;
    private boolean habilitado;

    public Usuario(String clave, String usuario, boolean habilitado) {
        this.clave = clave;
        this.usuario = usuario;
        this.habilitado = habilitado;
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
}
