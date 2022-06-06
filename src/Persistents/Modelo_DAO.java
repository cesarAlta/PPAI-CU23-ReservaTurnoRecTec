package Persistents;

import Models.Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Modelo_DAO extends Conexion{

    public Modelo obtenerDatos(int idmodelo) throws SQLException {
        Modelo modelo = null;
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM modelos where idmodelos = ?");
            st.setInt(1,idmodelo);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                modelo = new Modelo();
                modelo.setNombre(rs.getString("nombre"));
            }
            rs.close();
            st.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.deconectar();
        }
        return modelo;
    }

    public void mostrarModelos(){}
}
