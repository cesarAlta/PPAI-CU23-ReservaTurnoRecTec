package Persistents;

import Models.Estado;
import Models.Marca;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Estado_DAO extends Conexion{

    public Estado obtenerDatos(int idEstado) throws SQLException {
        Estado estado= null;
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
                estado.setEsReservable(rs.getString("esReservable").equals("si"));
//                estado.setEsCancelable(rs.getString("esCancelable").equals("si"));
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

    public List<Estado> listar() throws SQLException {
        List<Estado> estados = null;
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM estados");
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Estado estado = new Estado();
                estado.setNombre(rs.getString("nombre"));
                estado.setDescripcion(rs.getString("descripcion"));
                estado.setAmbito(rs.getString("ambito"));
                estado.setEsReservable(rs.getString("esReservable").equals("si"));
//                estado.setEsCancelable(rs.getString("esCancelable").equals("si"));
                estados.add(estado);
            }
            rs.close();
            st.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.deconectar();
        }
        return estados;
    }
}
