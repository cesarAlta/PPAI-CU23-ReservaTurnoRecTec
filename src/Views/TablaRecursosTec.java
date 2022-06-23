package Views;

import javafx.scene.control.Button;

public class TablaRecursosTec implements Comparable<TablaRecursosTec>{
    private  int idRec;
    private  String marca;
    private  String modelo;
    private  String estado;
    private String centroInvestigacion;

    public TablaRecursosTec(int idRec, String marca, String modelo, String estado, String centroInvestigacion) {
        this.idRec = idRec;
        this.marca = marca;
        this.modelo = modelo;
        this.estado = estado;
        this.centroInvestigacion = centroInvestigacion;
    }

    public int getIdRec() {
        return idRec;
    }

    public void setIdRec(int idRec) {
        this.idRec = idRec;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCentroInvestigacion() {
        return centroInvestigacion;
    }

    public void setCentroInvestigacion(String centroInvestigacion) {
        this.centroInvestigacion = centroInvestigacion;
    }
// implementamos el metodo de la interface para comparar los nombres de los centros de investigacion
    @Override
    public int compareTo(TablaRecursosTec tablaRecursosTec) {
        return getCentroInvestigacion().compareTo(tablaRecursosTec.getCentroInvestigacion());
    }
}
