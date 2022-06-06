package Models;

public class Estado {
    private String nombre;
    private String descripcion;
    private String ambito;
    private boolean esReservable;
    private boolean esCancelable;

    public Estado(String nombre, String descripcion, String ambito, boolean esReservable, boolean esCancelable) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ambito = ambito;
        this.esReservable = esReservable;
        this.esCancelable = esCancelable;
    }

    public Estado() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public boolean esEsReservable() {
        return esReservable;
    }

    public void setEsReservable(boolean esReservable) {
        this.esReservable = esReservable;
    }

    public boolean isEsCancelable() {
        return esCancelable;
    }

    public void setEsCancelable(boolean esCancelable) {
        this.esCancelable = esCancelable;
    }

    public String mostrarEstado(){
        return "mostrar estado";
    }
}
