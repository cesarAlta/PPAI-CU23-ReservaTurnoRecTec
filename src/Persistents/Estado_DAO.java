package Persistents;

import Models.Estado;
import Models.Marca;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Estado_DAO extends Conexion{

    public Estado obtenerDatos(int idEstado) throws SQLException {
        Estado estado
                = null;
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM estados where idestados= ?");
            st.setInt(1,idEstado);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                estado = new Estado();
                estado.setNombre(rs.getString("nombre"));
                estado.setDescripcion(rs.getString("descripcion"));
                estado.setAmbito(rs.getString("ambito"));
                estado.setEsReservable(rs.getString("esReservable").equals("si")? true : false);
                estado.setEsCancelable(rs.getString("esCancelable").equals("si")? true : false);
            }
            rs.close();
            st.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.deconectar();
        }
        return estado;

    }
}
