package Persistents;

import Models.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecursoTecnologico_DAO extends Conexion {

    public List<RecursoTecnologico> listar() throws Exception{
        List<RecursoTecnologico> lista = null;
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM recursos_tecnologicos");
            lista = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                RecursoTecnologico recursoTecnologico = new RecursoTecnologico();
                recursoTecnologico.setNumeroRT(rs.getInt("idrecursos_tecnologicos"));
                recursoTecnologico.setDuracionMantenimiento(rs.getInt("duracionMantenimineto"));
                recursoTecnologico.setFechaAlta(rs.getDate("fechaAlta"));
                recursoTecnologico.setImagenes(rs.getString("imagenes"));
                recursoTecnologico.setPeriodicidadMantenimientoPrev(rs.getInt("duracionMantenimineto"));
                recursoTecnologico.setFraccionTurnos(rs.getInt("fraccionHorariaTurnos"));

                //Asigno el modelo asiciado
                Modelo_DAO modelo_dao = new Modelo_DAO();
                recursoTecnologico.setModelo(modelo_dao.obtenerDatos(rs.getInt("idmodelos")));

                //Asigno el tipo de recurso asociado
                TipoRecursoTecnologico_DAO tipoRecursoTecnologico_dao = new TipoRecursoTecnologico_DAO();
                recursoTecnologico.setTipoRecursoTecnologico(tipoRecursoTecnologico_dao.obtenerTipo(rs.getInt("idTipoRecursoTec")));

                //Asigno los turnos asicuado verrrrrrr
                Turno_DAO turno_dao = new Turno_DAO();
                recursoTecnologico.setTurnos(turno_dao.listar(recursoTecnologico.getNumeroRT()));

                //Asigno los cambios de estados asociados.
                CambioEstadoRT_DAO cambioEstadoRT_dao = new CambioEstadoRT_DAO();
                recursoTecnologico.setCambioEstadoRT(cambioEstadoRT_dao.listar(recursoTecnologico.getNumeroRT()));

                //Agrego a la lista
                lista.add(recursoTecnologico);
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
    public List<RecursoTecnologico> listar(String nombre) throws Exception{
        List<RecursoTecnologico> lista = null;
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM recursos_tecnologicos");
            lista = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                RecursoTecnologico recursoTecnologico = new RecursoTecnologico();
                recursoTecnologico.setNumeroRT(rs.getInt("idrecursos_tecnologicos"));
                recursoTecnologico.setDuracionMantenimiento(rs.getInt("duracionMantenimineto"));
                recursoTecnologico.setFechaAlta(rs.getDate("fechaAlta"));
                recursoTecnologico.setImagenes(rs.getString("imagenes"));
                recursoTecnologico.setPeriodicidadMantenimientoPrev(rs.getInt("duracionMantenimineto"));
                recursoTecnologico.setFraccionTurnos(rs.getInt("fraccionHorariaTurnos"));
                Modelo_DAO modelo_dao = new Modelo_DAO();
                Modelo modelo = modelo_dao.obtenerDatos(rs.getInt("idmodelos"));
                recursoTecnologico.setModelo(modelo);
                lista.add(recursoTecnologico);
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
