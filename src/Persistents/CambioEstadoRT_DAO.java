package Persistents;

import Models.CambioEstadoRT;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CambioEstadoRT_DAO extends Conexion {


    public List<CambioEstadoRT> listar(int idRecursoTec) throws SQLException {
        List<CambioEstadoRT> lista = null;
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM cambios_estados_rt where IdRecursosTecnologicos=? ");
            lista = new ArrayList<>();
            st.setInt(1,idRecursoTec);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                CambioEstadoRT cambioEstadoRT = new CambioEstadoRT();
                cambioEstadoRT.setFechaHoraDesde(rs.getDate("fechaHoraDesde"));
                cambioEstadoRT.setFechaHoraHasta(rs.getDate("fechaHoraFin"));
                Estado_DAO estado_dao = new Estado_DAO();
                cambioEstadoRT.setEstado(estado_dao.obtenerDatos(rs.getInt("idEstados")));
                lista.add(cambioEstadoRT);
            }
            rs.close();
            st.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.deconectar();
        }
        return lista;
    }
}
