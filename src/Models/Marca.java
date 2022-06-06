package Models;

public class Marca {
    /*
        Atributos
    */
    private String nombre;
    private Modelo modelo[];


    public Marca() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void mostrarMarca(){}
     public void mostrarMisModelos(){}

    @Override
    public String toString() {
        return "nombre=" + getNombre() ;
    }
}
