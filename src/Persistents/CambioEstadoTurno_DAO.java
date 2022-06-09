package Persistents;

import Models.CambioEstadoRT;
import Models.CambioEstadoTurno;
import Models.Marca;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CambioEstadoTurno_DAO extends Conexion{

    public List<CambioEstadoTurno> listar(int idTurno) throws SQLException {
        List<CambioEstadoTurno> cambioEstadoTurnos = new ArrayList<>();

        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM cambios_estados_turnos where idTurnos= ?");
            st.setInt(1,idTurno);
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                CambioEstadoTurno cambioEstadoTurno = new CambioEstadoTurno();
                cambioEstadoTurno.setFechaHoraDesde(rs.getDate("fechaHoraInicio").toLocalDate().atStartOfDay());
                if (rs.getDate("fechaHoraFin") != null)
                    cambioEstadoTurno.setFechaHoraHasta(rs.getTimestamp("fechaHoraFin").toLocalDateTime());

                //Asigo el Estado asociado
                Estado_DAO estado_dao = new Estado_DAO();
                cambioEstadoTurno.setEstado(estado_dao.obtenerDatos(rs.getInt("idEstados")));

                cambioEstadoTurnos.add(cambioEstadoTurno);
        }
        rs.close();
        st.close();

    } catch (Exception e) {
        throw e;
    } finally {
        this.deconectar();
    }
          return cambioEstadoTurnos;
}
}
