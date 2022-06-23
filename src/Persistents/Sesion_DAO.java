package Persistents;

import Models.Estado;
import Models.Sesion;
import Models.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Sesion_DAO extends Conexion{

    public Sesion getSesionActual() throws SQLException {
        Sesion sesion = null;
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM sesiones");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                sesion = new Sesion();
                sesion.setFechaHoraHasta(rs.getTimestamp("horaFechaDesde").toLocalDateTime());
                if (rs.getDate("horaFechaHasta") != null)
                    sesion.setFechaHoraHasta(rs.getTimestamp("horaFechaHasta").toLocalDateTime());

            }
            rs.close();
            st.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.deconectar();
        }
        return sesion;
    }
}
