package Models;

public class Modelo {
    private String nombre;

    public Modelo(String nombre) {
        this.nombre = nombre;
    }

    public Modelo() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void mostrarModelos(){}

    public String obtenerMiMarca(Marca marca) {
        Marca miMarca = marca;
        return  miMarca.getNombre();
    }
}
