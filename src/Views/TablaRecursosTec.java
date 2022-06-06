package Views;

import javafx.scene.control.Button;

public class TablaRecursosTec {
    private  int idRec;
    private  String marca;
    private  String modelo;
    private  String estado;
    private Button button;

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

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
