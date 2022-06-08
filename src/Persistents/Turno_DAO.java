package Persistents;

import Models.TipoRecursoTecnologico;
import Models.Turno;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Turno_DAO extends Conexion {
    public List<Turno> listar(int numeroRT) throws SQLException {
        Turno turnoRecTec = null;
        List<Turno> turnos = new ArrayList<>();
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM turnos");
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                turnoRecTec = new Turno();
                turnoRecTec.setFechaGeneracion(rs.getDate("fechaGeneracion"));
                turnoRecTec.setDiaSemana(rs.getString("diaSemana"));
                turnoRecTec.setFechaHoraInicio(rs.getTimestamp("fechaHoraInicio").toLocalDateTime());
                turnoRecTec.setFechaHoraFin(rs.getTimestamp("fechaHoraFin").toLocalDateTime());
                CambioEstadoTurno_DAO cambioEstadoRT_dao= new CambioEstadoTurno_DAO();
                turnoRecTec.setCambioEstadoTurnos(cambioEstadoRT_dao.listar(rs.getInt("idturnos")));



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
