package Persistents;

import Models.Estado;
import Models.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Usuario_DAO extends Conexion {

    public Usuario getUser() throws SQLException {
        Usuario usuario = null;
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM usuarios where idPersonalesCientificos = 1");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setClave(rs.getString("clave"));
                usuario.setHabilitado(rs.getString("habilitado").equals("si"));

            }
            rs.close();
            st.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.deconectar();
        }
        return usuario;
    }
}