package Persistents;

import Models.TipoRecursoTecnologico;
import Models.Turno;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Turno_DAO extends Conexion {
    public List<Turno> listar(int idRecTec) throws SQLException {
        List<Turno> turnos = new ArrayList<>();
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM turnos where idRecursosTecnologicos = ? ");
            st.setInt(1,idRecTec);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Turno turnoRecTec = new Turno();
                turnoRecTec.setFechaGeneracion(rs.getDate("fechaGeneracion"));
                turnoRecTec.setDiaSemana(rs.getString("diaSemana"));
                turnoRecTec.setFechaHoraInicio(rs.getTimestamp("fechaHoraInicio").toLocalDateTime());
                turnoRecTec.setFechaHoraFin(rs.getTimestamp("fechaHoraFin").toLocalDateTime());

                //Asigno los cambios de estadosTurnos asocaidos
                CambioEstadoTurno_DAO cambioEstadoTurno_dao = new CambioEstadoTurno_DAO();
                turnoRecTec.setCambioEstadoTurnos(cambioEstadoTurno_dao.listar(rs.getInt("idturnos")));

                turnos.add(turnoRecTec);
            }
            rs.close();
            st.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.deconectar();
        }
        return turnos;
    }
}
